package org.tomislavgazica.newsapp.ui.newsList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


import org.tomislavgazica.newsapp.App;
import org.tomislavgazica.newsapp.Constants;
import org.tomislavgazica.newsapp.R;
import org.tomislavgazica.newsapp.model.Article;
import org.tomislavgazica.newsapp.presentation.NewsListPresenter;
import org.tomislavgazica.newsapp.ui.newsDetails.NewsDetailsActivity;
import org.tomislavgazica.newsapp.ui.newsList.adapters.NewsListAdapter;
import org.tomislavgazica.newsapp.ui.newsList.listeners.OnArticleClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListActivity extends AppCompatActivity implements NewsListContract.View, OnArticleClickListener {

    @BindView(R.id.newsListHolder)
    RecyclerView newsListHolder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private NewsListAdapter adapter;
    private NewsListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        ButterKnife.bind(this);

        presenter = new NewsListPresenter(App.getApiInteractor(), this);
        presenter.setView(this);
        initToolbar();

        initNewsListHolder();
        presenter.getArticles();
    }

    private void initNewsListHolder() {
        adapter = new NewsListAdapter(this);

        newsListHolder.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        newsListHolder.setItemAnimator(new DefaultItemAnimator());
        newsListHolder.setAdapter(adapter);
    }

    @Override
    public void updateArticlesList(List<Article> articles) {
        adapter.updateArticlesList(articles);
    }

    @Override
    public void onNoNetworkConnection() {
        Toast.makeText(getApplicationContext(), "No network connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onArticleClick(Article article) {
        Intent intent = new Intent(this, NewsDetailsActivity.class);
        intent.putExtra(Constants.PASSED_ARTICLE_KEY, article);
        startActivity(intent);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
    }

}
