package com.example.uadnd.cou8901.popularmoviesapp2.cp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.uadnd.cou8901.popularmoviesapp2.R;

/**
 * Created by dd2568 on 5/19/2017.
 */

public class FavoriteMoviesDbHelper extends SQLiteOpenHelper{
    Context mContext;
    private static final String DATABASE_NAME = "favoriteMoviesDb.db";
    private static final int VERSION = 1;

    public FavoriteMoviesDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public FavoriteMoviesDbHelper(Context context) {

        super(context, DATABASE_NAME, null, VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create favorite_movies table
        final String FAVORITE_MOVIES_CREATE_TABLE = " CREATE TABLE "  + FavoriteMoviesContract.FavoriteMovies.TABLE_NAME + " (" +
                FavoriteMoviesContract.FavoriteMovies._ID                + " INTEGER PRIMARY KEY, " +
                FavoriteMoviesContract.FavoriteMovies.COLUMN_ID + " TEXT  UNIQUE  NOT NULL ); " ;

         db.execSQL(FAVORITE_MOVIES_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String FAVORITE_MOVIES_DROP_TABLE = "DROP TABLE IF EXISTS " + FavoriteMoviesContract.FavoriteMovies.TABLE_NAME;
        db.execSQL(FAVORITE_MOVIES_DROP_TABLE);
        onCreate(db);
    }
}
