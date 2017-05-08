package com.example.hdgregory.resumeapp.Data;


import android.content.Context;
import com.example.hdgregory.resumeapp.Model.Schedule;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import java.util.List;
import rx.Observable;
import rx.schedulers.Schedulers;

public class ScheduleManager {

    BriteDatabase briteDatabase;

    public ScheduleManager(Context context) {
        final SqlBrite sqlBrite = new SqlBrite.Builder().build();
        briteDatabase = sqlBrite.wrapDatabaseHelper(new DbOpenHelper(context), Schedulers.io());
    }

    public long insertSchedule(Schedule schedule) {
        return schedule.insertInto(briteDatabase);
    }

    public Observable<List<Schedule>> getSchedules() {
        return briteDatabase.createQuery(Schedule.TABLE, Schedule.GET_ALL_ENTRIES)
                .mapToList(Schedule.MAPPER);
    }
}