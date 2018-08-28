package xin.jerome.wechat.entity;

/**
 * accessToken bean
 *
 * @author Jerome Zhu
 * @since 2018.08.28 10:06
 */
public class AccessToken {

    private String token;
    private String expiresIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }
}
