package xin.jerome.wechat.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import xin.jerome.wechat.entity.AccessToken;
import xin.jerome.wechat.entity.Menu;
import xin.jerome.wechat.entity.menu.Button;
import xin.jerome.wechat.entity.menu.ClickButton;
import xin.jerome.wechat.entity.menu.ViewButton;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * 对微信平台请求的包
 *
 * @author Jerome Zhu
 * @since 2018.08.28 09:31
 */
public class WeChatUtil {

    private static final String APP_ID = "wxee494735fb88730c";
    private static final String APP_SECRET = "9e4b5b747e583aa602d1b2794dfa114b";
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * get请求
     *
     * @param url 请求的url地址
     * @return JSONObject
     */
    public static JSONObject doGetStr(String url) throws IOException {

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;

        HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");
            jsonObject = JSONObject.parseObject(result);
            // 处理请求错误
            String errcode = jsonObject.getString("errcode");
            if (errcode != null) {
                return null;
            }
        }
        return jsonObject;
    }

    /**
     * post 请求
     *
     * @param url    请求对象
     * @param outStr 请求参数
     * @return JSONObject
     */
    public static JSONObject doPostStr(String url, String outStr) throws IOException {

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type","application/json; encoding=utf-8");
        httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
        JSONObject jsonObject = null;

        HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");
            jsonObject = JSONObject.parseObject(result);
        }
        return jsonObject;
    }

    /**
     * 获取accessToken
     *
     * @return AccessToken
     */
    public static AccessToken getAccessToken() throws IOException{
        AccessToken accessToken = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("APPID", APP_ID).replace("APPSECRET", APP_SECRET);
        JSONObject jsonObject = doGetStr(url);
        if (jsonObject != null) {
            accessToken.setToken(jsonObject.getString("access_token"));
            accessToken.setExpiresIn(jsonObject.getString("expires_in"));
        }
        return accessToken;
    }

    /**
     * 上传文件
     *
     * @param filePath    文件的路径
     * @param accessToken token值
     * @param type        文件类型
     * @return JSONObject
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws KeyManagementException
     */
    public static JSONObject upload(String filePath, String accessToken, String type)
            throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new IOException("文件不存在");
        }

        String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);

        URL urlObj = new URL(url);
        //连接
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        con.setRequestMethod("POST");
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);

        //设置请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");

        //设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");

        byte[] head = sb.toString().getBytes("utf-8");

        //获得输出流
        OutputStream out = new DataOutputStream(con.getOutputStream());
        //输出表头
        out.write(head);

        //文件正文部分
        //把文件已流文件的方式 推入到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();

        //结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分隔线

        out.write(foot);
        out.flush();
        out.close();

        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        String result = null;
        try {
            //定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        JSONObject jsonObj = JSONObject.parseObject(result);
        return jsonObj;
    }

    /**
     * 初始化菜单
     *
     * @return Menu
     */
    public static Menu initMenu() {
        Menu menu = new Menu();

        ClickButton clickButton = new ClickButton();
        clickButton.setName("click菜单");
        clickButton.setType("click");
        clickButton.setKey("click_1");

        ViewButton viewButton = new ViewButton();
        viewButton.setName("view菜单");
        viewButton.setType("view");
        viewButton.setUrl("http://wechat.jeromezhu.xin/hello");

        ClickButton clickButton31 = new ClickButton();
        clickButton31.setName("扫码事件");
        clickButton31.setType("scancode_push");
        clickButton31.setKey("scan_code_1");
        ClickButton clickButton32 = new ClickButton();
        clickButton32.setName("地理位置");
        clickButton32.setType("location_select");
        clickButton32.setKey("location_1");
        Button button30 = new Button();
        button30.setName("多级菜单");
        button30.setSub_button(new Button[]{clickButton31,clickButton32});

        menu.setButton(new Button[]{clickButton, viewButton, button30});
        return menu;
    }

    public static int createMenu(String token, String menu) throws IOException {
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN",token);
        System.out.println(url);
        JSONObject jsonObject = doPostStr(url, menu);
        int result;
        if (jsonObject != null) {
            result = jsonObject.getInteger("errcode");
        }
        return -1;
    }

}
