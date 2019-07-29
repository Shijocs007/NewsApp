package com.shijo.mvpexample.newslist;


import com.shijo.mvpexample.http.apimodel.Article;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class NewsListPresenter implements NewsListMVP.Presenter{

    NewsListMVP.View view;
    NewsListMVP.Model model;
    Disposable disposable;

    public NewsListPresenter(NewsListMVP.Model model) {
        this.model = model;
    }

    @Override
    public void loadData() {
        view.showProgressDialog();
        disposable = model.result()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Article>(){

                    @Override
                    public void onNext(Article news) {
                        if (view != null) {
                            view.updateData(news);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.dismissProgressDialog();
                            view.showSnackbar("Error getting movies");
                        }
                    }

                    @Override
                    public void onComplete() {
                        view.dismissProgressDialog();
                    }
                });
    }

    @Override
    public void rxUnsubscribe() {

        if (disposable != null) {
            if (!disposable.isDisposed()) {
                disposable.dispose();
            }
        }
    }

    @Override
    public void setView(NewsListMVP.View view) {
        this.view = view;
    }
}
