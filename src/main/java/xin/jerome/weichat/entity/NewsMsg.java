package xin.jerome.weichat.entity;

import java.util.List;

/**
 * news msg bean
 *
 * @author Jerome Zhu
 * @since 2018.08.27 16:40
 */
public class NewsMsg extends BaseMsg{

    private int ArticleCount;
    private List<New> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<New> getArticles() {
        return Articles;
    }

    public void setArticles(List<New> articles) {
        Articles = articles;
    }
}
