package com.example.movieapp.view.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.model.MovieModel;
import com.example.movieapp.model.VideoTrailerModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerAdapter.MovieTrailerViewHolder> {

    private final LayoutInflater layoutInflater;

    private Context context;

    private List<VideoTrailerModel> videoTrailerList;

    @Inject
    public MovieTrailerAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        videoTrailerList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MovieTrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.layoutInflater
                .inflate(R.layout.item_movie_trailer_list, parent, false);
        return new MovieTrailerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieTrailerViewHolder holder, int position) {
        holder.tvMovieTrailerTitle.setText(this.videoTrailerList.get(position).getName());
        holder.videoTrailerContainer.setOnClickListener(v->{
            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + this.videoTrailerList.get(position).getKey()));
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + this.videoTrailerList.get(position).getKey()));
            try {
                context.startActivity(appIntent);
            } catch (ActivityNotFoundException ex) {
                context.startActivity(webIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoTrailerList.size();
    }

    public void addVideoTrailerCollection(Collection<VideoTrailerModel> trailerList){
        this.videoTrailerList.addAll(trailerList);
        this.notifyDataSetChanged();
    }

    static class MovieTrailerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.video_trailer_container)
        LinearLayout videoTrailerContainer;
        @BindView(R.id.tv_movie_trailer_title)
        TextView tvMovieTrailerTitle;
        public MovieTrailerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
