package com.example.hdgregory.resumeapp.Model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import auto.parcel.AutoParcel;
import com.example.hdgregory.resumeapp.Data.OkCursor;
import com.squareup.sqlbrite.BriteDatabase;
import rx.functions.Func1;


@AutoParcel
public abstract class Schedule implements Parcelable {

    public static final String TABLE = "schedule";
    public static final String ID = "schedule_id";
    public static final String TITLE = "schedule_title";
    private static final String DATE = "date";
    private static final String TIME = "time";

    public static final String CREATE_SCHEDULE = "CREATE TABLE "
            + TABLE
            + "( "
            + ID
            + " INTEGER NON NULL PRIMARY KEY, "
            + TITLE
            + " TEXT NOT NULL, "
            + DATE
            + " TEXT ,"
            + TIME
            + " TEXT ) ";
    public static final String GET_ALL_ENTRIES = "SELECT * FROM " + Schedule.TABLE;
    public static final Func1<Cursor, Schedule> MAPPER = new Func1<Cursor, Schedule>() {
        @Override
        public Schedule call(Cursor cursor) {
            return create(new OkCursor(cursor));
        }
    };

    private static Schedule create(OkCursor okCursor) {
        final long id = okCursor.getLong(Schedule.ID);
        final String title = okCursor.getString(Schedule.TITLE);
        final String date = okCursor.getString(Schedule.DATE);
        final String time = okCursor.getString(Schedule.TIME);
        return Schedule.create(id, title, date, time);
    }

    public static Schedule create(long id, String title, String date, String time) {
        return new AutoParcel_Schedule(id, title, date, time);
    }

    public abstract long id();

    public abstract String title();

    public abstract String date();

    public abstract String time();

    public long insertInto(BriteDatabase briteDatabase) {
        return briteDatabase.insert(TABLE, toContentValues());
    }

    private ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(TITLE, title());
        values.put(DATE, date());
        values.put(TIME, time());
        return values;
    }
}