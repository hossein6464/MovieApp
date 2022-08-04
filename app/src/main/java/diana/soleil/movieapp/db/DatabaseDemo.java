package diana.soleil.movieapp.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import diana.soleil.movieapp.model.Movie;
import diana.soleil.movieapp.model.MovieFavorite;


public class DatabaseDemo {

    ArrayList<MovieFavorite> movieFavoriteArrayList;
    ArrayList<MovieFavorite> movieFavoriteArrayListGiven;

    // Database .....................
    DBManager dbManager;
    Cursor cursor;
    //...............................

    public DatabaseDemo(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public void insertDummyDataToDB(ArrayList<MovieFavorite> favoriteArrayListOf) {
        ArrayList<ContentValues> contentValuesArrayList = dbManager.javaObjectToContentValue(favoriteArrayListOf);
        dbManager.insertInTable(SQLCommands.TABLE_NAME, contentValuesArrayList);
    }
    public void insertOneDataToDB(MovieFavorite movieFavorite) {
        ContentValues contentValues = dbManager.javaObjectToOneContentValue(movieFavorite);
        dbManager.insertInTable(SQLCommands.TABLE_NAME, contentValues);
    }

    public Cursor readFromDB() {
        cursor = dbManager.queryInTable(
                SQLCommands.TABLE_NAME,
                SQLCommands.TABLE_COLUMNS,
                null,
                null);
        System.out.println("----------------------- Read from database");
        System.out.println(dbManager.cursorToArrayList(cursor));
        return  cursor;
    }

    public void deleteFromDB(int id) {
        dbManager.deleteRowFromTable(SQLCommands.TABLE_NAME,
                SQLCommands.COLUMN_ID + "=?"
                , new String[]{String.valueOf(id)});

        readFromDB();
    }
}