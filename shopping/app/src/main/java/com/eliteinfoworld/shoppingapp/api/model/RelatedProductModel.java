package com.eliteinfoworld.shoppingapp.api.model;

import com.google.gson.annotations.SerializedName;

public class RelatedProductModel {

    @SerializedName("id")
    public String id;

    @SerializedName("img")
    public String img;

    @SerializedName("productName")
    public String productName;

    @SerializedName("productOldPrice")
    public String productOldPrice;

    @SerializedName("productNewPrice")
    public String productNewPrice;


    public RelatedProductModel(String id, String img, String productName, String productOldPrice, String productNewPrice){
        this.id = id;
        this.img = img;
        this.productName = productName;
        this.productOldPrice = productOldPrice;
        this.productNewPrice = productNewPrice;
    }

}
