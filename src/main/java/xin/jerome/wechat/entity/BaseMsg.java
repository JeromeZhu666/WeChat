package xin.jerome.wechat.entity;

/**
 * base msg bean
 *
 * @author Jerome Zhu
 * @since 2018.08.27 16:41
 */
public class BaseMsg {

    private String ToUserName;// 开发者微信号
    private String FromUserName;// 发送方帐号（一个OpenID）
    private Long CreateTime;// 消息创建时间 （整型）
    private String MsgType; // text

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
