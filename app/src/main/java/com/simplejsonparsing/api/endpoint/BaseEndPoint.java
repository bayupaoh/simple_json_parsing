package com.simplejsonparsing.api.endpoint;

import com.simplejsonparsing.util.Constant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by irfan on 04/01/17.
 */

public class BaseEndPoint {

    public static Retrofit getEndPoint() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return mRetrofit;
    }
}
