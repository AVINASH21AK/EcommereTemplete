package com.eliteinfoworld.shoppingapp.api.model;

import com.google.gson.annotations.SerializedName;

public class KitchenModel {

    @SerializedName("layoutType")
    public String layoutType;

    @SerializedName("productid")
    public String productid;

    @SerializedName("productimg")
    public String productimg;

    @SerializedName("productName")
    public String productName;

    @SerializedName("compamyName")
    public String compamyName;

    @SerializedName("rating")
    public String rating;

    @SerializedName("productNewPrice")
    public String productNewPrice;

    @SerializedName("productOldPrice")
    public String productOldPrice;



    public KitchenModel(String layoutType, String productid, String productimg, String productName, String compamyName, String rating, String productNewPrice, String productOldPrice){
        this.layoutType = layoutType;
        this.productid = productid;
        this.productimg = productimg;
        this.productName = productName;
        this.compamyName = compamyName;
        this.rating = rating;
        this.productNewPrice = productNewPrice;
        this.productOldPrice = productOldPrice;
    }

}
