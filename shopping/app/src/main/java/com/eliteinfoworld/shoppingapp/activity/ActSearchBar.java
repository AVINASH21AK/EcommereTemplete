package com.eliteinfoworld.shoppingapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.eliteinfoworld.shoppingapp.R;
import com.eliteinfoworld.shoppingapp.api.model.KitchenGrideModel;

import java.util.ArrayList;

public class ActSearchBar extends BaseActivity {

    String TAG = "ActKitchenGride";
    RecyclerView recyclerViewKitchenGride;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.act_home);
        ViewGroup.inflate(this, R.layout.act_searchbar, ll_SubMainContainer);

        try{
            initialize();
            clickEvent();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void initialize(){
        try{

            /*----- BaseActivity -----*/
            tvTitle.setText("");
            ivMenu.setVisibility(View.GONE);
            ivSearch.setVisibility(View.GONE);

            etSearch.setVisibility(View.VISIBLE);
            ivBack.setVisibility(View.VISIBLE);
            ivDot.setVisibility(View.VISIBLE);
            ivCorssright.setVisibility(View.VISIBLE);



            /*----- This Activity -----*/
            recyclerViewKitchenGride = (RecyclerView)findViewById(R.id.recyclerViewKitchenGride);
            recyclerViewKitchenGride.setNestedScrollingEnabled(false);  //recycleview smooth scrool inside nestedScrollview




        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickEvent(){
        try{


            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        if (drawer.isDrawerOpen(left_drawer)) {
            drawer.closeDrawers();
        } else {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }

}
