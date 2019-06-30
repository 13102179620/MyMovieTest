package com.example.mymovie.Event;

import com.example.mymovie.Dao.MovieEntity2Db;

public class ShowDetaiFragmentEvent extends EVENT.EventHelper {
    MovieEntity2Db movie;

    public ShowDetaiFragmentEvent(MovieEntity2Db movie){
        this.movie = movie;
    }

    @Override
    public MovieEntity2Db getMovie() {
        return movie;
    }
}
