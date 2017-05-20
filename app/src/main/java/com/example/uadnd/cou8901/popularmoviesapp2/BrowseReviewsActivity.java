package com.example.uadnd.cou8901.popularmoviesapp2;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.uadnd.cou8901.popularmoviesapp2.moviedbutils.MovieDbAPI;
import com.example.uadnd.cou8901.popularmoviesapp2.moviedbutils.MovieDbJsonParser;

public class BrowseReviewsActivity extends AppCompatActivity implements
        ReviewsAdapter.ReviewsAdapterOnClickHandler,
        LoaderManager.LoaderCallbacks<String[]>{
    private static final String TAG = BrowseReviewsActivity.class.getName();
    private RecyclerView mRecyclerView;
    private ReviewsAdapter mReviewsAdapter;

    private static final int REVIEWS_LOADER_ID = 2;
    private String movieId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_reviews);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_reviews);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mReviewsAdapter = new ReviewsAdapter(this);
        mRecyclerView.setAdapter(mReviewsAdapter);

        Intent intent = getIntent();
        if(intent.hasExtra("MOVIE_ID")) {
            String movie_id = intent.getStringExtra("MOVIE_ID");

            movieId = movie_id;
        }

        int loaderId = REVIEWS_LOADER_ID;
        LoaderManager.LoaderCallbacks<String []> callback = BrowseReviewsActivity.this;
        Bundle bundle = null;
        getSupportLoaderManager().initLoader(loaderId, bundle, callback);
        
    }

    @Override
    public void onClick(String reviewClicked) {
        Context context = this;
        //Implicit / Explicit Intent
        //startActivity

    }

    @Override
    public Loader<String[]> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<String[]>(this){
            String[] mReviewData = null;

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                if(mReviewData != null) {
                    deliverResult(mReviewData);
                }else {
                    forceLoad();
                }
            }

            @Override
            public String[] loadInBackground() {
                String[] strArray = null;

                String reviewsJSON = MovieDbAPI.getMovieReviewsJSON(getString(R.string.movie_db_api_key), movieId);
                //Log.d(TAG, reviewsJSON);
                MovieDbMovieReview[] reviews = MovieDbJsonParser.parseMovieReviewList(reviewsJSON);
                //String[] vid = null;
                int i;
                if (null == reviews) {
                    return null;
                } else {
                    strArray = new String[reviews.length];
                    for (i = 0; i < reviews.length; i++) {
                        //strArray[i] = reviews[i].getAuthor() + "/" + reviews[i].getContent();
                        strArray[i] = reviews[i].getContent() + "\n\n-" + reviews[i].getAuthor();
                    }

                }
                return strArray;
            }
        };

    }

    @Override
    public void onLoadFinished(Loader<String[]> loader, String[] data) {
        mReviewsAdapter.setReviewsData(data);
    }

    @Override
    public void onLoaderReset(Loader<String[]> loader) {

    }
}
