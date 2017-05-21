/*
 * Copyright (C) 2017 dd2568 : Project submitted for Udacity Android Developer Nanodegree.
 */
package com.example.uadnd.cou8901.popularmoviesapp2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MovieDbMainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final static String TAG = MovieDbMainActivity.class.getSimpleName();


    public static String currentMovieList = null ; // initialize from shared preferences in onCreate
    //int index = gridView.getFirstVisiblePosition();
    //gridView.setSelection(index);

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
        MovieDbMainActivityFragment movieGridFragment = (MovieDbMainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        outState.putString("GRID_POS", String.valueOf(movieGridFragment.gridView.getFirstVisiblePosition()));
        Log.d(TAG, "onSaveInstanceState: GRID_POS : " + String.valueOf(movieGridFragment.gridView.getFirstVisiblePosition()));
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int intGridPos = 0;
        MovieDbMainActivityFragment movieGridFragment = (MovieDbMainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        String gridPos = savedInstanceState.getString("GRID_POS");
        if(gridPos != null) {
            Log.d(TAG, "onRestoreInstanceState: GRID_POS : " + gridPos);
            intGridPos = Integer.parseInt(gridPos);
        }
        movieGridFragment.gridView.setSelection(intGridPos);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupCurrentMovieListFromSharedPreferences();
        setContentView(R.layout.activity_movie_db_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    private void setupCurrentMovieListFromSharedPreferences() {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        currentMovieList = sharedPreferences.getString(getString(R.string.pref_movie_db_list), getString(R.string.ml_popular));
        //Log.d(TAG, currentMovieList);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu;
        getMenuInflater().inflate(R.menu.menu_movie_db_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //menu item selected/clicked
        int id = item.getItemId();
        if (id == R.id.action_settings) {
             Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;

        }

        /*
         * Get the Fragment reference and load movie data with the current movie list
         */
        MovieDbMainActivityFragment movieGridFragment = (MovieDbMainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if(movieGridFragment != null) {
            movieGridFragment.loadMovieDbData(currentMovieList);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_movie_db_list))) {
            currentMovieList = sharedPreferences.getString(key, getResources().getString(R.string.pref_movie_db_list_default));
            //Log.d(TAG, "Movie list changed to  : " + currentMovieList);
        }

    }
    public static String getCurrentMovieList() {
        return currentMovieList;
    }
}
