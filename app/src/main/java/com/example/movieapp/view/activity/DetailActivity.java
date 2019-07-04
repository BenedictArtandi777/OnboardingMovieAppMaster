package com.example.movieapp.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.di.HasComponent;
import com.example.movieapp.di.component.DaggerMovieComponent;
import com.example.movieapp.di.component.MovieComponent;
import com.example.movieapp.view.fragment.DetailMovieFragment;

import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity implements HasComponent<MovieComponent> {

    private MovieComponent movieComponent;
    private String movieId;

    public static Intent getCallingIntent(Context context, String movieId) {
        Intent callingIntent = new Intent(context, DetailActivity.class);
        callingIntent.putExtra("MovieId", movieId);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initializeActivity(savedInstanceState);
        initializeInjector();
        ButterKnife.bind(this);
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            movieId = getIntent().getStringExtra("MovieId");
            addFragment(R.id.movie_detail_container, DetailMovieFragment.newInstance(movieId));
        }
    }

    private void initializeInjector() {
        movieComponent = DaggerMovieComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("MovieId",movieId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setTitle(R.string.detail);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public MovieComponent getComponent() {
        return movieComponent;
    }
}
