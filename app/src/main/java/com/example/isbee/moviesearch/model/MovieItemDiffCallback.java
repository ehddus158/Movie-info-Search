package com.example.isbee.moviesearch.model;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class MovieItemDiffCallback extends DiffUtil.Callback {

    private final List<MovieItem> oldMovieItems, newMovieItems;

    public MovieItemDiffCallback(List<MovieItem> oldMovieItems, List<MovieItem> newMovieItems) {
        this.oldMovieItems = oldMovieItems;
        this.newMovieItems = newMovieItems;
    }

    @Override
    public int getOldListSize() {
        return oldMovieItems.size();
    }

    @Override
    public int getNewListSize() {
        return newMovieItems.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldMovieItems.get(oldItemPosition).getTitle() == newMovieItems.get(newItemPosition).getTitle();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldMovieItems.get(oldItemPosition).equals(newMovieItems.get(newItemPosition));
    }
}
