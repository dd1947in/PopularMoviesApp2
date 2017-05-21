/*
 * Copyright (C) 2017 dd2568 : Project submitted for Udacity Android Developer Nanodegree.
 */
package com.example.uadnd.cou8901.popularmoviesapp2;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.uadnd.cou8901.popularmoviesapp2.cp.FavoriteMoviesContract;
import com.example.uadnd.cou8901.popularmoviesapp2.moviedbutils.MovieDbAPI;
import com.example.uadnd.cou8901.popularmoviesapp2.moviedbutils.MovieDbJsonParser;

import java.net.URL;
import java.util.Arrays;

import static com.example.uadnd.cou8901.popularmoviesapp2.moviedbutils.MovieDbAPI.getFavoriteMovieJSON;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDbMainActivityFragment extends Fragment {

    /*
     * Required Views and Movie DB variables
     */
    private static String TAG = MovieDbMainActivityFragment.class.getSimpleName();
    private View rootView;
    public GridView gridView;
    private MovieDbMovieAdapter movieDbMovieAdapter;
    public static MovieDbMovie [] movieDbMovies ;

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        outState.putString("GRID_POS", String.valueOf(gridView.getFirstVisiblePosition()));
//        super.onSaveInstanceState(outState);
//        Log.d(TAG, "GRID_POS : " + String.valueOf(gridView.getFirstVisiblePosition()));
//    }

//    @Override
//    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
//        int intGridPos = 0;
//        super.onViewStateRestored(savedInstanceState);
//        String gridPos = savedInstanceState.getString("GRID_POS");
//        if(gridPos != null) {
//            intGridPos = Integer.parseInt(gridPos);
//        }
//        gridView.setSelection(intGridPos);
//
//    }
    //String currentMovieList = MovieDbMainActivity.POPULAR_LIST;


     public MovieDbMainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void loadMovieDbData(String movieList) {
        /*
         * Use Async Task to load Movie DB data.
         */
        new MovieDbQueryTask().execute(movieList);    //Async Task

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*
         * onCreateView load data and initialize views
         */
        //loadMovieDbData(currentMovieList);
        loadMovieDbData(MovieDbMainActivity.getCurrentMovieList());

        rootView = inflater.inflate(R.layout.fragment_movie_db_main, container, false);
        gridView = (GridView) rootView.findViewById(R.id.movies_grid);

        return rootView;
    }
    public class MovieDbQueryTask extends AsyncTask<String, Void, String> {
        /*
         * Async task for loading data from Movie DB
         */
        @Override
        protected String  doInBackground(String... params) {
            /*
             * Get the results in background thread
             */
            String currentMovieList = params[0];

            String moviesJSON = null;

            if(currentMovieList.equals(getString(R.string.ml_popular))) {
                moviesJSON = MovieDbAPI.getPopularMoviesJSON(getString(R.string.movie_db_api_key));
            } else if(currentMovieList.equals(getString(R.string.ml_top_rated))){
                moviesJSON = MovieDbAPI.getTopRatedMoviesJSON(getString(R.string.movie_db_api_key));
            } else if(currentMovieList.equals(getString(R.string.ml_favorite))) {
                moviesJSON = getFavMovies();
            }
            return  moviesJSON ;
        }

        // Method to get Favorite movies from content provider and then to get json for each favorite movie .
        protected String getFavMovies() {
            String moviesJSON = null;
            //Log.d(TAG, "FAVORITE_LIST is getting loaded");
            Context context = getContext();
            int i = 0;
            String[] moviesFavJson = null;
            int favoriteCount;
            Cursor cursor = context.getContentResolver().query(FavoriteMoviesContract.FavoriteMovies.CONTENT_URI, null, null, null, FavoriteMoviesContract.FavoriteMovies.COLUMN_ID);
            int idColIndex = cursor.getColumnIndex(FavoriteMoviesContract.FavoriteMovies.COLUMN_ID);
            favoriteCount = cursor.getCount();
            // JSON for favorite movies list is mechanically prepared by concating individual movie json and then
            // concating with prefix and postfix to make the whole string a valid json string
            if(favoriteCount > 0) {
                moviesFavJson = new String[favoriteCount];
                while (cursor.moveToNext() && i < cursor.getCount() ){
                    //Get valid JSON strings for each favorite movie and hold them in a array list .
                    moviesFavJson[i] = getFavoriteMovieJSON(getString(R.string.movie_db_api_key), cursor.getString(idColIndex));
                    i++;
                }

                String jsonPrefix = "{\"page\":1,\"results\":[";   // JSON prefix to make the valid json
                String jsonPostfix = "],\"total_results\":19537,\"total_pages\":977}"; // JSON postfix to make the valid json
                String wholeJson = "" + jsonPrefix + moviesFavJson[0]; // first movie is added to the list upfront.
                for(i = 1; i < moviesFavJson.length ; i++ ) {
                    //Concat all movie Jsons with "," as seperator
                    wholeJson += ","+ moviesFavJson[i];
                }
                moviesJSON = wholeJson + jsonPostfix;
                if(cursor != null) {
                    cursor.close();  //close the cursor
                }

            }
            return moviesJSON;
        }

        @Override
        protected void onPostExecute(String moviesJSON) {
            /*
             * onPostExecute , if results are available, parse the json result, create and set array
             * adapter to movie grid view of main activity
             */
            super.onPostExecute(moviesJSON);
            if(moviesJSON != null){
                movieDbMovies = MovieDbJsonParser.parseMovieList(moviesJSON);
                //movieDbMovies = null; //
                if(movieDbMovies == null) {
                    movieDbMovies = new MovieDbMovie[0];
                }
                movieDbMovieAdapter = new MovieDbMovieAdapter(getActivity(), Arrays.asList(movieDbMovies));
                gridView.setAdapter(movieDbMovieAdapter);
            }
        }
    }

}
