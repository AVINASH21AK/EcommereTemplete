package com.eliteinfoworld.shoppingapp.api.model;

import com.google.gson.annotations.SerializedName;

public class Explore2CategoriesModel {

    @SerializedName("categoryid")
    public String categoryid;

    @SerializedName("categoryimg")
    public String categoryimg;

    @SerializedName("categoryName")
    public String categoryName;

    @SerializedName("categorytotal")
    public String categorytotal;

    public Explore2CategoriesModel(String categoryid, String categoryimg, String categoryName, String categorytotal){
        this.categoryid = categoryid;
        this.categoryimg = categoryimg;
        this.categoryName = categoryName;
        this.categorytotal = categorytotal;

    }

}
