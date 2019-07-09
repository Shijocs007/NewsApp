package com.shijo.mvpexample.newslist;


import com.shijo.mvpexample.http.NewsApiModule;
import com.shijo.mvpexample.http.NewsApiService;
import com.shijo.mvpexample.http.apimodel.Article;
import com.shijo.mvpexample.http.apimodel.Source;

import java.util.ArrayList;
import java.util.Calendar;
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

        Calendar c= Calendar.getInstance();
        int cyear = c.get(Calendar.YEAR);
        int cmonth = c.get(Calendar.MONTH) + 1;
        int cday = c.get(Calendar.DAY_OF_MONTH);
        String date = "";
        if(cmonth<10 && cday < 10) {
            date = cyear +"-0"+cmonth+"-0"+cday;
        } else if(cmonth < 10) {
            date = cyear +"-0"+cmonth+"-"+cday;
        } else if(cday < 10) {
            date = cyear +"-"+cmonth+"-0"+cday;
        } else {
            date = cyear +"-"+cmonth+"-"+cday;
        }


        Map<String, String> params = new HashMap<>();
        params.put("q", "cricket");
        params.put("from",cyear +"-"+cmonth+"-"+cday);
        params.put("sortBy", "publishedAt");
        params.put("apiKey", NewsApiModule.API_KEY);

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
