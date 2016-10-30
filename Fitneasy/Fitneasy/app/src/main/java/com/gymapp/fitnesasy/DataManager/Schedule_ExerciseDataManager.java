package com.gymapp.fitnesasy.DataManager;

import com.gymapp.fitnesasy.Model.DTO.Schedule_Exercise;



public class Schedule_ExerciseDataManager extends DataManager<Schedule_Exercise> {
    public Schedule_ExerciseDataManager() {
        super(Schedule_Exercise.class);
    }

    @Override
    void addValueToDataset(Schedule_Exercise value, String key) {

        value.setId(key);

        super.addValueToDataset(value,key);
    }












}