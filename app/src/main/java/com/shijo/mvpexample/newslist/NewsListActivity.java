package com.shijo.mvpexample.newslist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.shijo.mvpexample.R;
import com.shijo.mvpexample.http.apimodel.Article;
import com.shijo.mvpexample.root.NewsApplication;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NewsListActivity extends AppCompatActivity implements NewsListMVP.View{

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
        mAdapter = new NewsListAdapter(articles, this);
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
}
