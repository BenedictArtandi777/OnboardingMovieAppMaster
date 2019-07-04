package com.example.movieapp;

import com.example.domain.executor.PostExecutionThread;
import javax.inject.Inject;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class UIThread implements PostExecutionThread {
    @Inject
    public UIThread() {
    }


    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
