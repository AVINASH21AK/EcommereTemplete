package com.eliteinfoworld.shoppingapp.api.model;

import com.google.gson.annotations.SerializedName;

public class FragmentModel {

    @SerializedName("id")
    public String id;

    @SerializedName("fragName")
    public String fragName;


    public FragmentModel(String id, String fragName){
        this.id = id;
        this.fragName = fragName;
    }

}
