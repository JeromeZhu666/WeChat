package xin.jerome.wechat.entity;

import xin.jerome.wechat.entity.menu.Button;

/**
 * 菜单对象
 *
 * @author Jerome Zhu
 * @since 2018.08.28 13:55
 */
public class Menu {
    private Button[] button;

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }
}
