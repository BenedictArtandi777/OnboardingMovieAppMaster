package com.example.movieapp.view.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.movieapp.MainApp;
import com.example.movieapp.di.component.ApplicationComponent;
import com.example.movieapp.di.module.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * Get the Main Application Component for Dependency Injection
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((MainApp) getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity Module for Dependency Injection
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
