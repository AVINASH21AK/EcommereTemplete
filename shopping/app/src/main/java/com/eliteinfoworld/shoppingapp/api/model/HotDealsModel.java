package com.eliteinfoworld.shoppingapp.api.model;

import com.google.gson.annotations.SerializedName;

public class HotDealsModel {

    @SerializedName("dealId")
    public String dealId;

    @SerializedName("dealImg")
    public String dealImg;

    @SerializedName("dealName")
    public String dealName;

    @SerializedName("dealOldPrice")
    public String dealOldPrice;

    @SerializedName("dealNewPrice")
    public String dealNewPrice;

    @SerializedName("dealFullTime")
    public String dealFullTime;

    @SerializedName("dealLeftTime")
    public String dealLeftTime;

    public HotDealsModel(String dealId, String dealImg, String dealName, String dealOldPrice, String dealNewPrice, String dealFullTime, String dealLeftTime){
        this.dealId = dealId;
        this.dealImg = dealImg;
        this.dealName = dealName;
        this.dealOldPrice = dealOldPrice;
        this.dealNewPrice = dealNewPrice;
        this.dealFullTime = dealFullTime;
        this.dealLeftTime = dealLeftTime;
    }

}
