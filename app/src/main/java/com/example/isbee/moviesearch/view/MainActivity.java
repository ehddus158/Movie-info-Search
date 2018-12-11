package com.example.isbee.moviesearch.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.example.isbee.moviesearch.R;
import com.example.isbee.moviesearch.api.NaverSearchAPI;
import com.example.isbee.moviesearch.api.NaverSearchClientSingleton;
import com.example.isbee.moviesearch.model.MovieItem;
import com.example.isbee.moviesearch.model.NetworkRepositoryImpl;
import com.example.isbee.moviesearch.model.Repository;
import com.example.isbee.moviesearch.viewmodel.MovieViewModel;
import com.example.isbee.moviesearch.viewmodel.MovieViewModelFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 의존성 주입
        NaverSearchAPI.MovieAPI nsApi = NaverSearchClientSingleton.getInstance().getService();
        Repository repo = new NetworkRepositoryImpl(nsApi);
        ViewModelProvider.Factory factory = new MovieViewModelFactory(repo);

        movieViewModel = ViewModelProviders.of(this, factory).get(MovieViewModel.class);
        movieViewModel.getMovieResponse("avengers");
        Log.d("main","hi");

        /*
        val shortenUrlViewModel = ViewModelProviders.of(this, shortenUrlViewModelFactory).get(ShortenUrlViewModel::class.java)

                // clickConvert = SingleLiveEvent<Any>()
        shortenUrlViewModel.clickConvert.observe(this, Observer {
            shortenUrlViewModel.getShortenUrl(viewDataBinding.urlEditText.text.toString())
        })

                // error = MutableLiveData<String>()
        shortenUrlViewModel.error.observe(this, Observer<String> { t ->
                Toast.makeText(this@ShortenUrlActivity, t, Toast.LENGTH_LONG).show()
        })
        */
    }
}
