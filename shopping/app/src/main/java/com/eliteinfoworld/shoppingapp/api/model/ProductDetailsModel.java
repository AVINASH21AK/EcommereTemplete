package com.eliteinfoworld.shoppingapp.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductDetailsModel {

    @SerializedName("id")
    public String id;

    @SerializedName("img")
    public String img;

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("isButton")
    public String isButton;



    public ProductDetailsModel(String id, String img, String title, String description, String isButton){
        this.id = id;
        this.img = img;
        this.title = title;
        this.description = description;
        this.isButton= isButton;
    }

}
