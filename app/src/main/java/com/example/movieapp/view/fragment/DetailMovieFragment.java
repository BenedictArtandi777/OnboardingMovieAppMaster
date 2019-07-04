package com.example.movieapp.view.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.domain.model.MovieDetail;
import com.example.movieapp.Presenter.MovieDetailPresenter;
import com.example.movieapp.R;
import com.example.movieapp.ViewInterface.MovieDetailView;
import com.example.movieapp.di.component.MovieComponent;
import com.example.movieapp.model.MovieDetailModel;
import com.example.movieapp.model.MovieModel;
import com.example.movieapp.model.VideoTrailerModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailMovieFragment extends BaseFragment implements MovieDetailView{

    @BindView(R.id.pb_detail_movie)
    ProgressBar pbDetailMovie;
    @BindView(R.id.tv_movie_title)
    TextView tvMovieTitle;
    @BindView(R.id.tv_release_date)
    TextView tvReleaseDate;
    @BindView(R.id.tv_duration)
    TextView tvDuration;
    @BindView(R.id.tv_rating)
    TextView tvRating;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.btn_add_favourite)
    Button btnAddFavourite;
    @BindView(R.id.btn_remove_favourite)
    Button btnRemoveFavourite;
    @BindView(R.id.movie_detail_container)
    LinearLayout movieDetailContainer;
    @BindView(R.id.iv_movie_detail_poster)
    ImageView ivMovieDetailPoster;

    @Inject
    MovieDetailPresenter movieDetailPresenter;

    Unbinder unbinder;

    MovieDetailModel movieDetail;

    public DetailMovieFragment() {
        // Required empty public constructor
    }

    public static DetailMovieFragment newInstance(String movieId) {
        final DetailMovieFragment fragment = new DetailMovieFragment();
        final Bundle arguments = new Bundle();
        arguments.putString("MovieId", movieId);
        fragment.setArguments(arguments);
        return fragment;
    }

    @OnClick(R.id.btn_add_favourite)
    public void setMovieAsFavourite(){
        movieDetailPresenter.addFavourite(movieDetail);
    }

    @OnClick(R.id.btn_remove_favourite)
    public void removeFavourite(){
        movieDetailPresenter.removeFavouriteMovie(getMovieID());
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_movie, container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        movieDetailPresenter.setView(this);
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState == null){
            this.loadDetails();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(MovieComponent.class).inject(this);
    }

    @Override
    public void initializeMovieDetail(MovieDetailModel movieModel) {
        movieDetail = movieModel;
        if(movieModel != null){
            tvMovieTitle.setText(movieModel.getTitle());
            tvReleaseDate.setText(movieModel.getReleaseDate());
            tvDuration.setText(movieModel.getDuration() +" minutes");
            tvRating.setText(movieModel.getMovieRate()+"/10");
            tvDescription.setText(movieModel.getSynopsis());
            Glide.with(getContext()).load(movieModel.getUrlPoster()).into(ivMovieDetailPoster);
        }
    }

    public String getMovieID(){
        final Bundle arguments = getArguments();
        if(arguments != null) {
            String movieId = arguments.getString("MovieId");
            return movieId;
        }else {
            return "";
        }
    }

    private void loadDetails() {
        if (movieDetailPresenter != null) {
            movieDetailPresenter.initialize(getMovieID());
        }
    }

    @Override
    public void showFavouriteButton() {
        btnAddFavourite.setVisibility(View.VISIBLE);
        btnRemoveFavourite.setVisibility(View.GONE);
    }

    @Override
    public void showUnfavouriteButton() {
        btnRemoveFavourite.setVisibility(View.VISIBLE);
        btnAddFavourite.setVisibility(View.GONE);
    }

    @Override
    public void showMovieTrailer(List<VideoTrailerModel> videoModels) {

    }

    @Override
    public void showLoading() {
        movieDetailContainer.setVisibility(View.GONE);
        pbDetailMovie.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbDetailMovie.setVisibility(View.GONE);
        movieDetailContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(context(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void onResume() {
        movieDetailPresenter.resume();
        super.onResume();
    }

    @Override
    public void onPause() {
        movieDetailPresenter.pause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        movieDetailPresenter.destroy();
        super.onDestroy();
    }
}
