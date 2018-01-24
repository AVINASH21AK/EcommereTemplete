package com.eliteinfoworld.shoppingapp.api.model;

import com.google.gson.annotations.SerializedName;

public class ProductMayLikedModel {

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

    @SerializedName("price")
    public String price;

    public ProductMayLikedModel(String productid, String productimg, String productName, String compamyName, String rating, String price){
        this.productid = productid;
        this.productimg = productimg;
        this.productName = productName;
        this.compamyName = compamyName;
        this.rating = rating;
        this.price = price;
    }

}
