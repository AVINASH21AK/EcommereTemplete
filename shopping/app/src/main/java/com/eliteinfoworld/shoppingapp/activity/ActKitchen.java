package com.eliteinfoworld.shoppingapp.activity;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eliteinfoworld.shoppingapp.R;
import com.eliteinfoworld.shoppingapp.api.model.KitchenModel;
import com.eliteinfoworld.shoppingapp.utils.App;

import java.util.ArrayList;

public class ActKitchen extends BaseActivity {

    String TAG = "ActKitchen";
    RecyclerView recyclerViewKitchen;
    ArrayList<KitchenModel> arrayListKitchen= new ArrayList<KitchenModel>();
    KitchenAdapter kitchenAdapter;
    FloatingActionButton fabKitchen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.act_home);
        ViewGroup.inflate(this, R.layout.act_kitchen, ll_SubMainContainer);

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
            ivMenu.setVisibility(View.GONE);
            ivBack.setVisibility(View.VISIBLE);
            tvTitle.setText("Kitchen");
            setEnableDrawer(false);  //closing menu bar


            /*----- This Activity -----*/
            fabKitchen = (FloatingActionButton) findViewById(R.id.fabKitchen);
            recyclerViewKitchen = (RecyclerView)findViewById(R.id.recyclerViewKitchen);
            recyclerViewKitchen.setNestedScrollingEnabled(false);  //recycleview smooth scrool inside nestedScrollview


            setRecycleviewData();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickEvent(){
        try{

            fabKitchen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    App.showSnackBar(v, "Fab button click");
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


    public void setRecycleviewData(){
        try{

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerViewKitchen.setLayoutManager(linearLayoutManager);
            recyclerViewKitchen.setHasFixedSize(true);


            String layoutType[] = {"vertical", "vertical", "horizontal", "vertical", "vertical", "horizontal"};
            String productid[] = {"1", "2", "3", "4", "5", "6"};
            int productimg[] = {R.drawable.img_transparent1, R.drawable.img_transparent7, R.drawable.img_transparent3,
                    R.drawable.img_transparent6, R.drawable.img_transparent5, R.drawable.img_transparent7};
            String productName[] = {"A Product", "B Product", "C Product", "D Product", "E Product", "F Product"};
            String compamyName[] = {"Bharat Electronics", "Intex", "Voltas", "Intex", "Voltas", "Intex"};

            String oldprice[] = {"514", "84", "215", "454", "545", "999"};
            String newprice[] = {"412", "25", "130", "221", "150", "300"};
            String rating[] = {"3", "4", "2", "5", "3", "1"};

           

            for(int i=0; i<productid.length; i++){
                arrayListKitchen.add(new KitchenModel(layoutType[i], productid[i], String.valueOf(productimg[i]),
                        productName[i], compamyName[i], rating[i], oldprice[i], newprice[i]));
            }


            kitchenAdapter = new KitchenAdapter(this, arrayListKitchen);
            recyclerViewKitchen.setAdapter(kitchenAdapter);
            recyclerViewKitchen.setItemAnimator(new DefaultItemAnimator());

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public class KitchenAdapter extends RecyclerView.Adapter<KitchenAdapter.VersionViewHolder> {
        ArrayList<KitchenModel> arrayListKitchen;
        Context mContext;
        Boolean isV2 = true;


        public KitchenAdapter(Context context, ArrayList<KitchenModel> arrayListKitchen) {
            this.arrayListKitchen = arrayListKitchen;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_kitchen_vertical, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder versionViewHolder, int i) {
            try {

                if(arrayListKitchen.get(i).layoutType.equalsIgnoreCase("horizontal")){
                    versionViewHolder.rl_horizontal.setVisibility(View.VISIBLE);
                    versionViewHolder.rl_vertical.setVisibility(View.GONE);

                    versionViewHolder.ivImgH.setImageResource(Integer.parseInt(arrayListKitchen.get(i).productimg));
                    versionViewHolder.tvProNmH.setText(arrayListKitchen.get(i).productName);
                    versionViewHolder.tvProOldPriceH.setText("$"+arrayListKitchen.get(i).productOldPrice);
                    versionViewHolder.tvPriceH.setText("$"+arrayListKitchen.get(i).productNewPrice);

                }else {

                    if(isV2){
                        versionViewHolder.rl_vertical.setVisibility(View.VISIBLE);
                        versionViewHolder.cardViewV2.setVisibility(View.GONE);
                        versionViewHolder.rl_horizontal.setVisibility(View.GONE);

                        versionViewHolder.ivImgV1.setImageResource(Integer.parseInt(arrayListKitchen.get(i).productimg));
                        versionViewHolder.tvProNmV1.setText(arrayListKitchen.get(i).productName);
                        versionViewHolder.tvComNmV1.setText(arrayListKitchen.get(i).compamyName);
                        versionViewHolder.tvPriceV1.setText("$"+arrayListKitchen.get(i).productNewPrice);
                        versionViewHolder.tvRatingV1.setRating(Float.parseFloat(arrayListKitchen.get(i).rating));

                        LayerDrawable stars1 = (LayerDrawable) versionViewHolder.tvRatingV1.getProgressDrawable();
                        stars1.getDrawable(2).setColorFilter(getResources().getColor(R.color.greyDark), PorterDuff.Mode.SRC_ATOP);
                        stars1.getDrawable(1).setColorFilter(getResources().getColor(R.color.greyDark), PorterDuff.Mode.SRC_ATOP);
                        stars1.getDrawable(0).setColorFilter(getResources().getColor(R.color.greyLight), PorterDuff.Mode.SRC_ATOP);

                        isV2 = true;

                        if(arrayListKitchen.get(i+1).layoutType.equalsIgnoreCase("vertical")){
                            versionViewHolder.cardViewV2.setVisibility(View.VISIBLE);
                            versionViewHolder.ivImgV2.setImageResource(Integer.parseInt(arrayListKitchen.get(i+1).productimg));
                            versionViewHolder.tvProNmV2.setText(arrayListKitchen.get(i+1).productName);
                            versionViewHolder.tvComNmV2.setText(arrayListKitchen.get(i+1).compamyName);
                            versionViewHolder.tvPriceV2.setText("$"+arrayListKitchen.get(i+1).productNewPrice);
                            versionViewHolder.tvRatingV2.setRating(Float.parseFloat(arrayListKitchen.get(i+1).rating));

                            LayerDrawable stars2 = (LayerDrawable) versionViewHolder.tvRatingV2.getProgressDrawable();
                            stars2.getDrawable(2).setColorFilter(getResources().getColor(R.color.greyDark), PorterDuff.Mode.SRC_ATOP);
                            stars2.getDrawable(1).setColorFilter(getResources().getColor(R.color.greyDark), PorterDuff.Mode.SRC_ATOP);
                            stars2.getDrawable(0).setColorFilter(getResources().getColor(R.color.greyLight), PorterDuff.Mode.SRC_ATOP);

                            isV2 = false;
                        }
                    }else {
                        isV2 = true;
                        versionViewHolder.rl_vertical.setVisibility(View.GONE);
                        versionViewHolder.rl_horizontal.setVisibility(View.GONE);
                    }



                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrayListKitchen.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder {
            ImageView ivImgH;
            ImageView ivImgV1, ivImgV2;

            TextView tvProNmH, tvPriceH, tvProOldPriceH;
            TextView tvProNmV1, tvComNmV1, tvPriceV1,    tvProNmV2, tvComNmV2, tvPriceV2;

            RatingBar tvRatingV1, tvRatingV2;
            RelativeLayout rl_horizontal, rl_vertical;

            CardView cardViewV1, cardViewV2;


            public VersionViewHolder(View itemView) {
                super(itemView);

                cardViewV1 = (CardView)itemView.findViewById(R.id.cardViewV1);
                cardViewV2 = (CardView)itemView.findViewById(R.id.cardViewV2);

                rl_horizontal = (RelativeLayout) itemView.findViewById(R.id.rl_horizontal);
                rl_vertical = (RelativeLayout) itemView.findViewById(R.id.rl_vertical);

                ivImgH = (ImageView) itemView.findViewById(R.id.ivImgH);
                ivImgV1 = (ImageView) itemView.findViewById(R.id.ivImgV1);
                ivImgV2 = (ImageView) itemView.findViewById(R.id.ivImgV2);

                tvProNmV1 = (TextView) itemView.findViewById(R.id.tvProNmV1);
                tvProNmV2 = (TextView) itemView.findViewById(R.id.tvProNmV2);
                tvProNmH = (TextView) itemView.findViewById(R.id.tvProNmH);

                tvPriceV1 = (TextView) itemView.findViewById(R.id.tvPriceV1);
                tvPriceV2 = (TextView) itemView.findViewById(R.id.tvPriceV2);
                tvPriceH = (TextView) itemView.findViewById(R.id.tvPriceH);
                tvProOldPriceH = (TextView) itemView.findViewById(R.id.tvProOldPriceH);

                tvComNmV1 = (TextView) itemView.findViewById(R.id.tvComNmV1);
                tvComNmV2 = (TextView) itemView.findViewById(R.id.tvComNmV2);


                tvRatingV1 = (RatingBar) itemView.findViewById(R.id.tvRatingV1);
                tvRatingV2 = (RatingBar) itemView.findViewById(R.id.tvRatingV2);


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
