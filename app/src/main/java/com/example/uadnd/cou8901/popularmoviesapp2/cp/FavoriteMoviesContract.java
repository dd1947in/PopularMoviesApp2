package com.example.uadnd.cou8901.popularmoviesapp2.cp;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by dd2568 on 5/19/2017.
 */

public final class FavoriteMoviesContract { // Make it final as it will not be extended
    public static final String AUTHORITY = "com.example.uadnd.cou8901.popularmoviesapp2";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY) ;

    public static final String PATH_FAVORITE_MOVIES = "favorite_movies";
    private FavoriteMoviesContract(){}  // suppress the constructor
    public static final class FavoriteMovies implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITE_MOVIES).build();
        public static final String TABLE_NAME = "favorite_movies";
        public static final String COLUMN_ID = "id";  // id of the movie that was marked favorite
    }

}
