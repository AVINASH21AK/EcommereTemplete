package com.eliteinfoworld.shoppingapp.api.model;

import com.google.gson.annotations.SerializedName;

public class KitchenRBModel {


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

    @SerializedName("productPrice")
    public String productPrice;




    public KitchenRBModel(String productid, String productimg, String productName, String compamyName, String rating, String productPrice){
        this.productid = productid;
        this.productimg = productimg;
        this.productName = productName;
        this.compamyName = compamyName;
        this.rating = rating;
        this.productPrice = productPrice;
    }

}
