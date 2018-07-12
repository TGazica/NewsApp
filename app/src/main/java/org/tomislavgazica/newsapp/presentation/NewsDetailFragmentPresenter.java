package org.tomislavgazica.newsapp.presentation;

import org.tomislavgazica.newsapp.model.Article;
import org.tomislavgazica.newsapp.ui.newsDetails.fragment.NewsDetailsFragmentContract;

public class NewsDetailFragmentPresenter implements NewsDetailsFragmentContract.Presenter{

    private NewsDetailsFragmentContract.View view;

    @Override
    public void setView(NewsDetailsFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void getNewsData(Article article) {
        view.setContent(article.getUrl());
    }
}
