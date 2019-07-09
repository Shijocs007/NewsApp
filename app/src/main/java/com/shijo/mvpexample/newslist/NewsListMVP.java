package com.shijo.mvpexample.newslist;


import com.shijo.mvpexample.http.apimodel.Article;

import io.reactivex.Observable;

public interface NewsListMVP {

    interface View {

        void updateData(Article news);

        void showSnackbar(String s);

    }

    interface Presenter {

        void loadData();

        void rxUnsubscribe();

        void setView(NewsListMVP.View view);

    }

    interface Model {

        Observable<Article> result();

    }
}
