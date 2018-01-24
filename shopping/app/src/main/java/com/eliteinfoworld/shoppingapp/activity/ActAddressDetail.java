package com.eliteinfoworld.shoppingapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;
import com.eliteinfoworld.shoppingapp.R;
import com.eliteinfoworld.shoppingapp.api.model.KitchenGrideModel;

import java.util.ArrayList;
import java.util.Collections;

public class ActAddressDetail extends BaseActivity {

    String TAG = "ActAddressDetail";
    CountryCodePicker ccp;
    TextView tvReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.act_home);
        ViewGroup.inflate(this, R.layout.act_address_detail, ll_SubMainContainer);



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
            tvTitle.setText("Enter a new address");
            ivMenu.setVisibility(View.GONE);
            ivSearch.setVisibility(View.GONE);
            ivBack.setVisibility(View.VISIBLE);
            setEnableDrawer(false);  //closing menu bar



            /*----- This Activity -----*/
            ccp = (CountryCodePicker) findViewById(R.id.ccp);
            tvReset = (TextView)findViewById(R.id.tvReset);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickEvent(){
        try{
            ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
                @Override
                public void onCountrySelected() {
                    Snackbar.make(ccp, "Country: " + ccp.getSelectedCountryName(), Snackbar.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(), "Updated " + ccp.getSelectedCountryName(), Toast.LENGTH_SHORT).show();
                }
            });

            
            
            tvReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ActAddressDetail.this, "Clear all data", Toast.LENGTH_SHORT).show();
                }
            });
            
            
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
