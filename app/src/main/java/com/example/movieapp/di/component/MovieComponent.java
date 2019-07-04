package com.example.movieapp.di.component;

import com.example.movieapp.di.annotation.PerActivity;
import com.example.movieapp.di.module.ActivityModule;
import com.example.movieapp.view.fragment.DetailMovieFragment;
import com.example.movieapp.view.fragment.FavouriteMovieFragment;
import com.example.movieapp.view.fragment.PopularMovieFragment;
import com.example.movieapp.view.fragment.TopRatedMovieFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface MovieComponent {
    void inject(PopularMovieFragment popularMovieFragment);

    void inject(TopRatedMovieFragment topRatedMovieFragment);

    void inject(FavouriteMovieFragment favoriteMovieFragment);

    void inject(DetailMovieFragment detailMovieFragment);
}
