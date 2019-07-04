package com.example.data.entity.mapper;

import com.example.data.cache.Serializer;
import com.example.data.entity.MovieEntity;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.data.entity.MovieEntity;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieEntityJsonMapper {
    private final Serializer serializer;

    @Inject
    public MovieEntityJsonMapper() {
        this.serializer = new Serializer();
    }

    /**
     * Transform from valid json string to {@link MovieEntity}.
     *
     * @param movieJsonResponse a json representing a movie file.
     * @return {@link MovieEntity}.
     */
    public MovieEntity transformMovieEntity(String movieJsonResponse) throws JsonSyntaxException {
        MovieEntity movieEntity = new MovieEntity();
        try {
            JSONObject jsonObject = new JSONObject(movieJsonResponse);
            movieEntity.setId(jsonObject.getInt("id"));
            movieEntity.setOriginalTitle(jsonObject.getString("original_title"));
            movieEntity.setVoteAverage(jsonObject.getDouble("vote_average"));
            movieEntity.setPosterPath(jsonObject.getString("poster_path"));
            movieEntity.setReleaseDate(jsonObject.getString("release_date"));
            movieEntity.setOverview(jsonObject.getString("overview"));
            movieEntity.setRuntime(jsonObject.getString("runtime"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieEntity;
    }

    /**
     * Transform from valid json string to List of {@link MovieEntity}.
     *
     * @param movieListJsonResponse a json representing a collection of movies.
     * @return List of {@link MovieEntity}.
     */
    public List<MovieEntity> transformMovieEntityCollection(String movieListJsonResponse) {
        List<MovieEntity> movieEntityList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(movieListJsonResponse);
            if (!jsonObject.getString("total_results").isEmpty()) {
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject results = jsonArray.getJSONObject(i);
                    MovieEntity movieEntity = new MovieEntity();
                    movieEntity.setId(results.getInt("id"));
                    movieEntity.setVoteCount(results.getInt("vote_count"));
                    movieEntity.setOriginalTitle(results.getString("original_title"));
                    movieEntity.setVoteAverage(results.getDouble("vote_average"));
                    movieEntity.setPosterPath(results.getString("poster_path"));
                    movieEntity.setOverview(results.getString("overview"));
                    movieEntity.setReleaseDate(results.getString("release_date"));
                    movieEntityList.add(movieEntity);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieEntityList;
    }
}
