package com.gymapp.fitnesasy.DataManager;

import com.gymapp.fitnesasy.Model.DTO.Exercise;

import java.util.ArrayList;



public class ExerciseDataManager extends DataManager<Exercise> {

    public static final ExerciseDataManager instance = new ExerciseDataManager();
    ArrayList<Exercise> exercisesByMuscleID = new ArrayList<>();

    public void setMuscleID(String muscleID) {
        this.muscleID = muscleID;
    }

    String muscleID;

    public ExerciseDataManager() {
        super(Exercise.class);
    }

    @Override
    void addValueToDataset(Exercise value, String key) {




        value.setId(key);

        super.addValueToDataset(value,key);
    }

    public ArrayList<Exercise> getExerciseByMuscleId(String muscleID) {


        return exercisesByMuscleID;
    }


}
