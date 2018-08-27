package xin.jerome.weichat.entity;

/**
 * textMsg bean
 *
 * @author Jerome Zhu
 * @since 2018.08.27 14:05
 */
public class TextMsg extends BaseMsg{


    private String Content;//文本消息内容
    private String MsgId;//消息id，64位整型

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
