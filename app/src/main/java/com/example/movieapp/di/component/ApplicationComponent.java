package com.example.movieapp.di.component;


import android.content.Context;

import com.example.domain.executor.PostExecutionThread;
import com.example.domain.executor.ThreadExecutor;
import com.example.domain.repository.FavouriteMovieRepository;
import com.example.domain.repository.MovieRepository;
import com.example.movieapp.di.module.ApplicationModule;
import com.example.movieapp.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    MovieRepository movieRepository();

    FavouriteMovieRepository favouriteMovieRepository();
//
//    FavoriteMovieRepository favoriteMovieRepository();
}
