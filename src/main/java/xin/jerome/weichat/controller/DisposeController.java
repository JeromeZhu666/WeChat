package xin.jerome.weichat.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.jerome.weichat.util.MsgUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * dispose weichat request
 *
 * @author Jerome Zhu
 * @since 2018.08.27 15:28
 */
@RestController
public class DisposeController {

    @PostMapping("/")
    public String checkServer(HttpServletRequest request) {
        String replyContent;
        String replyMsg = "";

        Map<String, String> map = MsgUtil.xmlToMap(request);
        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        String msgType = map.get("MsgType");
        String content = map.get("Content");

        if(MsgUtil.MSG_TEXT.equals(msgType)) {
            // dispose reply content
            if("1".equals(content)) {
                replyContent = "这是顺便说";
            } else if ("2".equals(content)) {
                replyContent = "这是顺便做";
            } else if ("3".equals(content)) {
                replyContent = MsgUtil.initNewsMsg(fromUserName,toUserName);
                return replyContent;
            } else if ("?".equals(content) || "？".equals(content)) {
                replyContent = MsgUtil.initMenuText();
            } else {
                replyContent = "you send message is:" + content;
            }
            replyMsg = MsgUtil.initTextMsg(fromUserName, toUserName, replyContent);
        }else if(MsgUtil.MSG_EVENT.equals(msgType)) {
            String eventType = map.get("Event");
            if (MsgUtil.MSG_SUBSCRIBE.equals(eventType)) {
                replyMsg = MsgUtil.initTextMsg(fromUserName, toUserName, MsgUtil.initMenuText());
            }
        }

        return replyMsg;
    }

}
