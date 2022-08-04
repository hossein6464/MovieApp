package diana.soleil.movieapp.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {

    // Optional ....................
    public DBOpenHelper(Context context){
        // Create the Database
        this(context,
             SQLCommands.DATABASE_NAME,
             null,
             Integer.parseInt(SQLCommands.SCHEMA_VERSION),
             null);
    }

    public DBOpenHelper(Context context,
                        String name,
                        SQLiteDatabase.CursorFactory factory,
                        int version) {

        super(context, name, factory, version);
    }
    // .............................

    public DBOpenHelper(Context context,
                        String name,
                        SQLiteDatabase.CursorFactory factory,
                        int version,
                        DatabaseErrorHandler errorHandler) {

        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table
        db.execSQL(SQLCommands.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}