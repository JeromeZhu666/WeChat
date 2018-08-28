package xin.jerome.wechat.util;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xin.jerome.wechat.entity.AccessToken;

import java.io.IOException;


/**
 * test WeChatUtil
 *
 * @author Jerome Zhu
 * @since 2018.08.28 10:14
 */
public class WeChatUtilTest {

    private Logger logger = LoggerFactory.getLogger(WeChatUtilTest.class);

    @Test
    public void testGetAccessToke() {
        try {
            AccessToken accessToken = WeChatUtil.getAccessToken();
            logger.info("token:" + accessToken.getToken());
            logger.info("expires_in:" + accessToken.getExpiresIn());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUploadGetMediaId() {
        String accessToken = "13_aw9m3TxWLJJZ2MNsHd1n0QpvigyPoAtj6cRZb9IjkpWjrHFYqkkdKWqGwPEFP2JkD_XfvhOnMbHBiVf1SamUne0bSi5ga9flWKOYPmVBftMYs_GQxaNISOpHi1TQ-tdj-_EZEBjsTIcnvUPZUOIfACAYSM";
        String filePath = "C:\\Users\\user\\Pictures\\Saved Pictures\\th.jpg";
        try {
            JSONObject jsonObject = WeChatUtil.upload(filePath, accessToken, "image");
            String mediaId = jsonObject.getString("media_id");
            logger.info("mediaId:" + mediaId);
            // Q-asSquQGGkvhBQHeKNt9DvBu_83Xkjx_6h3_sWhPYPOSgbLeVqObEyUR_LiHWE4
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUploadGetThumbMediaId() {
        String accessToken = "13_HNEUT7hHTGcMD_GysNOH16qu6uaXzhUHu99cfpNSmD_sQmLN93KlutGp1pGsumUkFk2B66d8ICrLsF5gvN65YHlLgLWNznYEW92KbTPnEUs8HvqKhQWK0qKQlA-GqADDv-sj4IzjOKYySp7vUFEbADAITS";
        String filePath = "C:\\Users\\user\\Pictures\\Saved Pictures\\th 3.jpg";
        try {
            JSONObject jsonObject = WeChatUtil.upload(filePath, accessToken, "thumb");
            String thumbMediaId = jsonObject.getString("thumb_media_id");
            logger.info("thumbMediaId:" + thumbMediaId);
            // _4hrg2TDWokhgbbMsbUA0XeKPbPTQdQmVMVNam6IFy7Fz9he35MH2iocDSAD2xPn
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateMenu() throws IOException {
        String token = "13_YB-ZGDGpMGEvdk9I6RtVuyB5AGVvFqb8hDxjhxzt-V5V3YV_Bo6zv-gSzpjn2PC0iUZzN0nKa56JA2-l-5-CI47bHBin3cZ7IHISo7HqbQ4fRnoZ8dA1_rH17a4TUX-pDoFG8355Y2OVPcmZFMMjAIASZZ";
        String menu = JSONObject.toJSONString(WeChatUtil.initMenu());
        logger.info("menu :" +menu);
        int result = WeChatUtil.createMenu(token, menu);
        if (result == 0) {
            logger.info("Create menu success");
        }
    }

}
