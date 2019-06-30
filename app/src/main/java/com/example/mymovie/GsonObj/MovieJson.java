package com.example.mymovie.GsonObj;

import java.util.List;

public class MovieJson {
    int count;
    int start;
    int total;
    List<movies> movies;

    class movies{
        public movies(){}
        int[] rating;
        String[] type;
        String title;
        String description;
        List<cast> casts;
        List<directors> directors;
        int year;
        String imageUrl;
        class cast{
            public cast(){}
            String name;
        }

        class  directors{
            public directors(){}
            String name;
        }
    }
}
