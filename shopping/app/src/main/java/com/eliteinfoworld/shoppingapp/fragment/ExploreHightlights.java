package com.eliteinfoworld.shoppingapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eliteinfoworld.shoppingapp.R;
import com.eliteinfoworld.shoppingapp.api.model.ExploreShoesModel;

import java.util.ArrayList;

/**
 * Created by Avinash Kahal on 22-Sep-17.
 */

public class ExploreHightlights extends Fragment {

    View viewFragment;
    RecyclerView recyclerViewShoes;
    ArrayList<ExploreShoesModel> arrModelExploreShoes = new ArrayList<ExploreShoesModel>();
    ShoesAdapter shoesAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.frag_explore_highlights, container, false);

        try{

            initialize();


        }catch (Exception e){
            e.printStackTrace();
        }

        return viewFragment;
    }

    public void initialize(){
        try{

            recyclerViewShoes = (RecyclerView) viewFragment.findViewById(R.id.recyclerViewShoes);
            recyclerViewShoes.setNestedScrollingEnabled(false);  //recycleview smooth scrool inside nestedScrollview

            setRecyclerViewShoes();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setRecyclerViewShoes(){
        try{

            //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            int numberOfColumns = 3;
            recyclerViewShoes.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));   //for Gride View with colorm numbers
            recyclerViewShoes.setHasFixedSize(true);


            String productid[] = {"1", "2", "3", "4", "5", "6"};
            int productimg[] = {R.drawable.img_transparent1, R.drawable.img_transparent2, R.drawable.img_transparent3,
                    R.drawable.img_transparent6, R.drawable.img_transparent5, R.drawable.img_transparent7};
            String productName[] = {"Air Conditioner", "Electric Fans", "Air Coolers", "Table Fan", "Exhaust Fans", "Air Ventilators"};
            String compamyName[] = {"Bharat Electronics", "Intex", "Voltas", "Intex", "Voltas", "Intex"};
            String rating[] = {"3", "4", "2", "5", "3", "1"};
            String price[] = {"412", "25", "130", "221", "150", "300"};

            for(int i=0; i<productid.length; i++){
                arrModelExploreShoes.add(new ExploreShoesModel(productid[i], String.valueOf(productimg[i]), productName[i], compamyName[i], rating[i], price[i]));
            }


            shoesAdapter = new ShoesAdapter(getActivity(), arrModelExploreShoes);
            recyclerViewShoes.setAdapter(shoesAdapter);
            recyclerViewShoes.setItemAnimator(new DefaultItemAnimator());

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public class ShoesAdapter extends RecyclerView.Adapter<ShoesAdapter.VersionViewHolder> {
        ArrayList<ExploreShoesModel> arrModelExploreShoes;
        Context mContext;



        public ShoesAdapter(Context context, ArrayList<ExploreShoesModel> arrModelExploreShoes) {
            this.arrModelExploreShoes = arrModelExploreShoes;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_explorefrag_newpro, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
            try {

                versionViewHolder.ivImg.setImageResource(Integer.parseInt(arrModelExploreShoes.get(i).productimg));
                versionViewHolder.tvProductName.setText(arrModelExploreShoes.get(i).productName);
                versionViewHolder.tvCompanyName.setText(arrModelExploreShoes.get(i).compamyName);
                versionViewHolder.tvPrice.setText("€"+arrModelExploreShoes.get(i).price);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrModelExploreShoes.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder {
            ImageView ivImg;
            TextView tvProductName, tvCompanyName, tvPrice;

            public VersionViewHolder(View itemView) {
                super(itemView);

                ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
                tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
                tvCompanyName = (TextView) itemView.findViewById(R.id.tvCompanyName);
                tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);

            }

        }
    }


}
