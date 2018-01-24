package com.eliteinfoworld.shoppingapp.api.model;

import com.google.gson.annotations.SerializedName;

public class HomeViewPagerModel {

    @SerializedName("id")
    public String id;

    @SerializedName("img")
    public String img;

    @SerializedName("title")
    public String title;

    @SerializedName("content")
    public String content;

    public HomeViewPagerModel(String id, String img, String title, String content){
        this.id = id;
        this.img = img;
        this.title = title;
        this.content = content;
    }

}
