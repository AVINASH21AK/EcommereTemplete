package com.eliteinfoworld.shoppingapp.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GroupModel {

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("childModel")
    public ArrayList<FragProductModel> childModel;



    public GroupModel(String id, String name, ArrayList<FragProductModel> childModel){
        this.id = id;
        this.name = name;
        this.childModel= childModel;
    }


    /*
    public ArrayList<FragProductModel> getChildModel() {
        return childModel;
    }

    public void setChildModel(ArrayList<FragProductModel> childModel) {
        this.childModel = childModel;
    }*/

}
