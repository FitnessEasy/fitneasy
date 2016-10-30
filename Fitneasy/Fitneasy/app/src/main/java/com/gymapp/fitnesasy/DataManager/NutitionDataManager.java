package com.gymapp.fitnesasy.DataManager;

import com.gymapp.fitnesasy.Model.DTO.Nutrition;



public class NutitionDataManager extends DataManager<Nutrition> {
    public NutitionDataManager() {
        super(Nutrition.class);
    }

    @Override
    void addValueToDataset(Nutrition value, String key) {

        value.setId(key);

        super.addValueToDataset(value,key);
    }












}