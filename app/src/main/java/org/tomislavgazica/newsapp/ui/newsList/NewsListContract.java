package org.tomislavgazica.newsapp.ui.newsList;

import org.tomislavgazica.newsapp.model.Article;

import java.util.List;

public interface NewsListContract {

    interface View{

        void updateArticlesList(List<Article> articles);

        void onNoNetworkConnection();

    }

    interface Presenter {

        void setView(NewsListContract.View view);

        void getArticles();

    }

}
