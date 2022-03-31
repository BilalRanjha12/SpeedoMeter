package com.example.userprofile;

public class Food {
    String name;
    String detail;
    int img;

    public Food(String name, String detail, int img) {
        this.name = name;
        this.detail = detail;
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public int getImg() {
        return Integer.parseInt(String.valueOf(img));
    }
}

