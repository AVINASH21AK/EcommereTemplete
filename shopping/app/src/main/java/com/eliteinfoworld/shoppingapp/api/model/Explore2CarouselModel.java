package com.eliteinfoworld.shoppingapp.api.model;

import com.google.gson.annotations.SerializedName;

public class Explore2CarouselModel {

    @SerializedName("id")
    public String id;

    @SerializedName("img")
    public String img;

    @SerializedName("tvProductName")
    public String tvProductName;

    @SerializedName("tvTotalRating")
    public String tvTotalRating;

    @SerializedName("tvRating")
    public String tvRating;

    @SerializedName("tvPrice")
    public String tvPrice;

    public Explore2CarouselModel(String id, String img, String tvProductName, String tvTotalRating, String tvRating, String tvPrice){
        this.id = id;
        this.img = img;
        this.tvProductName = tvProductName;
        this.tvTotalRating = tvTotalRating;
        this.tvRating = tvRating;
        this.tvPrice = tvPrice;
    }

}
