package com.eliteinfoworld.shoppingapp.api.model;

import com.google.gson.annotations.SerializedName;

public class ShippingDetailsModel {

    @SerializedName("productid")
    public String productid;


    @SerializedName("compamyName")
    public String compamyName;

    @SerializedName("days")
    public String days;

    @SerializedName("price")
    public String price;

    @SerializedName("checkbox")
    public String checkbox;



    public ShippingDetailsModel(String productid, String compamyName, String days, String price, String checkbox){
        this.productid = productid;
        this.compamyName = compamyName;
        this.days = days;
        this.price = price;
        this.checkbox = checkbox;
    }

}
