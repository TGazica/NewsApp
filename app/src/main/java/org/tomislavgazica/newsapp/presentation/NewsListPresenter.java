package org.tomislavgazica.newsapp.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;

import org.tomislavgazica.newsapp.App;
import org.tomislavgazica.newsapp.Constants;
import org.tomislavgazica.newsapp.interaction.ApiInteractor;
import org.tomislavgazica.newsapp.model.Article;
import org.tomislavgazica.newsapp.model.ArticlesList;
import org.tomislavgazica.newsapp.persistance.ArticlesHolder;
import org.tomislavgazica.newsapp.ui.newsList.NewsListContract;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsListPresenter implements NewsListContract.Presenter {

    private NewsListContract.View view;
    private final ApiInteractor apiInteractor;
    private ArticlesHolder holder;
    private SharedPreferences sharedPreferences;
    private Activity activity;
    private Date date;
    private String filePath;

    public NewsListPresenter(ApiInteractor apiInteractor, Activity activity) {
        this.apiInteractor = apiInteractor;
        this.activity = activity;
        sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        holder = ArticlesHolder.getInstance();
        filePath = App.getFilePath();
    }

    @Override
    public void setView(NewsListContract.View view) {
        this.view = view;
    }

    @Override
    public void getArticles() {

        date = new Date(sharedPreferences.getLong(Constants.FILE_DOWNLOAD_TIME, 0));
        Date currentDate = new Date(System.currentTimeMillis());

        if (isNetworkAvailable()) {
            if (currentDate.getTime() - date.getTime() >= Constants.REFRESH_TIME) {
                try {
                    deleteDataInFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                apiInteractor.getArticles(getArticlesCallback(), Constants.SOURCE_BBC, Constants.SORT_BY_TOP, Constants.API_KEY);
            } else {
                try {
                    readFile();
                    view.updateArticlesList(holder.getArticles());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            view.onNoNetworkConnection();
            try {
                readFile();
                view.updateArticlesList(holder.getArticles());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Callback<ArticlesList> getArticlesCallback() {
        return new Callback<ArticlesList>() {
            @Override
            public void onResponse(Call<ArticlesList> call, Response<ArticlesList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Article> articles = response.body().articles;

                    date = new Date(System.currentTimeMillis());
                    sharedPreferences.edit().putLong(Constants.FILE_DOWNLOAD_TIME, date.getTime()).apply();
                    try {
                        writeFile(articles);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    holder.setArticles(articles);

                    view.updateArticlesList(holder.getArticles());
                }

            }

            @Override
            public void onFailure(Call<ArticlesList> call, Throwable t) {

            }
        };
    }

    private void writeFile(List<Article> articles) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(articles);
        objectOutputStream.close();
    }

    private void readFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        holder.setArticles((List<Article>) objectInputStream.readObject());
        objectInputStream.close();
    }

    private void deleteDataInFile() throws IOException {
        PrintWriter printWriter = new PrintWriter(filePath);
        printWriter.write("");
        printWriter.close();
    }

    public boolean isNetworkAvailable() {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
