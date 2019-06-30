package com.example.mymovie.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymovie.Dao.MovieEntity2Db;
import com.example.mymovie.R;

import static com.example.mymovie.MainActivity.TAG;


public class MovieDetailFragment extends Fragment {

    MovieEntity2Db movie;

    private static final String MOVIE_DETAIL_OBJ = "movieDetailObj";


    public MovieDetailFragment() {
        // Required empty public constructor
    }



    public static MovieDetailFragment newInstance(MovieEntity2Db movie) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(MOVIE_DETAIL_OBJ, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movie = getArguments().getParcelable(MOVIE_DETAIL_OBJ);
            Log.d(TAG, "onCreate: " + movie.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }






}
