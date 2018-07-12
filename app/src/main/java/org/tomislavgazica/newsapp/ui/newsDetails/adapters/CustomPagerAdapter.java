package org.tomislavgazica.newsapp.ui.newsDetails.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.tomislavgazica.newsapp.model.Article;
import org.tomislavgazica.newsapp.ui.newsDetails.fragment.NewsDetailsFragment;

import java.util.ArrayList;
import java.util.List;

public class CustomPagerAdapter extends FragmentPagerAdapter {

    private final List<Article> articles = new ArrayList<>();

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setArticles(List<Article> articles){
        this.articles.clear();
        this.articles.addAll(articles);
        notifyDataSetChanged();
    }



    @Override
    public Fragment getItem(int position) {
        return NewsDetailsFragment.newInstance(articles.get(position));
    }

    @Override
    public int getCount() {
        return articles.size();
    }
}
