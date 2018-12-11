package com.example.isbee.moviesearch.model;

import io.reactivex.Single;
import java.util.List;

public interface Repository {
    public Single<List<MovieItem>> getMovieItems(String title);
}
