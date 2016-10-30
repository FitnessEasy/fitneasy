package com.gymapp.fitnesasy.Model.DTO;



public class ExerciseSchedule {




    private String name;
    private String time;

    public ExerciseSchedule() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ExerciseSchedule(String name,String time){
        this.name = name;
        this.time = time;
    }
}