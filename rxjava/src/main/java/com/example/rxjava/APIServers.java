package com.example.rxjava;


import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by T_baby on 17/11/03.
 */

public interface APIServers {
    @GET("wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10&page=1")
    Observable<Supers> getNoParams();
}
