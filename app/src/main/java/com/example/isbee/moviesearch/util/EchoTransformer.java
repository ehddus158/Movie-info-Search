package com.example.isbee.moviesearch.util;

import io.reactivex.SingleTransformer;
import io.reactivex.SingleSource;
import io.reactivex.Single;

public class EchoTransformer<T> implements SingleTransformer<T, Pair<T, T>> {
    private T lastValue;

    public EchoTransformer(T initValue) {
        lastValue = initValue;
    }

    @Override
    public SingleSource<Pair<T, T>> apply(Single<T> upstream) {
        return upstream.map(newValue -> {
            Pair<T, T> result = Pair.of(lastValue, newValue);
            lastValue = newValue;
            return result;
        });
    }
}
