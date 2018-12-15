package com.example.isbee.moviesearch.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.databinding.DataBindingUtil;

import android.widget.Toast;

import android.os.Bundle;

import com.example.isbee.moviesearch.R;
import com.example.isbee.moviesearch.api.NaverSearchAPI;
import com.example.isbee.moviesearch.api.NaverSearchClientSingleton;
import com.example.isbee.moviesearch.databinding.ActivityMainBinding;
import com.example.isbee.moviesearch.model.NetworkRepositoryImpl;
import com.example.isbee.moviesearch.model.Repository;
import com.example.isbee.moviesearch.viewmodel.MovieViewModel;
import com.example.isbee.moviesearch.viewmodel.MovieViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private MovieViewModel movieViewModel;
    private ActivityMainBinding viewDataBinding;

    private MovieItemAdapter movieItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        movieViewModel = ViewModelProviders.of(this, getViewModelFactoryWithDI()).get(MovieViewModel.class);
        movieItemAdapter = new MovieItemAdapter(movieViewModel);

        basicSetUp();

        movieViewModel.getSearchClickEvent().observe(this, event ->
                movieViewModel.getMovieResponse(viewDataBinding.query.getText().toString())
        );

        movieViewModel.getMovieItems().observe(this, movieItems -> {
                movieItemAdapter.setMovieItems(movieItems);
        });

        movieViewModel.getErrorMessage().observe(this, errorMessage ->
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        );

    }

    private ViewModelProvider.Factory getViewModelFactoryWithDI() {
        NaverSearchAPI.MovieAPI nsApi = NaverSearchClientSingleton.getInstance().getService();
        Repository repo = new NetworkRepositoryImpl(nsApi);
        ViewModelProvider.Factory factory = new MovieViewModelFactory(repo);

        return factory;
    }

    private void basicSetUp() {
        viewDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewDataBinding.recyclerView.setAdapter(movieItemAdapter);

        viewDataBinding.setMovieViewModel(movieViewModel);
        viewDataBinding.setLifecycleOwner(this);
    }
}
