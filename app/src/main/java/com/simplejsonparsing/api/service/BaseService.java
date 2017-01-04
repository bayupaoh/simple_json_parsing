package com.simplejsonparsing.api.service;

import com.simplejsonparsing.api.dao.MainDao;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by irfan on 04/01/17.
 */

public interface BaseService {
    @GET("/3/discover/movie?api_key=1b2f29d43bf2e4f3142530bc6929d341&sort_by=popularity.desc")
    Observable<MainDao> getMovieList();
}
