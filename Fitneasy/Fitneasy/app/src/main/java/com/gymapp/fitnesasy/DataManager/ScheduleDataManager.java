package com.gymapp.fitnesasy.DataManager;

import com.gymapp.fitnesasy.Model.DTO.Schedule;



public class ScheduleDataManager extends DataManager<Schedule> {
    public ScheduleDataManager() {
        super(Schedule.class);
    }

    @Override
    void addValueToDataset(Schedule value, String key) {

        value.setId(key);

        super.addValueToDataset(value,key);
    }












}