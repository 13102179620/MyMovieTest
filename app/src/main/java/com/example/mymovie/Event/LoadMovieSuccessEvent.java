package com.example.mymovie.Event;

import com.example.mymovie.Dao.MovieEntity2Db;

import java.util.List;

public class LoadMovieSuccessEvent extends EVENT.EventHelper {
    private List<MovieEntity2Db> movielists;
    public LoadMovieSuccessEvent(List<MovieEntity2Db> movies){
        movielists = movies;
    }

    @Override
    public List<MovieEntity2Db> getMovielists() {
        return movielists;
    }
}
