package com.eliteinfoworld.shoppingapp.api.model;

import com.google.gson.annotations.SerializedName;

public class CheckoutModel {

    @SerializedName("productid")
    public String productid;

    @SerializedName("productimg")
    public String productimg;

    @SerializedName("productName")
    public String productName;

    @SerializedName("compamyName")
    public String compamyName;

    @SerializedName("productQty")
    public String productQty;

    @SerializedName("price")
    public String price;



    public CheckoutModel(String productid, String productimg, String productName, String compamyName, String productQty, String price){
        this.productid = productid;
        this.productimg = productimg;
        this.productName = productName;
        this.compamyName = compamyName;
        this.productQty = productQty;
        this.price = price;
    }

}
