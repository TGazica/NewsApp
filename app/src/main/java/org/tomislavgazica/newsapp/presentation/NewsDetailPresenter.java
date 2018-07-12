package org.tomislavgazica.newsapp.presentation;

import org.tomislavgazica.newsapp.model.Article;
import org.tomislavgazica.newsapp.persistance.ArticlesHolder;
import org.tomislavgazica.newsapp.ui.newsDetails.NewsDetailsContract;

public class NewsDetailPresenter implements NewsDetailsContract.Presenter {

    private NewsDetailsContract.View view;
    private ArticlesHolder holder;

    public NewsDetailPresenter() {
        holder = ArticlesHolder.getInstance();
    }

    @Override
    public void setView(NewsDetailsContract.View view) {
        this.view = view;
    }

    @Override
    public void getArticles(Article article) {

        int selectedArticle = 0;

        for (int i = 0; i < holder.getArticles().size(); i++) {
            if (holder.getArticles().get(i).getId().equals(article.getId())) {
                selectedArticle = i;
                break;
            }
        }

        view.updateArticleList(holder.getArticles(), selectedArticle);
    }

    @Override
    public String getArticleTitle(int position) {
        return holder.getArticles().get(position).getTitle();
    }

    @Override
    public String getArticleTitle(Article article) {
        return article.getTitle();
    }
}
