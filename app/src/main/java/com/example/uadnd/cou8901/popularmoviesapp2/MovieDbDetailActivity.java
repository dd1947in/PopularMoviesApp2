/*
 * Copyright (C) 2017 dd2568 : Project submitted for Udacity Android Developer Nanodegree.
 */
package com.example.uadnd.cou8901.popularmoviesapp2;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uadnd.cou8901.popularmoviesapp2.cp.FavoriteMoviesContract;
import com.example.uadnd.cou8901.popularmoviesapp2.moviedbutils.MovieDbAPI;
import com.example.uadnd.cou8901.popularmoviesapp2.moviedbutils.MovieDbJsonParser;
import com.squareup.picasso.Picasso;



public class MovieDbDetailActivity extends AppCompatActivity  {
    /*
     * Detail Activity View variables and Image URL prefix
     */
    private static final String TAG = MovieDbDetailActivity.class.getName();

    private static final int VIDEOS_LOADER_ID = 1;
    private static final int REVIEWS_LOADER_ID = 2;

    private static final String POSTER_PREFIX_URL = "http://image.tmdb.org/t/p/w185";
    private TextView tvMovieTitle;
    private TextView tvOverview;
    private ImageView ivImageView;
    private TextView tvRating;
    private TextView tvReleaseDate;
    Context context;

    private static String movieId = null;
    private static String movieJson =null;
    private static MovieDbMovie movie = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
         * Call super class method and set content view
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_db_detail);

        /*
         * Intitialize all view variables
         */
        tvMovieTitle = (TextView) findViewById(R.id.tv_movie_title);
        tvOverview = (TextView) findViewById(R.id.tv_overview);
        ivImageView = (ImageView) findViewById(R.id.iv_movie_poster);
        tvRating = (TextView) findViewById(R.id.tv_movie_vote_average);
        tvReleaseDate = (TextView) findViewById(R.id.tv_movie_release_date);
        context = this.getApplicationContext();
        /*
         * Get the intent and check if it has "MOVIE" json string in Extra
         */

        if(savedInstanceState != null) {
            String movieJ = savedInstanceState.getString("MOVIE");
            Log.d(TAG, "savedInstanceState: "+movieJ);
        }
        Intent intent = getIntent();
        if(intent.hasExtra("MOVIE"))  {
            /*
             * Get the MOVIE from extra in Intent and parse it
             */
            movieJson = intent.getStringExtra("MOVIE");
            movie = MovieDbJsonParser.parseMovie(movieJson);
            movieId = movie.getId(); // to get videos/reviews

            /*
             * Prepare movie attributes for populating views
             */
            String url = POSTER_PREFIX_URL + movie.getPosterPath();
            String title = movie.getOriginalTitle();
            Picasso.with(context).load(url).fit().into(ivImageView);
            tvMovieTitle.setText(title);
            tvOverview.setText(movie.getOverview());
            tvRating.setText(String.valueOf(movie.getVoteAverage()));
            tvReleaseDate.setText(movie.getReleaseDate());

            //Log.d(TAG ,  ":onCreate:" + movieId);

        } else {
            Toast.makeText(this, "No MOVIE in Intent", Toast.LENGTH_LONG);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Log.d(TAG, "onStop");

    }

    @Override
    protected void onPause() {
        super.onPause();
        //Log.d(TAG, "onPause");
    }

    @Override
    protected void onStart() {
        Bundle savedInstanceState = new Bundle();
        super.onStart();
        //Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.d(TAG, "onResume");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //Log.d(TAG, "onSaveInstanceState 2");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Log.d(TAG,movieJson );
        outState.putString("MOVIE", movieJson);
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        movieJson = savedInstanceState.getString("MOVIE");
        Log.d(TAG, "onRestoreInstanceState 1");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //Log.d(TAG, "onRestart : ");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        //Log.d(TAG, "onRestoreInstanceState 2");
    }
    //Launch the new activity to browse the Movie Trailers/Videos on clicking the button.
    public void browseTrailers(View view){

         Context context = this;
         Class destinationActivity = BrowseTrailersActivity.class;
         Intent intentBrowseTrailers = new Intent(context, destinationActivity);
         intentBrowseTrailers.putExtra("MOVIE_ID", movieId);
         startActivity(intentBrowseTrailers);
    }
     //Launch the new activity to browse the Movie Reviews on clicking the button.
    public void browseReviews(View view){

        Context context = this;
        Class destinationActivity = BrowseReviewsActivity.class;
        Intent intentBrowseTrailers = new Intent(context, destinationActivity);
        intentBrowseTrailers.putExtra("MOVIE_ID", movieId);
        startActivity(intentBrowseTrailers);

    }
    //Mark a movie as favorite using Content Provider . Unique key will ensure a single entry for each movie
    public void markFavorite(View view){

        ContentValues contentValues = new ContentValues(1);
        contentValues.put(FavoriteMoviesContract.FavoriteMovies.COLUMN_ID, movieId);
        Uri uri = getContentResolver().insert(FavoriteMoviesContract.FavoriteMovies.CONTENT_URI, contentValues);
        if(uri != null) {
            Toast.makeText(this, "Movie Id "+ movieId + " is added @ " + uri.toString(), Toast.LENGTH_LONG ).show();
        }
    }
}
