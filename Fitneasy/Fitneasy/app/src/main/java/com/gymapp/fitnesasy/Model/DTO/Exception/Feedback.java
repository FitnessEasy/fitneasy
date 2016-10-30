package com.gymapp.fitnesasy.Model.DTO.Exception;


public class Feedback {
    public static Feedback getInstance() {
        return instance;
    }

    private static final Feedback instance = new Feedback();

    public Feedback(){

    }

    public void setFeedBackString(String errorStr){
        
    }
}
