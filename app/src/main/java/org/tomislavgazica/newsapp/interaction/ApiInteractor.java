package org.tomislavgazica.newsapp.interaction;


import org.tomislavgazica.newsapp.model.ArticlesList;

import retrofit2.Callback;

public interface ApiInteractor {

    void getArticles(Callback<ArticlesList> callback, String source, String sortBy, String apiKey);

}
