package com.shijo.mvpexample.newslist;


import com.shijo.mvpexample.http.NewsApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsListModule {

    @Provides
    NewsListMVP.Presenter provideNewsListMVPPresenter(NewsListMVP.Model model) {
        return new NewsListPresenter(model);
    }

    @Provides
    NewsListMVP.Model provideNewsListMVPModel(Repository repository) {
        return new NewsListModel(repository);
    }

    @Provides
    Repository provideRepository(NewsApiService newsApiService){
        return new NewsRepository(newsApiService);
    }
}
