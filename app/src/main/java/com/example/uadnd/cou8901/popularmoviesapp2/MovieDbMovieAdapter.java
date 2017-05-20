/*
 * Copyright (C) 2017 dd2568 : Project submitted for Udacity Android Developer Nanodegree.
 */
package com.example.uadnd.cou8901.popularmoviesapp2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/*
 * Created by dd2568 on 4/15/2017.
 */

public class MovieDbMovieAdapter extends ArrayAdapter<MovieDbMovie>{
    /*
     * Adapter variables
     */
    private Context context = null;
    private static final String POSTER_PREFIX_URL = "http://image.tmdb.org/t/p/w185";
    private static final String TAG = MovieDbMovieAdapter.class.getName();

    public MovieDbMovieAdapter(Activity acontext, List<MovieDbMovie> movieDbMovies) {
         /*
          * This adapter is created in fragment and set to grid view to provide the views as needed.
          */
          super(acontext, 0, movieDbMovies);
          context = this.getContext();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*
         * getView provides views as needed by grid view in fragment.
         *
         */
        final MovieDbMovie movieDbMovie = getItem(position);
            if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_item, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_poster);
        TextView textView  = (TextView) convertView.findViewById(R.id.original_title);
        //textView.setText(movieDbMovie.getOriginalTitle());  // not used now.
        String url =  POSTER_PREFIX_URL + movieDbMovie.getPosterPath(); // Poster image URL
        /*
         * Use Picaso API to load & cache images needed
         */
        Picasso.with(context).load(url).fit().into(imageView);
        /*
         * Set on click listener to take to movie detail activity.
         *  Json movie is passed in bundle and as well as intent .
         *  Only the movie json in intent is being used in detail activity.
         */
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent movieDetailIntent = new Intent(context, MovieDbDetailActivity.class );
                movieDetailIntent.putExtra("MOVIE", movieDbMovie.getJson());
                Bundle bundle = new Bundle();
                bundle.putString("MOVIE", movieDbMovie.getJson());
                startActivity(context, movieDetailIntent, bundle);  // on click start detail activity
            }
        });

        return convertView;
    }


}
