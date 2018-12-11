package com.example.isbee.moviesearch.model;

import com.example.isbee.moviesearch.api.NaverSearchAPI;

import io.reactivex.Single;
import java.util.List;

public final class NetworkRepositoryImpl implements Repository {

    private final NaverSearchAPI.MovieAPI nsApi;

    public NetworkRepositoryImpl(NaverSearchAPI.MovieAPI api) {
        nsApi = api;
    }

    @Override
    public Single<List<MovieItem>> getMovieItems(String title) {
        return nsApi.movieRequest(title)
                .map(movieResponse -> movieResponse.getItems());
    }
}
