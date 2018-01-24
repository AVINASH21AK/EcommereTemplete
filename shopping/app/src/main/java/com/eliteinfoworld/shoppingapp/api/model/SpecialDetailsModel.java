package com.eliteinfoworld.shoppingapp.api.model;

import com.google.gson.annotations.SerializedName;

public class SpecialDetailsModel {

    @SerializedName("productid")
    public String productid;


    @SerializedName("cardImg")
    public String cardImg;

    @SerializedName("cardName")
    public String cardName;

    @SerializedName("cardNumber")
    public String cardNumber;




    public SpecialDetailsModel(String productid, String cardImg, String cardName, String cardNumber){
        this.productid = productid;
        this.cardImg = cardImg;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
    }

}
