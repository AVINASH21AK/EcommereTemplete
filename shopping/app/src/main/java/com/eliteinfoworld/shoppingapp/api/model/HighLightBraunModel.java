package com.eliteinfoworld.shoppingapp.api.model;

import com.google.gson.annotations.SerializedName;

public class HighLightBraunModel {

    @SerializedName("img")
    public String img;

    @SerializedName("productName")
    public String productName;

    @SerializedName("productPrice")
    public String productPrice;

    @SerializedName("productDiscription")
    public String productDiscription;

    @SerializedName("productAvgPt")
    public String productAvgPt;

    @SerializedName("ratingBarAvg")
    public String ratingBarAvg;

    @SerializedName("productTotalReview")
    public String productTotalReview;

    public HighLightBraunModel(String img, String productName, String productPrice,
                               String productDiscription, String productAvgPt, String ratingBarAvg, String productTotalReview){
        this.img = img;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDiscription = productDiscription;
        this.productAvgPt = productAvgPt;
        this.ratingBarAvg = ratingBarAvg;
        this.productTotalReview = productTotalReview;
    }

}
