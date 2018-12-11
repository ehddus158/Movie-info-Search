package com.example.isbee.moviesearch.viewmodel;

import com.example.isbee.moviesearch.model.Repository;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

@SuppressWarnings("UNCHECKED_CAST")
public final class MovieViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repo;

    public MovieViewModelFactory(Repository repo) {
        this.repo = repo;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new MovieViewModel(repo);
    }
}
