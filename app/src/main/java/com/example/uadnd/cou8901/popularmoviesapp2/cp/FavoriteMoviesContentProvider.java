package com.example.uadnd.cou8901.popularmoviesapp2.cp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.example.uadnd.cou8901.popularmoviesapp2.cp.FavoriteMoviesContract.FavoriteMovies.TABLE_NAME;

/**
 * Created by dd2568 on 5/19/2017.
 */

public class FavoriteMoviesContentProvider extends ContentProvider {
    public static final int FAVORITE_MIVIES = 100; // Get all movied ids
    public static final int FAVORITE_MOVIES_ID = 101; // query or insert one movie id
    private static final UriMatcher strUriMatcher = buildUriMatcher();

    private FavoriteMoviesDbHelper mFavoriteMoviesDbHelper ;

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(FavoriteMoviesContract.AUTHORITY, FavoriteMoviesContract.PATH_FAVORITE_MOVIES, FAVORITE_MIVIES );  // Query all movie ids
        uriMatcher.addURI(FavoriteMoviesContract.AUTHORITY, FavoriteMoviesContract.PATH_FAVORITE_MOVIES + "/#", FAVORITE_MOVIES_ID ); // Query or insert one movie id

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        mFavoriteMoviesDbHelper = new FavoriteMoviesDbHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = mFavoriteMoviesDbHelper.getReadableDatabase();
        int match = strUriMatcher.match(uri);
        Cursor cursor;

        switch (match) {
            case  FAVORITE_MIVIES :
                cursor =  db.query(TABLE_NAME, projection,selection, selectionArgs,null,null, sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        //insert one favorite movie id
        final SQLiteDatabase db =  mFavoriteMoviesDbHelper.getWritableDatabase();

        int match = strUriMatcher.match(uri);
        Uri retUri;
        long id ;

        switch (match) {
            case FAVORITE_MIVIES :
                //id = db.insert(TABLE_NAME, null, values);  // new movieid in values
                id = db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                if(id > 0) {
                    retUri = ContentUris.withAppendedId(FavoriteMoviesContract.FavoriteMovies.CONTENT_URI, id) ;
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;

            case FAVORITE_MOVIES_ID :
                String movieId = uri.getPathSegments().get(1);  //  new movieid on path
                ContentValues values1 = new ContentValues(1);
                values1.put(FavoriteMoviesContract.FavoriteMovies.COLUMN_ID, movieId);

                //id = db.insert(TABLE_NAME, null, values1);
                id = db.insertWithOnConflict(TABLE_NAME, null, values1, SQLiteDatabase.CONFLICT_REPLACE);
                if(id > 0) {
                    retUri = ContentUris.withAppendedId(FavoriteMoviesContract.FavoriteMovies.CONTENT_URI, id) ;
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);

        }

        getContext().getContentResolver().notifyChange(uri, null);
        return retUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        final SQLiteDatabase db = mFavoriteMoviesDbHelper.getWritableDatabase();
        int match = strUriMatcher.match(uri);

        int moviesDeleted;
        switch (match) {
            case FAVORITE_MOVIES_ID:
                String id = uri.getPathSegments().get(1);
                moviesDeleted = db.delete(TABLE_NAME, " id=?", new String[]{id});

                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);

        }
        // Notify the resolver of a change and return the number of items deleted
        if (moviesDeleted != 0) {
            // A task was deleted, set notification
            getContext().getContentResolver().notifyChange(uri, null);
        }


        return moviesDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
