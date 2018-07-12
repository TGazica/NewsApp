package org.tomislavgazica.newsapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticlesList {

    @Expose
    @SerializedName("articles")
    public List<Article> articles;

}
