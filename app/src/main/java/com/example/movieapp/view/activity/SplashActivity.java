package com.example.movieapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.movieapp.ViewInterface.SplashActivityView;
import com.example.movieapp.R;

public class SplashActivity extends AppCompatActivity implements SplashActivityView {
    private Handler mWaitHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        LoadMainActivity();
    }

    @Override
    public void LoadMainActivity() {
        mWaitHandler.postDelayed(() -> {
            try {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }, 1000);
    }

}
