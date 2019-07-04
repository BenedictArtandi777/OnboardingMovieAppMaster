package com.example.movieapp;

import android.app.Application;

import com.example.movieapp.di.component.ApplicationComponent;
import com.example.movieapp.di.component.DaggerApplicationComponent;
import com.example.movieapp.di.module.ApplicationModule;

public class MainApp extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
