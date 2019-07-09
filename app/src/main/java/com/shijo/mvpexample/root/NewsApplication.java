package com.shijo.mvpexample.root;

import android.app.Application;

import com.shijo.mvpexample.http.NewsApiModule;
import com.shijo.mvpexample.newslist.NewsListModule;


public class NewsApplication extends Application {

    private NewsApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerNewsApplicationComponent.builder()
                .newsApplicationModule(new NewsApplicationModule(this))
                .newsApiModule(new NewsApiModule())
                .newsListModule(new NewsListModule())
                .build();

    }

    public NewsApplicationComponent getComponent() {
        return component;
    }
}
