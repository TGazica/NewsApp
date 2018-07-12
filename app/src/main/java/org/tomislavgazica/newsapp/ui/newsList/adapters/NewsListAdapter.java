package org.tomislavgazica.newsapp.ui.newsList.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.tomislavgazica.newsapp.R;
import org.tomislavgazica.newsapp.model.Article;
import org.tomislavgazica.newsapp.ui.newsList.listeners.OnArticleClickListener;
import org.tomislavgazica.newsapp.ui.newsList.viewHolders.NewsListViewHolder;

import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListViewHolder> {

    private List<Article> articles;
    private OnArticleClickListener listener;

    public NewsListAdapter(OnArticleClickListener listener){
        articles = new ArrayList<>();
        this.listener = listener;
    }

    public void updateArticlesList(List<Article> articles){
        this.articles.clear();
        this.articles.addAll(articles);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list, parent, false);
        return new NewsListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.setListener(listener);
        holder.setArticle(article);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
