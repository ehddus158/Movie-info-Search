package com.example.isbee.moviesearch.viewmodel;

import com.example.isbee.moviesearch.model.MovieItem;
import com.example.isbee.moviesearch.model.Repository;
import com.example.isbee.moviesearch.util.SingleLiveEvent;
import com.example.isbee.moviesearch.view.WebActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

public final class MovieViewModel extends DisposableViewModel {

    private final Repository networkRepo;

    private final MutableLiveData<List<MovieItem>> movieItems;
    private final SingleLiveEvent<Object> searchClickEvent;
    private final MutableLiveData<String> errorMessage;

    public MovieViewModel(Repository networkRepo) {
        this.networkRepo = networkRepo;

        this.movieItems = new MutableLiveData<>();
        this.searchClickEvent = new SingleLiveEvent<>();
        this.errorMessage = new MutableLiveData<>();
    }

    public void getMovieResponse(String title) {
        addDisposable(networkRepo.getMovieItems(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(items ->
                    movieItems.setValue(items)
                , throwable ->
                    errorMessage.setValue(throwable.getMessage())
                ));
    }

    public LiveData<List<MovieItem>> getMovieItems() {
        return movieItems;
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
