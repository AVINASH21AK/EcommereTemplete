package com.eliteinfoworld.shoppingapp.fragment;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.eliteinfoworld.shoppingapp.R;
import com.eliteinfoworld.shoppingapp.api.model.BraunForHomeModel;

import java.util.ArrayList;

/**
 * Created by Avinash Kahal on 22-Sep-17.
 */

public class BraunHome extends Fragment {

    View viewFragment;
    RecyclerView recyclerViewShoes;
    ArrayList<BraunForHomeModel> arrModelForHome= new ArrayList<BraunForHomeModel>();
    ForHomeAdapter forHomeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.frag_braun_home, container, false);

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

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerViewShoes.setLayoutManager(linearLayoutManager);
            recyclerViewShoes.setHasFixedSize(true);


            String productid[] = {"1", "2", "3", "4", "5", "6"};
            int productimg[] = {R.drawable.img_transparent1, R.drawable.img_transparent2, R.drawable.img_transparent3,
                    R.drawable.img_transparent6, R.drawable.img_transparent5, R.drawable.img_transparent7};
            String productName[] = {"Wall-mounted White Plastic CD Player", "Cone Clock with braun Pocket Calculator", "Air Coolers", "Table Fan", "Exhaust Fans", "Air Ventilators"};
            String compamyName[] = {"Bharat Electronics", "Intex", "Voltas", "Intex", "Voltas", "Intex"};
            String price[] = {"412", "25", "130", "221", "150", "300"};
            String rating[] = {"3", "4", "2", "5", "3", "1"};
            String totalRating[] = {"412", "25", "130", "221", "150", "300"};
            String favorite[] = {"1", "0", "0", "1", "0", "1"};

            for(int i=0; i<productid.length; i++){
                arrModelForHome.add(new BraunForHomeModel(productid[i], String.valueOf(productimg[i]),
                        productName[i], compamyName[i], rating[i], price[i], totalRating[i], favorite[i]));
            }


            forHomeAdapter = new ForHomeAdapter(getActivity(), arrModelForHome);
            recyclerViewShoes.setAdapter(forHomeAdapter);
            recyclerViewShoes.setItemAnimator(new DefaultItemAnimator());

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public class ForHomeAdapter extends RecyclerView.Adapter<ForHomeAdapter.VersionViewHolder> {
        ArrayList<BraunForHomeModel> arrModelForHome;
        Context mContext;



        public ForHomeAdapter(Context context, ArrayList<BraunForHomeModel> arrModelForHome) {
            this.arrModelForHome = arrModelForHome;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_braunhome_frag, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
            try {

                versionViewHolder.ivImg.setImageResource(Integer.parseInt(arrModelForHome.get(i).productimg));


                versionViewHolder.tvProductName.setText(arrModelForHome.get(i).productName);
                versionViewHolder.tvCompanyName.setText(arrModelForHome.get(i).compamyName);
                versionViewHolder.tvPrice.setText("$"+arrModelForHome.get(i).price);
                versionViewHolder.tvTotalRating.setText(arrModelForHome.get(i).totalrating);
                versionViewHolder.tvRatingBar.setRating(Float.parseFloat(arrModelForHome.get(i).rating));

                LayerDrawable stars = (LayerDrawable)  versionViewHolder.tvRatingBar.getProgressDrawable();
                stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.greyDark), PorterDuff.Mode.SRC_ATOP);
                stars.getDrawable(1).setColorFilter(getResources().getColor(R.color.greyDark), PorterDuff.Mode.SRC_ATOP);
                stars.getDrawable(0).setColorFilter(getResources().getColor(R.color.greyLight), PorterDuff.Mode.SRC_ATOP);

                if(arrModelForHome.get(i).Favorite.equalsIgnoreCase("1")){
                    versionViewHolder.tvFavorite.setImageResource(R.drawable.ic_bookmark_orange_18dp);
                }else {
                    versionViewHolder.tvFavorite.setImageResource(R.drawable.ic_bookmark_grey_18dp);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrModelForHome.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder {
            ImageView ivImg, tvFavorite;
            TextView tvProductName, tvCompanyName, tvPrice, tvTotalRating;
            RatingBar tvRatingBar;

            public VersionViewHolder(View itemView) {
                super(itemView);

                ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
                tvFavorite = (ImageView) itemView.findViewById(R.id.tvFavorite);
                tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
                tvCompanyName = (TextView) itemView.findViewById(R.id.tvCompanyName);
                tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
                tvTotalRating = (TextView) itemView.findViewById(R.id.tvTotalRating);
                tvRatingBar = (RatingBar) itemView.findViewById(R.id.tvRatingBar);

            }

        }
    }


}
