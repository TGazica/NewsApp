package org.tomislavgazica.newsapp.ui.newsDetails;

import org.tomislavgazica.newsapp.model.Article;

import java.util.List;

public interface NewsDetailsContract {

    interface View{

        void updateArticleList(List<Article> articles, int selectedArticle);

    }

    interface Presenter{

        void setView(NewsDetailsContract.View view);

        void getArticles(Article article);

        String getArticleTitle(int position);

        String getArticleTitle(Article article);

    }

}
