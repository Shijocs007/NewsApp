package com.shijo.mvpexample.newslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.shijo.mvpexample.R;
import com.shijo.mvpexample.http.apimodel.Article;
import com.shijo.mvpexample.newsdetails.NewsDetailsActivity;
import com.shijo.mvpexample.root.NewsAppActivity;
import com.shijo.mvpexample.root.NewsApplication;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NewsListActivity extends NewsAppActivity implements NewsListMVP.View, IAdapterListener{

    private RecyclerView recyclerView;
    private NewsListAdapter mAdapter;
    List<Article> articles = new ArrayList<>();

    @Inject
    NewsListMVP.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((NewsApplication) getApplication()).getComponent().inject(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        List<Article> articles = new ArrayList<>();
        mAdapter = new NewsListAdapter(articles, this, this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void updateData(Article article) {
        mAdapter.setItemAtPos(article);
    }

    @Override
    public void showSnackbar(String s) {

    }

    @Override
    public void showProgressDialog() {
        showProgress("Loading...");
    }

    @Override
    public void dismissProgressDialog() {
        dismissProgress();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.rxUnsubscribe();
        articles.clear();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClicked(Article article) {
        Intent intent = new Intent(this, NewsDetailsActivity.class);
        intent.putExtra("image", article.getUrlToImage());
        intent.putExtra("title", article.getTitle());
        intent.putExtra("details", article.getDescription());
        startActivity(intent);
    }
}
