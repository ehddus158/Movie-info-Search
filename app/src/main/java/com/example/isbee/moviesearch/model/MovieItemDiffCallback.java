package com.example.isbee.moviesearch.model;

import androidx.recyclerview.widget.DiffUtil;

import com.example.isbee.moviesearch.util.Pair;

import java.util.List;

public class MovieItemDiffCallback extends DiffUtil.Callback {

    private final List<MovieItem> oldMovieItems, newMovieItems;

    public MovieItemDiffCallback(Pair<List<MovieItem>, List<MovieItem>> pair) {
        this.oldMovieItems = pair.first;
        this.newMovieItems = pair.second;
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
        return oldMovieItems.get(oldItemPosition).getTitle().equals(newMovieItems.get(newItemPosition).getTitle());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldMovieItems.get(oldItemPosition).equals(newMovieItems.get(newItemPosition));
    }

    public List<MovieItem> getOldMovieItems() { return oldMovieItems; }

    public List<MovieItem> getNewMovieItems() { return newMovieItems; }
}
