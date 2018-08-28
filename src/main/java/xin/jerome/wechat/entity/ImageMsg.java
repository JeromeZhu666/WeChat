package xin.jerome.wechat.entity;

/**
 * Image msg bean
 *
 * @author Jerome Zhu
 * @since 2018.08.28 11:23
 */
public class ImageMsg extends BaseMsg{

    private Image Image;

    public xin.jerome.wechat.entity.Image getImage() {
        return Image;
    }

    public void setImage(xin.jerome.wechat.entity.Image image) {
        Image = image;
    }
}
