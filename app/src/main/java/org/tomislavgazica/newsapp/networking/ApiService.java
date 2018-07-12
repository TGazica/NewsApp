package org.tomislavgazica.newsapp.networking;


import org.tomislavgazica.newsapp.model.ArticlesList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("articles")
    Call<ArticlesList> getArticles(@Query("source") String source, @Query("sortBy") String sortBy, @Query("apiKey") String apiKey);

}
