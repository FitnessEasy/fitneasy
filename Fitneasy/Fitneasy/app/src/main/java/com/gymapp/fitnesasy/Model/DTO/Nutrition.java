package com.gymapp.fitnesasy.Model.DTO;

import java.io.Serializable;



public class Nutrition implements Serializable {
    private String id;
    private String name;
    private String image;
    private String info;
    private String videoID;

    public Nutrition() {
    }

    public Nutrition(String id, String name, String image, String info, String videoID) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.info = info;
        this.videoID = videoID;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
