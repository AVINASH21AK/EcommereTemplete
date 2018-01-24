package com.eliteinfoworld.shoppingapp.api.responce;

import com.google.gson.annotations.SerializedName;
import com.eliteinfoworld.shoppingapp.api.model.HighLightBraunModel;
import com.eliteinfoworld.shoppingapp.api.model.RelatedProductModel;
import com.eliteinfoworld.shoppingapp.api.model.ReviewListModel;

import java.util.ArrayList;

/**
 * Created by Avinash Kahal on 23-Sep-17.
 */

public class BraunHighlightsResponse {

    @SerializedName("detailsOfProduct")
    private HighLightBraunModel highLightBraunModel;

    @SerializedName("ReviewList")
    public ArrayList<ReviewListModel> arrayReviewListModel;

    @SerializedName("RelatedProductLis")
    public ArrayList<RelatedProductModel> arrayRelatedProductListModel;


}
