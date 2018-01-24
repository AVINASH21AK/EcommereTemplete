package com.eliteinfoworld.shoppingapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.eliteinfoworld.shoppingapp.R;
import com.eliteinfoworld.shoppingapp.api.model.KitchenRBModel;

import java.util.ArrayList;

public class ActKitchenRB extends BaseActivity {

    String TAG = "ActKitchenGride";
    RecyclerView recyclerViewKitchenGride;
    ArrayList<KitchenRBModel> arrayListKitchenRB= new ArrayList<KitchenRBModel>();
    KitchenRBAdapter kitchenRBAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.act_home);
        ViewGroup.inflate(this, R.layout.act_kitchen_rb, ll_SubMainContainer);

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
            tvTitle.setText("Kitchen");
            //ivMenu.settint(getResources().getColor(R.color.black));


            /*----- This Activity -----*/
            recyclerViewKitchenGride = (RecyclerView)findViewById(R.id.recyclerViewKitchenGride);
            recyclerViewKitchenGride.setNestedScrollingEnabled(false);  //recycleview smooth scrool inside nestedScrollview


            setRecycleviewData();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickEvent(){
        try{


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
            String productName[] = {"Laser Projector FG245", "Laser Projector FG245", "Laser Projector FG245", "Laser Projector FG245", "Laser Projector FG245", "Laser Projector FG245"};
            String compamyName[] = {"Bharat Electronics", "Intex", "Voltas", "Intex", "Voltas", "Intex"};

            String productprice[] = {"514", "84", "215", "454", "545", "999"};
            String rating[] = {"3", "4", "2", "5", "3", "1"};

           

            for(int i=0; i<productid.length; i++){
                arrayListKitchenRB.add(new KitchenRBModel(productid[i], String.valueOf(productimg[i]),
                        productName[i], compamyName[i], rating[i], productprice[i]));
            }


            kitchenRBAdapter = new KitchenRBAdapter(this, arrayListKitchenRB);
            recyclerViewKitchenGride.setAdapter(kitchenRBAdapter);
            recyclerViewKitchenGride.setItemAnimator(new DefaultItemAnimator());

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public class KitchenRBAdapter extends RecyclerView.Adapter<KitchenRBAdapter.VersionViewHolder> {
        ArrayList<KitchenRBModel> arrayListKitchenRB;
        Context mContext;
        int countChecked = 0;

        public KitchenRBAdapter(Context context, ArrayList<KitchenRBModel> arrayListKitchenRB) {
            this.arrayListKitchenRB = arrayListKitchenRB;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_kitchen_rb, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
            try {
                versionViewHolder.ivImg.setImageResource(Integer.parseInt(arrayListKitchenRB.get(i).productimg));
                versionViewHolder.tvProductName.setText(arrayListKitchenRB.get(i).productName);
                versionViewHolder.tvCompanyName.setText(arrayListKitchenRB.get(i).compamyName);
                versionViewHolder.tvPrice.setText("$"+arrayListKitchenRB.get(i).productPrice);




                versionViewHolder.cardViewLay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(versionViewHolder.rbButton.isChecked()){
                            versionViewHolder.rbButton.setChecked(false);
                            countChecked--;

                        }else {
                            versionViewHolder.rbButton.setChecked(true);
                            countChecked++;

                        }


                        if(countChecked > 0){
                            ivMenu.setVisibility(View.GONE);
                            ivSearch.setVisibility(View.GONE);

                            ivCorss.setVisibility(View.VISIBLE);
                            ivDelete.setVisibility(View.VISIBLE);
                            ivCart.setVisibility(View.VISIBLE);

                            tvTitle.setText(countChecked + " select");
                        }else {
                            ivMenu.setVisibility(View.VISIBLE);
                            ivSearch.setVisibility(View.VISIBLE);

                            ivCorss.setVisibility(View.GONE);
                            ivDelete.setVisibility(View.GONE);
                            ivCart.setVisibility(View.GONE);

                            tvTitle.setText("Kitchen");
                        }

                    }
                });



            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrayListKitchenRB.size();
        }



        class VersionViewHolder extends RecyclerView.ViewHolder {
            ImageView ivImg, ivDots;
            TextView tvProductName, tvCompanyName, tvPrice;
            RadioButton rbButton;
            CardView cardViewLay;

            public VersionViewHolder(View itemView) {
                super(itemView);

                cardViewLay = (CardView) itemView.findViewById(R.id.cardViewLay);
                ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
                ivDots = (ImageView) itemView.findViewById(R.id.ivDots);
                tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
                tvCompanyName = (TextView) itemView.findViewById(R.id.tvCompanyName);
                tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
                rbButton = (RadioButton) itemView.findViewById(R.id.rbButton);


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
