package com.example.hdgregory.resumeapp.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import com.example.hdgregory.resumeapp.Model.Schedule;

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 6;
    private static final String APP_DB = "app.db";

    public DbOpenHelper(Context context) {
        super(context, APP_DB, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < 16) {
            db.execSQL("PRAGMA foreign_keys = ON;");
        }
        db.execSQL(Schedule.CREATE_SCHEDULE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != DB_VERSION) {
            recreateDb(db);
        }
    }

    private void recreateDb(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS "+ Schedule.TABLE);
        onCreate(db);
    }
}