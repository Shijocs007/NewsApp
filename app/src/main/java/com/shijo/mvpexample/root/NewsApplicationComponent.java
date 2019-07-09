package com.shijo.mvpexample.root;

import com.shijo.mvpexample.http.NewsApiModule;
import com.shijo.mvpexample.newslist.NewsListActivity;
import com.shijo.mvpexample.newslist.NewsListModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NewsApplicationModule.class, NewsApiModule.class, NewsListModule.class})
public interface NewsApplicationComponent {
    void inject(NewsListActivity target);
}
