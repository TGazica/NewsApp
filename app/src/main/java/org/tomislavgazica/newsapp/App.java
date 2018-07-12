package org.tomislavgazica.newsapp;

import android.app.Application;

import org.tomislavgazica.newsapp.interaction.ApiInteractor;
import org.tomislavgazica.newsapp.interaction.ApiInteractorImpl;
import org.tomislavgazica.newsapp.networking.ApiService;
import org.tomislavgazica.newsapp.networking.RetrofitUtil;

import java.io.File;


import retrofit2.Retrofit;

public class App extends Application {

    private static ApiInteractor apiInteractor;
    private File file;
    private static String filePath;

    @Override
    public void onCreate() {
        super.onCreate();

        final Retrofit retrofit = RetrofitUtil.createRetrofit();
        final ApiService apiService = retrofit.create(ApiService.class);

        apiInteractor = new ApiInteractorImpl(apiService);

        filePath = getFilesDir().getPath() + Constants.FILE_NAME;
        file = new File(filePath);

    }

    public static ApiInteractor getApiInteractor(){
        return apiInteractor;
    }

    public static String getFilePath() {
        return filePath;
    }
}
