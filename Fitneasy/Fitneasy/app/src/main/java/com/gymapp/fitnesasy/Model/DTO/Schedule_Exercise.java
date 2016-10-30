package com.gymapp.fitnesasy.Model.DTO;



public class Schedule_Exercise {
    String id;
    String scheduleId;
    String exerciseId;
    int tuan;
    String info;


    public Schedule_Exercise(String id, String scheduleId, String exerciseId, int tuan, String info) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.exerciseId = exerciseId;
        this.tuan = tuan;
        this.info = info;
    }

    public int getTuan() {
        return tuan;
    }

    public void setTuan(int tuan) {
        this.tuan = tuan;
    }

    public Schedule_Exercise() {
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
