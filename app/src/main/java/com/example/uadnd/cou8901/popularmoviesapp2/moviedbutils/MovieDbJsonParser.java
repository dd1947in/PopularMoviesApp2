/*
 * Copyright (C) 2017 dd2568 : Project submitted for Udacity Android Developer Nanodegree.
 */
package com.example.uadnd.cou8901.popularmoviesapp2.moviedbutils;



import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.uadnd.cou8901.popularmoviesapp2.MovieDbMovie;
import com.example.uadnd.cou8901.popularmoviesapp2.MovieDbMovieReview;
import com.example.uadnd.cou8901.popularmoviesapp2.MovieDbMovieVideo;

/**
 * Created by dd2568 on 4/16/2017.
 * Shown below is a partial sample response from Movie DB
 *
 {
 "page": 1,
 "results": [
 {

 "poster_path": "/tWqifoYuwLETmmasnGHO7xBjEtt.jpg",
 "adult": false,
 "overview": "A live-action adaptation of Disney's version of the classic 'Beauty and the Beast' tale of a cursed prince and a beautiful young woman who helps him break the spell.",
 "release_date": "2017-03-16",
 "genre_ids": [
 14,
 10402,
 10749
 ],
 "id": 321612,
 "original_title": "Beauty and the Beast",
 "original_language": "en",
 "title": "Beauty and the Beast",
 "backdrop_path": "/6aUWe0GSl69wMTSWWexsorMIvwU.jpg",
 "popularity": 179.312119,
 "vote_count": 1394,
 "video": false,
 "vote_average": 7

 },
 .
 .
 .
 .
 ],
 "total_results": 19537,
 "total_pages": 977
 }
 */

public  class MovieDbJsonParser {
    private static final String POSTER_PREFIX_URL = "http://image.tmdb.org/t/p/w185"; // + poster_path ;

    /*
     * Keys in the JSON Results
     */
    private static final String RESULTS = "results";
    private static final String TOTAL_RESULTS = "total_results";
    private static final String TOTAL_PAGES = "total_pages";

    /*
     * Keys in JSON movie.
     * Not all the keys are used in this project.
     */
    private static final String POSTER_PATH = "poster_path";
    private static final String ADULT = "adult";
    private static final String OVERVIEW = "overview";
    private static final String RELEASE_DATE = "release_date";
    private static final String GENRE_IDS = "genre_ids";
    private static final String ID = "id";
    private static final String ORIGINAL_TITLE = "original_title";
    private static final String ORIGINAL_LANGUAGE = "original_language";
    private static final String TITLE = "title";
    private static final String BACKDROP_PATH = "backdrop_path";
    private static final String POPULARITY = "popularity";
    private static final String VOTE_COUNT = "vote_count";
    private static final String VIDEO = "video";
    private static final String VOTE_AVERAGE = "vote_average";

    //Review properties
    private static final String AUTHOR = "author";
    private static final String CONTENT = "content";
    private static final String URL = "url";

    //Video Properties
    private static final String ISO_639_1 = "iso_639_1";
    private static final String ISO_3166_1 = "iso_3166_1";
    private static final String KEY = "key";
    private static final String NAME = "name";
    private static final String SITE = "site";
    private static final String SIZE = "size";
    private static final String TYPE = "type";



    public static MovieDbMovie[] parseMovieList(String jsonString) {
        /*
         * Do Json parse of Movie DB response
         */
        String TAG = MovieDbMovie.class.getSimpleName();
        MovieDbMovie [] movieDbMovies = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            if(jsonObject.getInt(TOTAL_RESULTS) > 0 && jsonObject.getInt(TOTAL_PAGES) > 0 && jsonObject.has(RESULTS)) {
                //If movies json array is present
                JSONArray movieArray = jsonObject.getJSONArray(RESULTS);
                int numMovies = movieArray.length();
                movieDbMovies = new MovieDbMovie[numMovies];
                for(int i = 0; i < numMovies; i++){
                    /*
                     * Parse one movie at a time
                     */
                    JSONObject jsonMovie = movieArray.getJSONObject(i);
                    movieDbMovies[i] = parseMovie(jsonMovie.toString());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieDbMovies;

    }
    public static MovieDbMovie  parseMovie(String jsonString) {
        /*
         * Do Json parse of one movie json string
         */
        String TAG = MovieDbMovie.class.getSimpleName();
        MovieDbMovie  movieDbMovie = null;
        try {
            JSONObject jsonMovie = new JSONObject(jsonString);
            movieDbMovie = new MovieDbMovie(); // Instantiate a  MovieDbMovie object
            movieDbMovie.setPosterPath(jsonMovie.getString(POSTER_PATH));
            movieDbMovie.setOriginalTitle(jsonMovie.getString(ORIGINAL_TITLE));
            movieDbMovie.setOverview((jsonMovie.getString(OVERVIEW)));
            movieDbMovie.setAdult(jsonMovie.getBoolean(ADULT));
            movieDbMovie.setReleaseDate(jsonMovie.getString(RELEASE_DATE));
            //movieDbMovies[i].setGenreIds();
            movieDbMovie.setId(jsonMovie.getString(ID));
            //movieDbMovie.setOriginalTitle(jsonMovie.getString(TITLE));
            movieDbMovie.setTitle(jsonMovie.getString(TITLE));
            movieDbMovie.setVoteAverage(jsonMovie.getString(VOTE_AVERAGE));
            movieDbMovie.setJson(jsonString); // set json string as json attribute
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieDbMovie;

    }
    public static MovieDbMovieReview[] parseMovieReviewList(String jsonString) {
        /*
         * Do Json parse of Movie DB response
         */
        String TAG = MovieDbMovie.class.getSimpleName();
        MovieDbMovieReview [] movieDbMovieReviews = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            if(jsonObject.getInt(TOTAL_RESULTS) > 0 && jsonObject.getInt(TOTAL_PAGES) > 0 && jsonObject.has(RESULTS)) {
                //If movies json array is present
                JSONArray reviewArray = jsonObject.getJSONArray(RESULTS);
                int numReviews = reviewArray.length();
                movieDbMovieReviews = new MovieDbMovieReview[numReviews];
                for(int i = 0; i < numReviews; i++){
                    /*
                     * Parse one movie at a time
                     */
                    JSONObject jsonReview = reviewArray.getJSONObject(i);
                    movieDbMovieReviews[i] = parseMovieReview(jsonReview.toString());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieDbMovieReviews;

    }

    public static MovieDbMovieReview parseMovieReview(String jsonString) {
        MovieDbMovieReview movieDbMovieReview = null;
        try {

            JSONObject jsonReview = new JSONObject(jsonString);
            movieDbMovieReview = new MovieDbMovieReview();
            movieDbMovieReview = new MovieDbMovieReview();
            movieDbMovieReview.setId(jsonReview.getString(ID));
            movieDbMovieReview.setAuthor(jsonReview.getString(AUTHOR));
            movieDbMovieReview.setContent(jsonReview.getString(CONTENT));
            movieDbMovieReview.setUrl(jsonReview.getString(URL));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieDbMovieReview;
    }

    public static MovieDbMovieVideo[] parseMovieVideoList(String jsonString) {
        /*
         * Do Json parse of Movie DB response
         */
        String TAG = MovieDbMovie.class.getSimpleName();
        MovieDbMovieVideo [] movieDbMovieVideos = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            //if(jsonObject.getInt(TOTAL_RESULTS) > 0 && jsonObject.getInt(TOTAL_PAGES) > 0 && jsonObject.has(RESULTS)) {
            if(jsonObject.has(RESULTS)) {
                //If movies json array is present
                JSONArray videoArray = jsonObject.getJSONArray(RESULTS);
                int numVideos = videoArray.length();
                //Log.d("NUM VIDEOS:", String.valueOf(numVideos));
                movieDbMovieVideos = new MovieDbMovieVideo[numVideos];
                for(int i = 0; i < numVideos; i++){
                    /*
                     * Parse one movie at a time
                     */

                    JSONObject jsonVideo = videoArray.getJSONObject(i);
                    //Log.d("Beforre Vodeo Parse", jsonVideo.toString());
                    movieDbMovieVideos[i] = parseMovieVideo(jsonVideo.toString());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieDbMovieVideos;

    }

    public static MovieDbMovieVideo parseMovieVideo(String jsonString) {
        MovieDbMovieVideo movieDbMovieVideo = null;
        try {
            JSONObject jsonVideo = new JSONObject(jsonString);
            movieDbMovieVideo = new MovieDbMovieVideo();
            movieDbMovieVideo.setId(jsonVideo.getString(ID));
            movieDbMovieVideo.setIso_639_1(jsonVideo.getString(ISO_639_1));
            movieDbMovieVideo.setIso_3166_1(jsonVideo.getString(ISO_3166_1));
            movieDbMovieVideo.setKey(jsonVideo.getString(KEY));
            movieDbMovieVideo.setName(jsonVideo.getString(NAME));
            movieDbMovieVideo.setSite(jsonVideo.getString(SITE));
            movieDbMovieVideo.setSize(jsonVideo.getString(SIZE));
            movieDbMovieVideo.setType(jsonVideo.getString(TYPE));
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return  movieDbMovieVideo;
    }


}
