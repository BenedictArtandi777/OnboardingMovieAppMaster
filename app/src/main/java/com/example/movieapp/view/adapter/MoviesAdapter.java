package com.example.movieapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.model.MovieModel;
import com.example.movieapp.utils.Navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private final LayoutInflater layoutInflater;

    private Context context;

    private List<MovieModel> movieModelList;

    @Inject
    public MoviesAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        movieModelList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.layoutInflater
                .inflate(R.layout.item_movie_list, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieModel movieModel = this.movieModelList.get(position);
        Glide.with(context).load(movieModel.getPosterPath()).into(holder.ivThumbnail);
        holder.ivThumbnail.setOnClickListener(v->{
            Navigator navigator = new Navigator();
            navigator.navigateToDetailMovie(context, movieModel.getMovieId());
        });
    }

    private void validateMoviesCollection(Collection<MovieModel> moviesCollection){
        if (moviesCollection == null) {
            throw new IllegalArgumentException("The list cannot be null!");
        }
    }

    public void setMoviesCollection(Collection<MovieModel> moviesCollection){
        this.validateMoviesCollection(moviesCollection);
        this.movieModelList = (List<MovieModel>) moviesCollection;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (this.movieModelList != null) ? this.movieModelList.size() : 0;
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.thumbnail_image)
        ImageView ivThumbnail;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
