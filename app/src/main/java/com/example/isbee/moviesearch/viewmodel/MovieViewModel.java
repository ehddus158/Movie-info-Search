package com.example.isbee.moviesearch.viewmodel;

import com.example.isbee.moviesearch.model.MovieItem;
import com.example.isbee.moviesearch.model.Repository;
import com.example.isbee.moviesearch.model.MovieItemDiffCallback;
import com.example.isbee.moviesearch.util.SingleLiveEvent;
import com.example.isbee.moviesearch.util.EchoTransformer;
import com.example.isbee.moviesearch.util.Pair;
import com.example.isbee.moviesearch.view.WebActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.DiffUtil;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.List;
import java.util.Collections;

public final class MovieViewModel extends DisposableViewModel {

    private final Repository networkRepo;

    private final MutableLiveData<Pair<DiffUtil.DiffResult, List<MovieItem>>> moviePair;
    private final SingleLiveEvent<Object> searchClickEvent;
    private final MutableLiveData<String> errorMessage;

    private List<MovieItem> movieItems;

    public MovieViewModel(Repository networkRepo) {
        this.networkRepo = networkRepo;

        this.moviePair = new MutableLiveData<>();
        this.searchClickEvent = new SingleLiveEvent<>();
        this.errorMessage = new MutableLiveData<>();
        this.movieItems = Collections.emptyList();
    }

    public void getMovieResponse(String title) {
        addDisposable(networkRepo.getMovieItems(title)
                .compose(new EchoTransformer<>(movieItems))
                .map(MovieItemDiffCallback::new)
                .map(callback -> Pair.of(DiffUtil.calculateDiff(callback), callback.getNewMovieItems()) )
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            movieItems = result.second;
                            moviePair.setValue(Pair.of(result.first, result.second));
                        }, throwable ->
                    errorMessage.setValue(throwable.getMessage())
                ));
    }

    public LiveData<Pair<DiffUtil.DiffResult, List<MovieItem>>> getMoviePair() {
        return moviePair;
    }

    public LiveData<Object> getSearchClickEvent() {
        return searchClickEvent;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void onClickSearchButton(View view) {
        searchClickEvent.call();
        hideSoftKey(view);
    }

    public void onClickCardView(View view, String link) {
        Context context = view.getContext();
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("link", link);
        context.startActivity(intent);
    }

    private void hideSoftKey(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
