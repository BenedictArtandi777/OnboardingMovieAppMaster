package com.example.domain.interactor;

import com.example.domain.executor.PostExecutionThread;
import com.example.domain.executor.ThreadExecutor;
import com.example.domain.repository.FavouriteMovieRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AddFavouriteMovie extends UseCase<Boolean,AddFavouriteMovie.Params> {

    private final FavouriteMovieRepository repository;

    @Inject
    public AddFavouriteMovie(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, FavouriteMovieRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<Boolean> buildUseCaseObservable(Params params) {
        return repository.addFavoriteMovie(params.getMovieId(),params.getPosterPath());
    }


    public class Params{
        String movieId;
        String posterPath;

        public Params(String movieId, String posterPath) {
            this.movieId = movieId;
            this.posterPath = posterPath;
        }

        public String getMovieId() {
            return movieId;
        }

        public void setMovieId(String movieId) {
            this.movieId = movieId;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }
    }

    public Params createFavouriteMovieParas(String movieId, String posterPath){
        Params params = new Params(movieId,posterPath);
        return params;
    }
}
