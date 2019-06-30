package com.example.mymovie.Event;

import com.example.mymovie.Dao.MovieEntity2Db;

import java.util.List;

public interface EVENT {
    public List<MovieEntity2Db> getMovielists();
    public MovieEntity2Db getMovie();


    abstract class EventHelper implements EVENT{

        @Override
        public List<MovieEntity2Db> getMovielists() {
            return null;
        }
        @Override
        public MovieEntity2Db getMovie(){
            return  null;
        }
    }

}
