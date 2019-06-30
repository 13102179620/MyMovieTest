package com.example.mymovie.Event;

import com.example.mymovie.Dao.MovieEntity2Db;

import java.util.List;

public class LoadSearchResMovieSuccessEvent extends EVENT.EventHelper {
    List<MovieEntity2Db> movielists;
    public LoadSearchResMovieSuccessEvent(List<MovieEntity2Db> movies){
        this.movielists = movies;
    }
    @Override
    public List<MovieEntity2Db> getMovielists() {
        return movielists;
    }

}
