package com.eliteinfoworld.shoppingapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eliteinfoworld.shoppingapp.R;

/**
 * Created by Avinash Kahal on 22-Sep-17.
 */

public class DesignerMen extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.frag_designer_men, container, false);
    }
}
