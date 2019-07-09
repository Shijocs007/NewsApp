package com.shijo.mvpexample.http;



import com.shijo.mvpexample.http.apimodel.Source;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface NewsApiService {

    @GET("v2/everything")
    Observable<Source>  getNews(@QueryMap Map<String, String> params);
}
