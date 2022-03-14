package com.example.myapplication;

public class List_Custom {
    private String user_name;
    private int imgId;
    public List_Custom(String description, int imgId) {
        this.user_name = description;
        this.imgId = imgId;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
