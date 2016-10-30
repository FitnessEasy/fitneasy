package com.gymapp.fitnesasy.DataManager;

import com.gymapp.fitnesasy.Model.DTO.FavoriteExercise;

import java.util.HashMap;



public class FavoriteExerciseDataManager extends DataManager<FavoriteExercise> {
    public FavoriteExerciseDataManager() {
        super(FavoriteExercise.class);
    }

    public static String email;
    HashMap<String, FavoriteExercise> favoriteExerciseHashMap = new HashMap<>();

    @Override
    void addValueToDataset(FavoriteExercise value,String key)
    {
        if(email!=null && value.getEmail() == email){
            favoriteExerciseHashMap.put(value.getExerID(),value);
        }
        value.setId(key);
        super.addValueToDataset(value, key);
    }


    public void changeLike(String exerciseID){
        if(checkLike(exerciseID)){
            unLike(exerciseID);
        }
        else
            like(exerciseID);
    }

    void like(String exerciseID){
        if(email==null)
            return;
        insert(new FavoriteExercise("",email,exerciseID));
    }

    void unLike(String exerciseID){
        delete(favoriteExerciseHashMap.get(exerciseID).getId());
    }

    public boolean checkLike(String exerciseID){

        if(email==null)
            return false;

        for (FavoriteExercise favoriteExercise : getDataset()){
            if(favoriteExercise.getExerID().contains(exerciseID) && email.contains( favoriteExercise.getEmail())){
                return true;
            }
        }
        return false;
        
    }



}
