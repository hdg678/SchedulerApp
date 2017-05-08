package com.example.hdgregory.resumeapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.hdgregory.resumeapp.Model.Schedule;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>  {

    private List<Schedule> schedules = new ArrayList<>();

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.schedule_item_layout, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int position) {
        Schedule schedule = schedules.get(position);
        holder.display(schedule);
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
        notifyDataSetChanged();
    }

    class ScheduleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.schedule_title) TextView scheduleTitleTv;
        @BindView(R.id.schedule_date) TextView scheduleDateTv;
        @BindView(R.id.schedule_time) TextView scheduleTimeTv;

        ScheduleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void display(Schedule schedule) {
            scheduleTitleTv.setText(schedule.title());
            scheduleDateTv.setText(schedule.date());
            scheduleTimeTv.setText(schedule.time());
        }
    }
}