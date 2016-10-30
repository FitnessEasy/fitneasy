package com.gymapp.fitnesasy.DataManager;

import com.gymapp.fitnesasy.Model.DTO.MuscleType;

import java.util.ArrayList;



public class MuscleTypeDataManager extends DataManager<MuscleType> {

    public static final MuscleTypeDataManager instance = new MuscleTypeDataManager();

    public MuscleTypeDataManager() {
        super(MuscleType.class);
    }

    @Override
    void addValueToDataset(MuscleType value, String key) {




        value.setId(key);

        super.addValueToDataset(value,key);
    }

    public ArrayList<MuscleType> getMuscleTypeByName(String name) {
        ArrayList<MuscleType> muscleTypesByName = new ArrayList<>();

        for(MuscleType muscleType : dataset){
            if(muscleType.getName().contains(name)){
                muscleTypesByName.add(muscleType);
            }
        }
        return  muscleTypesByName;
    }

}