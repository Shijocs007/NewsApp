package com.shijo.mvpexample.newslist;

import com.shijo.mvpexample.http.apimodel.Article;

public interface IAdapterListener {

    public void onItemClicked(Article article);
}
