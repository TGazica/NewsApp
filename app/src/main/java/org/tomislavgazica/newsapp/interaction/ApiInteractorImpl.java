package org.tomislavgazica.newsapp.interaction;


import org.tomislavgazica.newsapp.model.ArticlesList;
import org.tomislavgazica.newsapp.networking.ApiService;

import retrofit2.Callback;

public class ApiInteractorImpl implements ApiInteractor{

    private final ApiService apiService;

    public ApiInteractorImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getArticles(Callback<ArticlesList> callback, String source, String sortBy, String apiKey) {
        apiService.getArticles(source, sortBy, apiKey).enqueue(callback);
    }
}
