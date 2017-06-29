package com.imdglobal.psi.api.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.imdglobal.psi.ImdGlobalPSIApplication;

/**
 * Created by rizkyriadhy on 19/06/17.
 */
public class ImdGlobalPSIDb extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "imdglobal.db";
    public static int DATABASE_VERSION = 2;

    private static ImdGlobalPSIDb instance = null;

    private ImdGlobalPSIDb() {
        super(ImdGlobalPSIApplication.context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Lazy Initialization (If required then only)
    public static ImdGlobalPSIDb getInstance() {
        if (instance == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (ImdGlobalPSIDb.class) {
                if (instance == null) {
                    instance = new ImdGlobalPSIDb();
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(LocalData.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(ImdGlobalPSIDb.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + LocalData.TABLE_NAME);
        onCreate(db);
    }
}
