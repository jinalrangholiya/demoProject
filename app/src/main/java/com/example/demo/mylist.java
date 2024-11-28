package com.example.demo;

public class mylist {
    private String text,detail;
    private int image;

    public mylist(String s1,String details,int img) {
        this.image = img;
        this.text = s1;
        this.detail=details;

    }
    public String getText() {
        return text;
    }
    public String getDetail() {
        return detail;
    }

    public int getImage() {
        return image;
    }

}
