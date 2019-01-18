package com.example.isbee.moviesearch.view;

import com.example.isbee.moviesearch.databinding.ItemMovieBinding;
import com.example.isbee.moviesearch.model.MovieItem;

import com.example.isbee.moviesearch.R;
import com.example.isbee.moviesearch.util.Pair;
import com.example.isbee.moviesearch.viewmodel.MovieViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;
import java.util.List;

import android.view.ViewGroup;
import android.view.LayoutInflater;


public class MovieItemAdapter extends RecyclerView.Adapter<MovieItemAdapter.ViewHolder> {

    private List<MovieItem> movieItems;
    private MovieViewModel movieViewModel;

    public MovieItemAdapter(MovieViewModel movieViewModel) {
        this.movieViewModel = movieViewModel;
        movieItems = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMovieBinding itemMovieBinding = DataBindingUtil.inflate(inflater, R.layout.item_movie, parent, false);

        setMovieViewModel(itemMovieBinding);

        return new ViewHolder(itemMovieBinding);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        movieItems = movieViewModel.getMoviePair().getValue().second;
        MovieItem movieItem = movieItems.get(position);
        holder.bindMovieItem(movieItem);
    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }

    private void setMovieViewModel(ItemMovieBinding itemMovieBinding) {
        itemMovieBinding.setMovieViewModel(movieViewModel);
    }

    public void updateMovieItems(Pair<DiffUtil.DiffResult, List<MovieItem>> pair) {
        if (this.movieItems != null) {
            this.movieItems.clear();
            this.movieItems.addAll(pair.second);
            pair.first.dispatchUpdatesTo(this);
        }
        else this.movieItems = pair.second;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemMovieBinding itemMovieBinding;

        public ViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.itemMovieBinding = binding;
        }

        public void bindMovieItem(MovieItem item) {
            itemMovieBinding.setMovieItem(item);
        }
    }
}
