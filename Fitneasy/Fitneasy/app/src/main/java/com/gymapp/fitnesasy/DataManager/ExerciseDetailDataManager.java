package com.gymapp.fitnesasy.DataManager;


import com.gymapp.fitnesasy.Model.DTO.Exercise;
import com.gymapp.fitnesasy.Model.DTO.ExerciseDetail;

import java.util.ArrayList;




public class ExerciseDetailDataManager extends DataManager<ExerciseDetail> {
    public ExerciseDetailDataManager() {
        super(ExerciseDetail.class);
    }


    public static final ExerciseDetailDataManager instance = new ExerciseDetailDataManager();

    public ArrayList<Exercise> getExercisesByMuscleTypeID() {
        return exercisesByMuscleTypeID;
    }

    ArrayList<Exercise> exercisesByMuscleTypeID = new ArrayList<>();

    public String getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(String exerciseID) {
        this.exerciseID = exerciseID;
    }

    private String exerciseID;

    @Override
    void addValueToDataset(ExerciseDetail value, String key) {
        value.setId(key);
        super.addValueToDataset(value,key);
    }


}
