package com.eliteinfoworld.shoppingapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eliteinfoworld.shoppingapp.R;

/**
 * Created by Avinash Kahal on 22-Sep-17.
 */

public class BraunSpecificationFrag extends Fragment {

    View viewFragment;
    TextView tvProductHighlight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.frag_braun_specifictn, container, false);

        try{

            initialize();


        }catch (Exception e){
            e.printStackTrace();
        }

        return viewFragment;
    }


    public void initialize(){

        try{
            /* -----   For Bullets to settext ------ */
           //https://stackoverflow.com/questions/3429546/how-do-i-add-a-bullet-symbol-in-textview


            tvProductHighlight = (TextView) viewFragment.findViewById(R.id.tvProductHighlight);

            String strData = "MPAndroidChart is a powerful & easy to use chart library for Android." +
                    "It runs on API level 8 and upwards." +
                    "As an additional feature, this library allows cross-platform development between Android and iOS as an iOS version of this library is also available Charts.";

            String str[] = strData.split("\\.");
            String setData = "";

            for(int i=0; i<str.length; i++){
                setData = setData + "\u2022 " +str[i] + "\n";
            }

            tvProductHighlight.setText(setData);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
