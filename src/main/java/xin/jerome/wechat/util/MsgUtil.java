package xin.jerome.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import xin.jerome.wechat.entity.*;

/**
 * dispose msg utils
 *
 * @author Jerome Zhu
 * @since 2018.08.27 14:11
 */
public class MsgUtil {

    public static final String MSG_TEXT = "text";
    public static final String MSG_IMAGE = "image";
    public static final String MSG_VOICE = "voice";
    public static final String MSG_VIDEO = "video";
    public static final String MSG_MUSIC = "music";
    public static final String MSG_LINK = "link";
    public static final String MSG_LOCATION = "location";
    public static final String MSG_EVENT = "event";
    public static final String MSG_SUBSCRIBE = "subscribe";
    public static final String MSG_UNSUBSCRIBE = "unsubscribe";
    public static final String MSG_CLICK = "CLICK";
    public static final String MSG_VIEW = "VIEW";
    public static final String MSG_NEWS = "news";

    /**
     * 将xml转成map对象
     *
     * @param request
     * @return Map<String,String>
     */
    public static Map<String,String> xmlToMap(HttpServletRequest request){
        Map<String,String> map = new HashMap<String,String>();
        SAXReader reader = new SAXReader();
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            Document doc = reader.read(inputStream);
            Element root = doc.getRootElement();
            List<Element> list = root.elements();
            for (Element element : list) {
                map.put(element.getName(), element.getText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally{
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }


    /**
     * 初始化菜单
     *
     * @return String
     */
    public static String initMenuText() {
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎您的关注，下面是菜单：\n\n");
        sb.append("1、随便说\n");
        sb.append("2、跟我做\n");
        sb.append("3、图文消息\n");
        sb.append("4、图片消息\n");
        sb.append("5、音乐消息\n");
        sb.append("打出?显示菜单\n\n");
        return sb.toString();
    }

    /**
     * 将文本消息对象转成xml
     *
     * @param textMsg
     * @return String
     */
    public static String textMsgToXml(TextMsg textMsg) {
        XStream xstream  = new XStream();
        // 将根元素替换成   xml
        xstream.alias("xml", textMsg.getClass());
        return xstream.toXML(textMsg);
    }

    /**
     * 初始化文本消息
     *
     * @param fromUserName
     * @param toUserName
     * @param content
     * @return String
     */
    public static String initTextMsg(String fromUserName, String toUserName, String content) {
        TextMsg textMsg = new TextMsg();
        textMsg.setToUserName(fromUserName);
        textMsg.setFromUserName(toUserName);
        textMsg.setMsgType(MSG_TEXT);
        textMsg.setCreateTime(Instant.now().toEpochMilli());
        textMsg.setContent(content);
        return textMsgToXml(textMsg);
    }

    /**
     * 图文消息转XML
     *
     * @param newsMsg
     * @return
     */
    public static String newsMsgToXml(NewsMsg newsMsg) {
        XStream xstream  = new XStream();
        // 将根元素替换成   xml
        xstream.alias("xml", newsMsg.getClass());
        xstream.alias("item", new New().getClass());
        return xstream.toXML(newsMsg);
    }

    /**
     * 初始化图文消息
     *
     * @param fromUserName
     * @param toUserName
     * @return
     */
    public static String initNewsMsg(String fromUserName, String toUserName) {
        List<New> news = new ArrayList<>();
        NewsMsg newsMsg = new NewsMsg();

        New new1 = new New();
        new1.setTitle("图文消息头");
        new1.setDescription("图文消息的描述\n图文消息的描述\n图文消息的描述\n图文消息的描述\n");
        new1.setPicUrl("https://tse1.mm.bing.net/th?id=OIP.wL067eV7Gi_VhKRT31y4MAHaEo&amp;pid=Api");
        new1.setUrl("wechat.jeromezhu.xin/hello");
        news.add(new1);

        newsMsg.setToUserName(fromUserName);
        newsMsg.setFromUserName(toUserName);
        newsMsg.setCreateTime(Instant.now().toEpochMilli());
        newsMsg.setMsgType(MSG_NEWS);
        newsMsg.setArticles(news);
        newsMsg.setArticleCount(news.size());

        return newsMsgToXml(newsMsg);
    }

    /**
     * 图片消息转XML
     *
     * @param imageMsg
     * @return String
     */
    public static String imageMsgToXml(ImageMsg imageMsg) {
        XStream xstream  = new XStream();
        // 将根元素替换成   xml
        xstream.alias("xml", imageMsg.getClass());
        return xstream.toXML(imageMsg);
    }

    /**
     * 初始化图片消息
     *
     * @param fromUserName
     * @param toUserName
     * @return
     */
    public static String initImageMsg(String fromUserName, String toUserName) {
        Image image = new Image();
        image.setMediaId("Q-asSquQGGkvhBQHeKNt9DvBu_83Xkjx_6h3_sWhPYPOSgbLeVqObEyUR_LiHWE4");
        ImageMsg imageMsg = new ImageMsg();
        imageMsg.setFromUserName(toUserName);
        imageMsg.setToUserName(fromUserName);
        imageMsg.setMsgType(MSG_IMAGE);
        imageMsg.setCreateTime(Instant.now().toEpochMilli());
        imageMsg.setImage(image);
        return imageMsgToXml(imageMsg);
    }

    /**
     * 音乐消息转XML
     *
     * @param musicMsg
     * @return String
     */
    public static String musicMsgToXml(MusicMsg musicMsg) {
        XStream xstream  = new XStream();
        // 将根元素替换成   xml
        xstream.alias("xml", musicMsg.getClass());
        return xstream.toXML(musicMsg);
    }

    public static String initMusicMsg(String fromUserName, String toUserName) {
        Music music = new Music();
        music.setTitle("失恋阵线联盟");
        music.setDescription("失恋阵线联盟  歌手：草蜢 所属专辑：失恋阵线联盟");
        music.setHQMusicUrl("https://96.f.1ting.com/5b84e05b/6aea533828e45aff7bb79fba0f391f47/2018/08/28X/28a_Baoling/02.mp3");
        music.setMusicUrl("https://96.f.1ting.com/5b84e05b/6aea533828e45aff7bb79fba0f391f47/2018/08/28X/28a_Baoling/02.mp3");
        music.setThumbMediaId("_4hrg2TDWokhgbbMsbUA0XeKPbPTQdQmVMVNam6IFy7Fz9he35MH2iocDSAD2xPn");
        MusicMsg musicMsg = new MusicMsg();
        musicMsg.setFromUserName(toUserName);
        musicMsg.setToUserName(fromUserName);
        musicMsg.setMsgType(MSG_MUSIC);
        musicMsg.setCreateTime(Instant.now().toEpochMilli());
        musicMsg.setMusic(music);
        return musicMsgToXml(musicMsg);

    }

}
