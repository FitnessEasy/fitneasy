package com.gymapp.fitnesasy.Model.DTO;

import java.io.Serializable;



public class Exercise implements Serializable {
    private String id;
    private String name;
    private String image;
    private String muscleTypeID;
    private String info;
    private String videoID;
    private boolean like;


    public Exercise(String id, String name, String image, String muscleTypeID, String info, String videoID) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.muscleTypeID = muscleTypeID;
        this.info = info;
        this.videoID = videoID;
        this.like = false;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMuscleTypeID() {
        return muscleTypeID;
    }

    public void setMuscleTypeID(String muscleTypeID) {
        this.muscleTypeID = muscleTypeID;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public Exercise() {
    }
}
