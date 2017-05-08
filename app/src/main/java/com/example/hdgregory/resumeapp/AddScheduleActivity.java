package com.example.hdgregory.resumeapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.hdgregory.resumeapp.Data.ScheduleManager;
import com.example.hdgregory.resumeapp.Model.Schedule;

public class AddScheduleActivity extends AppCompatActivity
        implements DatePickerFragment.DateSetListener, TimePickerFragment.TimeSetListener {

    @BindView(R.id.event_title_et) EditText eventNameEt;
    @BindView(R.id.choose_schedule_date) TextView chooseScheduleDateTv;
    @BindView(R.id.choose_schedule_time) TextView chooseScheduleTimeTv;

    private ScheduleManager scheduleManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_schedule_layout);
        ButterKnife.bind(this);
        scheduleManager = ((MyApp) getApplicationContext()).getScheduleManager();
    }

    @OnClick(R.id.choose_schedule_date)
    void onChooseScheduleDate() {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "DatePicker");
        fragment.setDateListener(this);
    }

    @Override
    public void onDateSet(int year, int month, int dayOfMonth) {
        chooseScheduleDateTv.setText(year + "/" + month + "/" + dayOfMonth);
    }

    @OnClick(R.id.choose_schedule_time)
    void onChooseScheduleTime() {
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.show(getSupportFragmentManager(), "DatePicker");
        fragment.setTimeListener(this);
    }

    @Override
    public void onTimeSet(int hourOfDay, int minute) {
        chooseScheduleTimeTv.setText(hourOfDay + ":" + minute);
    }

    @OnClick(R.id.save)
    void onClickSave() {
        scheduleManager.insertSchedule(Schedule.create(-1, eventNameEt.getText().toString(),
                chooseScheduleDateTv.getText().toString(), chooseScheduleTimeTv.getText().toString()));
        setResult(RESULT_OK);
        finish();
    }
}