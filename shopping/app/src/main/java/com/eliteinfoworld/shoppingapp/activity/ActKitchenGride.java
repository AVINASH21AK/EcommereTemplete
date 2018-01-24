package com.eliteinfoworld.shoppingapp.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.eliteinfoworld.shoppingapp.R;
import com.eliteinfoworld.shoppingapp.api.model.KitchenGrideModel;
import com.eliteinfoworld.shoppingapp.utils.App;

import java.util.ArrayList;

public class ActKitchenGride extends BaseActivity {

    String TAG = "ActKitchenGride";
    RecyclerView recyclerViewKitchenGride;
    ArrayList<KitchenGrideModel> arrayListKitchenGride= new ArrayList<KitchenGrideModel>();
    KitchenGrideAdapter kitchenGrideAdapter;

    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.act_home);
        ViewGroup.inflate(this, R.layout.act_kitchen_gride, ll_SubMainContainer);

        //hide fab button on scroll
        /*
        1. https://stackoverflow.com/questions/34560770/hide-fab-in-nestedscrollview-when-scrolling
            OR https://mzgreen.github.io/2015/02/15/How-to-hideshow-Toolbar-when-list-is-scroling(part1)/
        2. https://gist.github.com/xdbas/74dfb9265a8dddea9a1e
        */


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
            ivMenu.setColorFilter(ContextCompat.getColor(ActKitchenGride.this, R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY);
            ivSearch.setColorFilter(ContextCompat.getColor(ActKitchenGride.this, R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY);


            /*----- This Activity -----*/
            appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
            recyclerViewKitchenGride = (RecyclerView)findViewById(R.id.recyclerViewKitchenGride);
            recyclerViewKitchenGride.setNestedScrollingEnabled(false);  //recycleview smooth scrool inside nestedScrollview


            setRecycleviewData();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickEvent(){
        try{

            /*-------- Collapsing layout scrool toolbar title is set  -----------*/
            appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                boolean isShow = false;
                int scrollRange = -1;

                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (scrollRange == -1) {
                        scrollRange = appBarLayout.getTotalScrollRange();
                    }
                    if (scrollRange + verticalOffset == 0) {
                        tvTitle.setText("Kitchen");
                        isShow = true;
                    } else if(isShow) {
                        tvTitle.setText(" ");//carefull there should a space between double quote otherwise it wont work
                        isShow = false;
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void setRecycleviewData(){
        try{

            //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            //recyclerViewKitchenGride.setLayoutManager(linearLayoutManager);

            int numberOfColumns = 2;
            recyclerViewKitchenGride.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
            recyclerViewKitchenGride.setHasFixedSize(true);



            String productid[] = {"1", "2", "3", "4", "5", "6"};
            int productimg[] = {R.drawable.img_transparent1, R.drawable.img_transparent2, R.drawable.img_transparent3,
                    R.drawable.img_transparent6, R.drawable.img_transparent5, R.drawable.img_transparent7};
            String productName[] = {"Air Conditioner", "Electric Fans", "Air Coolers", "Table Fan", "Exhaust Fans", "Air Ventilators"};
            String compamyName[] = {"Bharat Electronics", "Intex", "Voltas", "Intex", "Voltas", "Intex"};

            String productprice[] = {"514", "84", "215", "454", "545", "999"};
            String rating[] = {"3", "4", "2", "5", "3", "1"};

           

            for(int i=0; i<productid.length; i++){
                arrayListKitchenGride.add(new KitchenGrideModel(productid[i], String.valueOf(productimg[i]),
                        productName[i], compamyName[i], rating[i], productprice[i]));
            }


            kitchenGrideAdapter = new KitchenGrideAdapter(this, arrayListKitchenGride);
            recyclerViewKitchenGride.setAdapter(kitchenGrideAdapter);
            recyclerViewKitchenGride.setItemAnimator(new DefaultItemAnimator());

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public class KitchenGrideAdapter extends RecyclerView.Adapter<KitchenGrideAdapter.VersionViewHolder> {
        ArrayList<KitchenGrideModel> arrayListKitchenGride;
        Context mContext;



        public KitchenGrideAdapter(Context context, ArrayList<KitchenGrideModel> arrayListKitchenGride) {
            this.arrayListKitchenGride = arrayListKitchenGride;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_kitchengride, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
            try {
                versionViewHolder.ivImg.setImageResource(Integer.parseInt(arrayListKitchenGride.get(i).productimg));
                versionViewHolder.tvProductName.setText(arrayListKitchenGride.get(i).productName);
                versionViewHolder.tvCompanyName.setText(arrayListKitchenGride.get(i).compamyName);
                versionViewHolder.tvPrice.setText("$"+arrayListKitchenGride.get(i).productPrice);
                versionViewHolder.ratingBar.setRating(Float.parseFloat(arrayListKitchenGride.get(i).rating));

                LayerDrawable stars = (LayerDrawable) versionViewHolder.ratingBar.getProgressDrawable();
                stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.greyDark), PorterDuff.Mode.SRC_ATOP);
                stars.getDrawable(1).setColorFilter(getResources().getColor(R.color.greyDark), PorterDuff.Mode.SRC_ATOP);
                stars.getDrawable(0).setColorFilter(getResources().getColor(R.color.greyLight), PorterDuff.Mode.SRC_ATOP);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrayListKitchenGride.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder {
            ImageView ivImg, ivDots;
            TextView tvProductName, tvCompanyName, tvPrice;
            RatingBar ratingBar;

            public VersionViewHolder(View itemView) {
                super(itemView);

                ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
                ivDots = (ImageView) itemView.findViewById(R.id.ivDots);
                tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
                tvCompanyName = (TextView) itemView.findViewById(R.id.tvCompanyName);
                tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
                ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);


            }

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
