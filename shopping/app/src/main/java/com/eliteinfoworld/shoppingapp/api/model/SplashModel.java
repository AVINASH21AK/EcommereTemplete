package com.eliteinfoworld.shoppingapp.api.model;

import com.google.gson.annotations.SerializedName;

public class SplashModel {

    @SerializedName("id")
    public String id;

    @SerializedName("bgimg")
    public String bgimg;


    @SerializedName("title")
    public String title;

    @SerializedName("subtitle")
    public String subtitle;


    public SplashModel(String id, String bgimg, String title, String subtitle){
        this.id = id;
        this.bgimg = bgimg;
        this.title = title;
        this.subtitle = subtitle;

    }

}
