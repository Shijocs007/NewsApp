package com.shijo.mvpexample.newslist;


import com.shijo.mvpexample.http.apimodel.Article;

import io.reactivex.Observable;

public class NewsListModel implements NewsListMVP.Model{

    Repository repository;

    public NewsListModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<Article> result() {
        return repository.getNewsData();
    }
}
