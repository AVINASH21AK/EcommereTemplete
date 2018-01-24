package com.eliteinfoworld.shoppingapp.api.model;

import com.google.gson.annotations.SerializedName;

public class ReviewListModel {

    @SerializedName("userId")
    public String userId;

    @SerializedName("userImg")
    public String userImg;

    @SerializedName("userName")
    public String userName;

    @SerializedName("userRating")
    public String userRating;

    @SerializedName("commentTitle")
    public String commentTitle;

    @SerializedName("userComment")
    public String userComment;


    public ReviewListModel(String userId, String userImg, String userName, String userRating, String commentTitle, String userComment){
        this.userId = userId;
        this.userImg = userImg;
        this.userName = userName;
        this.userRating = userRating;
        this.commentTitle = commentTitle;
        this.userComment = userComment;
    }

}
