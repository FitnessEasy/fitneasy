package com.gymapp.fitnesasy.Model.DTO;

import java.sql.Date;



public class ExerciseTimeTable {
    private int     id;
    private Date time;
    private String  userID;
    private String  info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ExerciseTimeTable(int ID, Date time, String userID, String info) {
        id = ID;
        this.time = time;
        this.userID = userID;
        this.info = info;
    }

    public ExerciseTimeTable(){

    }

}