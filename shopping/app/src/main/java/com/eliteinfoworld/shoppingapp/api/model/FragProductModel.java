package com.eliteinfoworld.shoppingapp.api.model;

import com.google.gson.annotations.SerializedName;

public class FragProductModel {

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;



    public FragProductModel(String id, String name){
        this.id = id;
        this.name = name;
    }

}
