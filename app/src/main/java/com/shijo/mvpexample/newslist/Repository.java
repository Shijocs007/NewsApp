package com.shijo.mvpexample.newslist;



import com.shijo.mvpexample.http.apimodel.Article;

import io.reactivex.Observable;

public interface Repository {
    Observable<Article> getNewsData();
}
