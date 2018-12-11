package com.example.isbee.moviesearch.viewmodel;

import com.example.isbee.moviesearch.model.Repository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import android.util.Log;

public final class MovieViewModel extends DisposableViewModel {

    private final Repository networkRepo;

    public MovieViewModel(Repository networkRepo) {
        this.networkRepo = networkRepo;
    }

    public void getMovieResponse(String title) {
        addDisposable(networkRepo.getMovieItems(title)
                .subscribeOn(Schedulers.io()) // Observable is called outside the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(channel -> {
                    // onSuccess 에서 해야할 것
                    // 필요하면 HTTP status code 처리

                    // LiveData 로 데이터 저장
                    Log.d("Get the Fuck Item", channel.get(0).getActor());

                }, throwable -> {
                    // onError 에서 해야할 것
                }));
    }
}
