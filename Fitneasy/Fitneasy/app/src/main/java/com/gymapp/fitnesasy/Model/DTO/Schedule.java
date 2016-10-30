package com.gymapp.fitnesasy.Model.DTO;



public class Schedule {
    String id;
    String image;
    String info;
    String name;
    String videoID;


    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public Schedule() {
    }

    public Schedule(String id, String image, String info, String name, String videoID) {
        this.id = id;
        this.image = image;
        this.info = info;
        this.name = name;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
