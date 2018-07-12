package org.tomislavgazica.newsapp.persistance;
import org.tomislavgazica.newsapp.model.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticlesHolder {

    private static ArticlesHolder instance;
    private List<Article> articles;

    private ArticlesHolder(){
        articles = new ArrayList<>();
    }

    public static ArticlesHolder getInstance(){
        if (instance == null){
            instance = new ArticlesHolder();
        }
        return instance;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles.clear();
        this.articles.addAll(articles);
    }
}
