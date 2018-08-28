package xin.jerome.wechat.entity.menu;

/**
 * view 类型的菜单
 *
 * @author Jerome Zhu
 * @since 2018.08.28 13:54
 */
public class ViewButton extends Button{

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
