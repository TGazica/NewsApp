package org.tomislavgazica.newsapp.ui.newsDetails.fragment;


import org.tomislavgazica.newsapp.model.Article;

public interface NewsDetailsFragmentContract {

    interface View{

        void setContent(String contentUrl);

        void onGetDataFailure();

    }

    interface Presenter{

        void setView(NewsDetailsFragmentContract.View view);

        void getNewsData(Article article);

    }

}
