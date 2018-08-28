package xin.jerome.wechat.entity;

/**
 * 音乐消息
 *
 * @author Jerome Zhu
 * @since 2018.08.28 12:43
 */
public class MusicMsg extends BaseMsg{

    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        this.Music = music;
    }
}
