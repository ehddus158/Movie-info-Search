package com.example.isbee.moviesearch.view;

import com.example.isbee.moviesearch.databinding.ItemMovieBinding;
import com.example.isbee.moviesearch.model.MovieItem;

import com.example.isbee.moviesearch.R;
import com.example.isbee.moviesearch.viewmodel.MovieViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.ViewGroup;
import android.view.LayoutInflater;


public class MovieItemAdapter extends RecyclerView.Adapter<MovieItemAdapter.ViewHolder> {

    private List<MovieItem> movieItems; // 데이터 바인딩으로 activity_main.xml 에 저장된 데이터를
                                           // 이 변수에 할당. 뷰홀더는 list내에 각 아이템을 item_movie.xml 에 할당.
                                           // 해당 아이템으로 이미지뷰/텍스트뷰 에 최종적인 바인딩 수행.
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
        Log.d("onBindViewHolder", String.valueOf(position));
        movieItems = movieViewModel.getMovieItems().getValue();
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

    public void setMovieItems(List<MovieItem> movieItems) {
        if (this.movieItems != null) {
            this.movieItems.clear();
            this.movieItems.addAll(movieItems);
        }
        else this.movieItems = movieItems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

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
