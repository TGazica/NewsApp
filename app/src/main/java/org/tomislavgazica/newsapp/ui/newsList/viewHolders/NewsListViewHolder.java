package org.tomislavgazica.newsapp.ui.newsList.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.tomislavgazica.newsapp.R;
import org.tomislavgazica.newsapp.model.Article;
import org.tomislavgazica.newsapp.ui.newsList.listeners.OnArticleClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class NewsListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.itemImage)
    ImageView itemImage;
    @BindView(R.id.itemTitle)
    TextView itemTitle;

    private Article article;
    private OnArticleClickListener listener;

    public NewsListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setListener(OnArticleClickListener listener){
        this.listener = listener;
    }

    public void setArticle(Article article){
        this.article = article;

        if (this.article != null){

            itemTitle.setText(this.article.getTitle());

            Picasso.get().load(this.article.getUrlToImage()).fit().into(itemImage);

        }

    }

    @OnClick
    public void onArticleClick(){
        listener.onArticleClick(this.article);
    }

}
