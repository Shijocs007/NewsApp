package com.shijo.mvpexample.newslist;


import com.shijo.mvpexample.http.NewsApiService;
import com.shijo.mvpexample.http.apimodel.Article;
import com.shijo.mvpexample.http.apimodel.Source;
import com.shijo.mvpexample.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class NewsRepository implements Repository{

    NewsApiService newsApiService;
    List<Article> articles;

    public NewsRepository(NewsApiService newsApiService) {
        this.newsApiService = newsApiService;
        this.articles = new ArrayList<>();
    }

    @Override
    public Observable<Article> getNewsData() {

        Map<String, String> params = new HashMap<>();
        params.put("q", "manchester united");
        params.put("from", new Util().getTodayDate());
        params.put("sortBy", "publishedAt");
        params.put("language", "en");

        Observable<Source> newsObservable = newsApiService.getNews(params);

        return newsObservable.concatMap(new Function<Source, Observable<Article>>() {
            @Override
            public Observable<Article> apply(Source source) throws Exception {
                return Observable.fromIterable(source.getArticles());
            }
        }).doOnNext(new Consumer<Article>() {
            @Override
            public void accept(Article article) throws Exception {
                articles.add(article);
            }
        });
    }
}
