package com.example.isbee.moviesearch.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import com.example.isbee.moviesearch.model.MovieResponse;

import io.reactivex.Single;

public interface NaverSearchAPI {
    interface MovieAPI {
        @GET("v1/search/movie.json")
        Single<MovieResponse> movieRequest(@Query("query") String title);
    }
}
