package com.imdglobal.psi.api.database;

import android.content.ContentValues;

import com.google.gson.Gson;

/**
 * Created by rizkyriadhy on 19/06/17.
 */
public class LocalData {
    public static final boolean encrypted = false;

    public static class Type {
        public static final String PSI_DATE = "PSI_DATE";
        public static final String PSI_DATETIME = "PSI_DATETIME";
    }

    public static final String TABLE_NAME = "LOCAL_DATA_CACHE";
    public static final String TBL_COL_NAME = "name";
    public static final int TBL_COL_IDX_NAME = 0;
    public static final String TBL_COL_DATA = "data";
    public static final int TBL_COL_IDX_DATA = 1;
    public static final String TBL_COL_TIMESTAMP = "timestamp";
    public static final int TBL_COL_IDX_TIMESTAMP = 2;
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + TBL_COL_NAME + " TEXT, " + TBL_COL_DATA + " BLOB, " + TBL_COL_TIMESTAMP + " INTEGER);";

    public static <T> T getLocalData(String type, Class<T> classType) {
        try {
            android.database.sqlite.SQLiteDatabase db = ImdGlobalPSIDb.getInstance().getReadableDatabase();
            android.database.Cursor cursor = db.query(true, TABLE_NAME, new String[]{TBL_COL_DATA}, TBL_COL_NAME + " = ? ",
                    new String[]{type}, null, null, TBL_COL_NAME + " DESC", "1");

            T model = null;
            if (cursor != null && cursor.moveToFirst()) {
                Gson gson = new Gson();
                model = gson.fromJson(cursor.getString(cursor.getColumnIndex(TBL_COL_DATA)), classType);
            }
            cursor.close();
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T getLocalData(String type, java.lang.reflect.Type classType) {
        try {
            android.database.sqlite.SQLiteDatabase db = ImdGlobalPSIDb.getInstance().getReadableDatabase();
            android.database.Cursor cursor = db.query(true, TABLE_NAME, new String[]{TBL_COL_DATA}, TBL_COL_NAME + "=?",
                    new String[]{type}, null, null, TBL_COL_NAME + " DESC", "1");

            T model = null;
            if (cursor != null && cursor.moveToFirst()) {
                Gson gson = new Gson();
                model = gson.fromJson(cursor.getString(cursor.getColumnIndex(TBL_COL_DATA)), classType);
            }
            cursor.close();
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long getLocalDataTimestamp(String type) throws RuntimeException {
        try {
            android.database.sqlite.SQLiteDatabase db = ImdGlobalPSIDb.getInstance().getReadableDatabase();
            android.database.Cursor cursor = db.query(true, TABLE_NAME, new String[]{TBL_COL_TIMESTAMP}, TBL_COL_NAME + "=?",
                    new String[]{type}, null, null, TBL_COL_NAME + " DESC", "1");

            long timestamp = 0;
            if (cursor != null && cursor.moveToFirst()) {
                timestamp = cursor.getLong(cursor.getColumnIndex(TBL_COL_TIMESTAMP));
            }
            cursor.close();
            return timestamp;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static <M> boolean saveLocalData(String type, M model) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(model, model.getClass());

        return insertOrUpdateLocalData(type, jsonString);
    }

    public static <M> boolean saveLocalData(String type, M model, java.lang.reflect.Type classType) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(model, classType);

        return insertOrUpdateLocalData(type, jsonString);
    }

    private static boolean insertOrUpdateLocalData(String type, String jsonString) {
        try {
            ContentValues values = new ContentValues();
            values.put(TBL_COL_NAME, type);
            values.put(TBL_COL_DATA, jsonString);
            values.put(TBL_COL_TIMESTAMP, System.currentTimeMillis());
            android.database.sqlite.SQLiteDatabase db = ImdGlobalPSIDb.getInstance().getWritableDatabase();

            long count = db.update(TABLE_NAME, values, TBL_COL_NAME + " = ? ", new String[]{type});

            if (count > 0) {
                return true;
            } else {
                count = db.insert(TABLE_NAME, null, values);
                return (count > 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void clearAllCache() {
        android.database.sqlite.SQLiteDatabase db = ImdGlobalPSIDb.getInstance().getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
    }

    public static void clearCache(String type) {
        android.database.sqlite.SQLiteDatabase db = ImdGlobalPSIDb.getInstance().getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME + " where " + TBL_COL_NAME + " = '" + type + "'");
    }
}