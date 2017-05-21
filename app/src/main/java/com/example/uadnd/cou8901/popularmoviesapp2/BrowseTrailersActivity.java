package com.example.uadnd.cou8901.popularmoviesapp2;


import android.content.Intent;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.uadnd.cou8901.popularmoviesapp2.moviedbutils.MovieDbAPI;
import com.example.uadnd.cou8901.popularmoviesapp2.moviedbutils.MovieDbJsonParser;

public class BrowseTrailersActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String[]>{
    private static final String TAG = BrowseTrailersActivity.class.getName();
    private RecyclerView mRecyclerView;
    private VideosAdapter mVideosAdapter;

    private static final int VIDEOS_LOADER_ID = 1;
    private String movieId;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_trailers);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_videos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mVideosAdapter = new VideosAdapter(this);

        mRecyclerView.setAdapter(mVideosAdapter);

        ///////////////////////////////////////////
        ActionBar actionBar = this.getSupportActionBar();
        // Set the action bar back button to look like an up button
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        ///////////////////////////////////////////

        Intent intent = getIntent();
        if(intent.hasExtra("MOVIE_ID")) {
            String movie_id = intent.getStringExtra("MOVIE_ID");

            movieId = movie_id;
            //Toast.makeText(this, "Movie Id:"+movieId, Toast.LENGTH_LONG).show();
        }

        int loaderId = VIDEOS_LOADER_ID;
        LoaderManager.LoaderCallbacks<String []> callback = BrowseTrailersActivity.this;
        Bundle bundle = null;
        getSupportLoaderManager().initLoader(loaderId, bundle, callback);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Log.d(TAG, "onBackPressed");
    }

    @Override
    public boolean onNavigateUp() {
        //Log.d(TAG, "onNavigateUp");
        return super.onNavigateUp();

    }

    @Override
    public Loader<String[]> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<String[]>(this){
            String[] mTrailerData = null;

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                if(mTrailerData != null) {
                    deliverResult(mTrailerData);
                }else {
                    forceLoad();
                }
            }

            @Override
            public String[] loadInBackground() {
                String[] strArray = null;

                String videosJSON = MovieDbAPI.getMovieVideosJSON(getString(R.string.movie_db_api_key), movieId);
                //Log.d(TAG, videosJSON);
                MovieDbMovieVideo[] videos = MovieDbJsonParser.parseMovieVideoList(videosJSON);

                int i;
                if (null == videos) {
                    return null;
                } else {
                    strArray = new String[videos.length];
                    for (i = 0; i < videos.length; i++) {
                        strArray[i] = videos[i].getName() + ":" + getString(R.string.youtube_watch_v) + videos[i].getKey();
                    }

                }
                return strArray;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String[]> loader, String[] data) {
        mVideosAdapter.setVideosData(data);

    }

    @Override
    public void onLoaderReset(Loader<String[]> loader) {

    }

}
