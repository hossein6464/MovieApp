package diana.soleil.movieapp.db;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import diana.soleil.movieapp.FavoriteActivity;
import diana.soleil.movieapp.model.Movie;
import diana.soleil.movieapp.model.MovieFavorite;

//  Attention: DBManager extends Application
public class DBManager extends Application {

public DBOpenHelper dbOpenHelper;
public SQLiteDatabase sqLiteDatabase;

// ................................
@Override
public void onCreate() {
    super.onCreate();

    // Create Database
    dbOpenHelper = new DBOpenHelper(this);
    // Create a reference to database for CRUD
    sqLiteDatabase = dbOpenHelper.getWritableDatabase();
    //sqLiteDatabase.execSQL("PRAGMA foreign_keys=ON");
}

@Override
public void onTerminate() {
    super.onTerminate();
}

public void insertInTable(String tableName,
                          ArrayList<ContentValues> values) {
    for (ContentValues value : values) {
        insertInTable(tableName, value);
    }
}

public long insertInTable(String tableName,
                          ContentValues values) {

    return sqLiteDatabase.insert(tableName,
            null, values);
}

public Cursor queryInTable(String tableName,
                           String[] columns,
                           String selection,
                           String[] selectionArgs) {

    return sqLiteDatabase.query(tableName,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null);
}

public int deleteRowFromTable(String tableName,
                              String selection,
                              String[] selectionArgs) {

    return sqLiteDatabase.delete(tableName,
            selection,
            selectionArgs);
}

public int updateTable(String tableName,
                       ContentValues values,
                       String whereClause,
                       String[] whereArgs) {

    return sqLiteDatabase.update(tableName,
            values,
            whereClause,
            whereArgs);
}

    public ContentValues javaObjectToOneContentValue(MovieFavorite movieFavorite) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(SQLCommands.COLUMN_ID, movieFavorite.getMovieId());
            contentValues.put(SQLCommands.COLUMN_TITLE, movieFavorite.getMovieTitle());
            contentValues.put(SQLCommands.COLUMN_IMAGE_WITH_TITLE, movieFavorite.getMovieImageWithTitle());
            contentValues.put(SQLCommands.COLUMN_DESCRIPTION, movieFavorite.getMovieDescription());
            contentValues.put(SQLCommands.COLUMN_LANGUAGE, movieFavorite.getMovieLanguage());
            contentValues.put(SQLCommands.COLUMN_RELEASE_DATE, movieFavorite.getMovieReleaseDate());

        return contentValues;
    }

public ArrayList<ContentValues> javaObjectToContentValue(ArrayList<MovieFavorite> favoriteArrayList) {
    ArrayList<ContentValues> contentValuesArrayList = new ArrayList<>();

    for (MovieFavorite movieFavorite : favoriteArrayList) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLCommands.COLUMN_ID, movieFavorite.getMovieId());
        contentValues.put(SQLCommands.COLUMN_TITLE, movieFavorite.getMovieTitle());
        contentValues.put(SQLCommands.COLUMN_IMAGE_WITH_TITLE, movieFavorite.getMovieImageWithTitle());
        contentValues.put(SQLCommands.COLUMN_DESCRIPTION, movieFavorite.getMovieDescription());
        contentValues.put(SQLCommands.COLUMN_LANGUAGE, movieFavorite.getMovieLanguage());
        contentValues.put(SQLCommands.COLUMN_RELEASE_DATE, movieFavorite.getMovieReleaseDate());

        contentValuesArrayList.add(contentValues);
    }
    return contentValuesArrayList;
}

public ArrayList<MovieFavorite> cursorToArrayList(Cursor cursor) {
    ArrayList<MovieFavorite> movieFavorites = new ArrayList<>();
    cursor.moveToFirst();

    if (cursor.getCount() != 0) {
        do {
            MovieFavorite movieFavorite = new MovieFavorite();
            movieFavorite.setMovieId(cursor.getInt(0));
            movieFavorite.setMovieTitle(cursor.getString(1));
            movieFavorite.setMovieImageWithTitle(cursor.getString(2));
            movieFavorite.setMovieDescription(cursor.getString(3));
            movieFavorite.setMovieLanguage(cursor.getString(4));
            movieFavorite.setMovieReleaseDate(cursor.getString(5));

            movieFavorites.add(movieFavorite);

        } while (cursor.moveToNext());
    }
    return movieFavorites;
}
}