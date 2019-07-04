package com.example.movieapp.di.component;

import android.app.Activity;

import com.example.movieapp.di.annotation.PerActivity;
import com.example.movieapp.di.module.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
