package com.example.movieapp.di.module;

import android.content.Context;

import com.example.data.db.FavouriteMovieEntityRepository;
import com.example.data.entity.MovieEntity;
import com.example.data.executor.JobExecutor;
import com.example.data.repository.datasource.MovieEntityRepository;
import com.example.domain.executor.PostExecutionThread;
import com.example.domain.executor.ThreadExecutor;
import com.example.domain.repository.FavouriteMovieRepository;
import com.example.domain.repository.MovieRepository;
import com.example.movieapp.MainApp;
import com.example.movieapp.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final MainApp application;

    public ApplicationModule(MainApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {

        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    MovieRepository providesMovieRepository(MovieEntityRepository movieEntityRepository){
        return movieEntityRepository;
    }

    @Provides
    @Singleton
    FavouriteMovieRepository providesFavouriteMovieRepository(FavouriteMovieEntityRepository favouriteMovieEntityRepository){
        return favouriteMovieEntityRepository;
    }

}
