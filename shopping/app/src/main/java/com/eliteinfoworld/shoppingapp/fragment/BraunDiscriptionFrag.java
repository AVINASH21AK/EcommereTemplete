package com.eliteinfoworld.shoppingapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.eliteinfoworld.shoppingapp.R;

/**
 * Created by Avinash Kahal on 22-Sep-17.
 */

public class BraunDiscriptionFrag extends Fragment {

    View viewFragment;
    WebView webview;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewFragment = inflater.inflate(R.layout.frag_braun_discriptn, container, false);

        try{

            initialize();


        }catch (Exception e){
            e.printStackTrace();
        }

        return viewFragment;
    }


    public void initialize(){

        try{
            webview = (WebView) viewFragment.findViewById(R.id.webview);

            String summary = "<html><body><b>MPAndroidChart</b> is a powerful & easy to use chart library for Android. It runs on API level 8 and upwards." +
                    "As an additional feature, this library allows cross-platform development between Android and iOS as an iOS version of this library is also available: Charts " +
                    "Are you using this library? Let me know about it and I will add your project to the references.</body></html>";

            webview.loadData(summary, "text/html; charset=utf-8", "utf-8");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
