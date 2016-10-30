package com.gymapp.fitnesasy.Model.DTO;


public class DemoData {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;

    public DemoData(int id, String value) {
        this.id = id;
        this.value = value;
    }
    public DemoData()
    {

    }
}
