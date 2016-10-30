package com.gymapp.fitnesasy.Model.DTO;



public class FavoriteExercise {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExerID() {
        return exerID;
    }

    public void setExerID(String exerID) {
        this.exerID = exerID;
    }

    private String id;
    private String email;
    private String exerID;

    public FavoriteExercise(){

    }

    public FavoriteExercise(String id, String email, String exerID){
        this.id = id;
        this.email = email;
        this.exerID = exerID;
    }
}
