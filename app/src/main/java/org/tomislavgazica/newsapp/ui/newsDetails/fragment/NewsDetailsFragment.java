package org.tomislavgazica.newsapp.ui.newsDetails.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import org.tomislavgazica.newsapp.Constants;
import org.tomislavgazica.newsapp.R;
import org.tomislavgazica.newsapp.model.Article;
import org.tomislavgazica.newsapp.presentation.NewsDetailFragmentPresenter;
import org.tomislavgazica.newsapp.ui.errorDialog.CustomErrorDialogFragment;
import org.tomislavgazica.newsapp.ui.errorDialog.OnCustomDialogFragmentClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailsFragment extends Fragment implements NewsDetailsFragmentContract.View, OnCustomDialogFragmentClickListener {

    @BindView(R.id.pageContent)
    WebView newsContent;
    @BindView(R.id.pageProgressBar)
    ProgressBar progressBar;

    private Article article;
    private NewsDetailsFragmentContract.Presenter presenter;

    public static NewsDetailsFragment newInstance(Article article) {
        Bundle data = new Bundle();
        data.putSerializable(Constants.ARTICLE_BUNDLE_KEY, article);
        NewsDetailsFragment fragment = new NewsDetailsFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        progressBar.setMax(100);

        if (getArguments() != null) {
            article = (Article) getArguments().getSerializable(Constants.ARTICLE_BUNDLE_KEY);
        }

        presenter = new NewsDetailFragmentPresenter();
        presenter.setView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isAdded() && getActivity() != null){
            refreshCurrentData();
        }
    }

    private void refreshCurrentData() {
        presenter.getNewsData(article);
    }

    @Override
    public void setContent(final String contentUrl) {
        newsContent.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100){
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setProgress(newProgress);
                }
            }
        });

        newsContent.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                onGetDataFailure();
            }
        });
        newsContent.loadUrl(contentUrl);
    }

    @Override
    public void onGetDataFailure() {
        FragmentManager fragmentManager = getChildFragmentManager();
        CustomErrorDialogFragment dialogFragment = new CustomErrorDialogFragment();
        dialogFragment.setListener(this);
        dialogFragment.show(fragmentManager, "Error dilaog");
    }

    @Override
    public void tryAgain() {
        presenter.getNewsData(article);
    }
}
