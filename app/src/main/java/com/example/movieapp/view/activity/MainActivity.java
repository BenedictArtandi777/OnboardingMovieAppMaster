package com.example.movieapp.view.activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.movieapp.ViewInterface.MainActivityView;
import com.example.movieapp.R;
import com.example.movieapp.di.HasComponent;
import com.example.movieapp.di.component.DaggerMovieComponent;
import com.example.movieapp.di.component.MovieComponent;
import com.example.movieapp.view.fragment.FavouriteMovieFragment;
import com.example.movieapp.view.fragment.PopularMovieFragment;
import com.example.movieapp.view.fragment.TopRatedMovieFragment;

public class MainActivity extends BaseActivity implements HasComponent<MovieComponent>,MainActivityView {

    Fragment fragment = null;
    private MovieComponent movieComponent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeInjector();
        this.initializeView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_popular:
                fragment = new PopularMovieFragment();
                break;
            case R.id.action_top_rated:
                fragment = new TopRatedMovieFragment();
                break;
            case R.id.action_favorite:
                fragment = new FavouriteMovieFragment();
                break;
            default:
                break;
        }
        if(fragment!= null){
            loadFragment(fragment);
        }
        return true;
    }

    @Override
    public void initializeView() {
        fragment = new PopularMovieFragment();
        loadFragment(fragment);
    }

    private void initializeInjector() {
        this.movieComponent = DaggerMovieComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    public void setTitle(Fragment fragment){
        if (fragment instanceof PopularMovieFragment) {
            this.getSupportActionBar().setTitle(R.string.popular_movie_title);
        } else if (fragment instanceof TopRatedMovieFragment) {
            this.getSupportActionBar().setTitle(R.string.top_rated_movie_title);
        } else if (fragment instanceof FavouriteMovieFragment) {
            this.getSupportActionBar().setTitle(R.string.favourite_movie_title);
        }
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in,
                            android.R.anim.fade_out)
                    .replace(R.id.main_container, fragment)
                    .addToBackStack(null)
                    .commit();

            setTitle(fragment);
            return true;
        }
        return false;
    }

    @Override
    public MovieComponent getComponent() {
        return movieComponent;
    }
}
