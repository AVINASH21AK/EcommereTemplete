package com.eliteinfoworld.shoppingapp.api.model;

import com.google.gson.annotations.SerializedName;

public class BaseTopMenuModel {

    @SerializedName("bid")
    public String bid;

    @SerializedName("bimg")
    public String bimg;

    @SerializedName("bname")
    public String bname;

    public BaseTopMenuModel(String bid, String bimg, String bname){
        this.bid = bid;
        this.bimg = bimg;
        this.bname = bname;
    }

}
