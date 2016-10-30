package com.gymapp.fitnesasy.Model.DTO;


public class ExerciseDetail {
    private String id;
    private String name;
    private String image;
    private String info;
    private String videoID;
    private String exerciseId;
    private long timeExercise;
    private long timeBreak;
    private long set;

    public long getSet() {
        return set;
    }

    public void setSet(long set) {
        this.set = set;
    }

    public long getTimeExercise() {
        return timeExercise;
    }

    public void setTimeExercise(long timeExercise) {
        this.timeExercise = timeExercise;
    }

    public long getTimeBreak() {
        return timeBreak;
    }

    public void setTimeBreak(long timeBreak) {
        this.timeBreak = timeBreak;
    }



    public ExerciseDetail(String id, String name, String image, String info, String videoID, String exerciseId, long timeExercise, long timeBreak) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.info = info;
        this.videoID = videoID;
        this.exerciseId = exerciseId;
        this.timeExercise = timeExercise;
        this.timeBreak = timeBreak;
    }

    public ExerciseDetail() {
    }

    public ExerciseDetail(String id, String exerciseId, String name, String image, String videoID, String info) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.info = info;
        this.videoID = videoID;
        this.exerciseId = exerciseId;
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

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }
}