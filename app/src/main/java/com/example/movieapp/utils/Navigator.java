package com.example.movieapp.utils;

import android.content.Context;
import android.content.Intent;

import com.example.movieapp.view.activity.DetailActivity;

import javax.inject.Inject;

public class Navigator {
    @Inject
    public Navigator() {

    }

    public void navigateToDetailMovie(Context context, String movieId) {
        if (context != null) {
            Intent intentToLaunch = DetailActivity.getCallingIntent(context, movieId);
            intentToLaunch.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentToLaunch);
        }
    }
}
