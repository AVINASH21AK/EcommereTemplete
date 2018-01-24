package com.eliteinfoworld.shoppingapp.api.model;

import com.google.gson.annotations.SerializedName;

public class BaseBottomMenuModel {

    @SerializedName("bid")
    public String bid;

    @SerializedName("bimg")
    public String bimg;

    @SerializedName("bname")
    public String bname;

    public BaseBottomMenuModel(String bid, String bimg, String bname){
        this.bid = bid;
        this.bimg = bimg;
        this.bname = bname;
    }

}
