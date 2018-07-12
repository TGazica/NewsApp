package org.tomislavgazica.newsapp.ui.newsDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.tomislavgazica.newsapp.Constants;
import org.tomislavgazica.newsapp.R;
import org.tomislavgazica.newsapp.model.Article;
import org.tomislavgazica.newsapp.presentation.NewsDetailPresenter;
import org.tomislavgazica.newsapp.ui.newsDetails.adapters.CustomPagerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailsActivity extends AppCompatActivity implements NewsDetailsContract.View{

    @BindView(R.id.articlesViewPager)
    ViewPager articlesViewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private CustomPagerAdapter adapter;
    private NewsDetailsContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        Article article = (Article) getIntent().getSerializableExtra(Constants.PASSED_ARTICLE_KEY);

        initUi();
        initToolbar(article.getTitle());

        adapter = new CustomPagerAdapter(getSupportFragmentManager());
        articlesViewPager.setAdapter(adapter);

        articlesViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String title = presenter.getArticleTitle(position);
                toolbar.setTitle(title);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        presenter = new NewsDetailPresenter();
        presenter.setView(this);
        presenter.getArticles(article);
    }

    private void initToolbar(String title) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUi() {
        if (articlesViewPager != null){
            articlesViewPager.setOffscreenPageLimit(10);
        }
    }

    @Override
    public void updateArticleList(List<Article> articles, int selectedArticle) {
        adapter.setArticles(articles);
        articlesViewPager.setCurrentItem(selectedArticle);
    }
}
