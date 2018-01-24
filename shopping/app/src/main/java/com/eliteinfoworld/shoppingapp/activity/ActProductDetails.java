package com.eliteinfoworld.shoppingapp.activity;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eliteinfoworld.shoppingapp.R;

import com.eliteinfoworld.shoppingapp.api.model.ProductDetailsModel;
import com.eliteinfoworld.shoppingapp.api.model.ProductMayLikedModel;
import com.eliteinfoworld.shoppingapp.utils.ExpandableLayout;



import java.util.ArrayList;

public class ActProductDetails extends BaseActivity {

    String TAG = "ActProductDetails";
    ImageView ivBackImg, ivDotImg;
    RatingBar tvRatingBar;


    RecyclerView recycleViewExpand;
    ArrayList<ProductDetailsModel> arrProductDetail= new ArrayList<ProductDetailsModel>();
    ExpandTextViewAdapter expandTextViewAdapter;


    RecyclerView recyclerViewLatestArrivals2;
    ArrayList<ProductMayLikedModel> arrProductMayLiked = new ArrayList<ProductMayLikedModel>();
    ProductMayLikeAdapter productMayLikeAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.act_productdetails);
        ViewGroup.inflate(this, R.layout.act_productdetails, ll_SubMainContainer);

        try{
            initialize();
            clickEvent();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void initialize(){
        try{

            /*------  FOR STATUS BAR TRANSPARENT  ------*/
            //App.setStatusBarTranslucent(true, this);
            rl_baseToolbar.setVisibility(View.GONE);
           /* ivMenu.setVisibility(View.GONE);
            ivSearch.setVisibility(View.GONE);
            ivBack.setVisibility(View.VISIBLE);
            ivDot.setVisibility(View.VISIBLE);
            tvTitle.setText("");*/



            /*----- This Activity -----*/
            tvRatingBar = (RatingBar) findViewById(R.id.tvRatingBar);
            ivBackImg = (ImageView) findViewById(R.id.ivBackImg);
            ivDotImg = (ImageView) findViewById(R.id.ivDotImg);
            recycleViewExpand = (RecyclerView) findViewById(R.id.recycleViewExpand);
            recyclerViewLatestArrivals2 = (RecyclerView) findViewById(R.id.recyclerViewLatestArrivals2);

            recycleViewExpand.setNestedScrollingEnabled(false);  //recycleview smooth scrool inside nestedScrollview
            recyclerViewLatestArrivals2.setNestedScrollingEnabled(false);  //recycleview smooth scrool inside nestedScrollview



            /*-------- Rating bar star color  ----------*/
            LayerDrawable stars = (LayerDrawable) tvRatingBar.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.orangeDark), PorterDuff.Mode.SRC_ATOP);
            stars.getDrawable(1).setColorFilter(getResources().getColor(R.color.orangeDark), PorterDuff.Mode.SRC_ATOP);
            stars.getDrawable(0).setColorFilter(getResources().getColor(R.color.greyLight), PorterDuff.Mode.SRC_ATOP);
            
            

            setRecyclerViewExpand();
            setRecyclerViewLatestArrival2();

            
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickEvent(){
        try{
            ivBackImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void setRecyclerViewExpand(){
        try{

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ActProductDetails.this);
            recycleViewExpand.setLayoutManager(linearLayoutManager);
            recycleViewExpand.setHasFixedSize(true);


            String id[] = {"1", "2", "3"};
            int img[] = {R.drawable.ic_info_24dp, R.drawable.ic_bookmark_grey_18dp, R.drawable.ic_info_24dp};
            String title[] = {"Discription", "Review", "Warrenty"};
            String description[] = {"Discription -- Over 92& of computers are infected with Adware and spyware. Such software is rarely accompanies by uninstall utility and even when it is it almost leave broken Winkows",
                    "Review -- Over 92& of computers are infected with Adware and spyware. Such software is rarely accompanies by uninstall utility and even when it is it almost leave broken Winkows",
                    "Warrenty -- Over 92& of computers are infected with Adware and spyware. Such software is rarely accompanies by uninstall utility and even when it is it almost leave broken Winkows"};
            String isButton[] = {"1", "0", "0"};



            for(int i=0; i<id.length; i++){
                arrProductDetail.add(new ProductDetailsModel(id[i], String.valueOf(img[i]), title[i], description[i],isButton[i]));
            }


            expandTextViewAdapter = new ExpandTextViewAdapter(recycleViewExpand, ActProductDetails.this, arrProductDetail);
            recycleViewExpand.setAdapter(expandTextViewAdapter);
            recycleViewExpand.setItemAnimator(new DefaultItemAnimator());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class ExpandTextViewAdapter extends RecyclerView.Adapter<ExpandTextViewAdapter.VersionViewHolder> {

        private static final int UNSELECTED = -1;

        private RecyclerView recyclerView;
        private int selectedItem = UNSELECTED;

        ArrayList<ProductDetailsModel> arrProductMayLiked;
        Context mContext;



        public ExpandTextViewAdapter(RecyclerView recyclerView, Context context, ArrayList<ProductDetailsModel> arrProductMayLiked) {
            this.recyclerView = recyclerView;
            this.arrProductMayLiked = arrProductMayLiked;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_product_expand, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
            try {

                //versionViewHolder.bind(i);

                versionViewHolder.tvHeader.setText(arrProductDetail.get(i).title);
                versionViewHolder.tvDescription.setText(arrProductDetail.get(i).description);
                versionViewHolder.ivImg.setImageResource(Integer.parseInt(arrProductDetail.get(i).img));

                if(arrProductDetail.get(i).isButton.equalsIgnoreCase("1")){
                    versionViewHolder.btnReadAll.setVisibility(View.VISIBLE);
                }else {
                    versionViewHolder.btnReadAll.setVisibility(View.GONE);
                }

                versionViewHolder.rlLayTitle.setSelected(false);
                versionViewHolder.expandableLayout.collapse(false);

                versionViewHolder.rlLayTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //VersionViewHolder holder = (VersionViewHolder) recycleViewExpand.findViewHolderForAdapterPosition(selectedItem);
                        if (versionViewHolder != null) {
                            versionViewHolder.rlLayTitle.setSelected(false);
                            versionViewHolder.expandableLayout.collapse();
                            versionViewHolder.ivExpandImg.setBackground(getResources().getDrawable(R.drawable.ic_arrow_down_24dp));
                        }

                        if (i == selectedItem) {
                            selectedItem = UNSELECTED;
                        } else {
                            versionViewHolder.rlLayTitle.setSelected(true);
                            versionViewHolder.ivExpandImg.setBackground(getResources().getDrawable(R.drawable.ic_arrow_up_24dp));
                            versionViewHolder.expandableLayout.expand();
                            selectedItem = i;
                        }
                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrProductMayLiked.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ExpandableLayout.OnExpansionUpdateListener {
            private RelativeLayout rlLayTitle;
            private ImageView ivImg, ivExpandImg;
            private ExpandableLayout expandableLayout;
            private TextView tvHeader, tvDescription;
            private Button btnReadAll;
            private int position;

            public VersionViewHolder(View itemView) {
                super(itemView);

                rlLayTitle = (RelativeLayout) itemView.findViewById(R.id.rlLayTitle);
                ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
                ivExpandImg = (ImageView) itemView.findViewById(R.id.ivExpandImg);
                tvHeader = (TextView) itemView.findViewById(R.id.tvHeader);
                tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
                btnReadAll = (Button) itemView.findViewById(R.id.btnReadAll);

                expandableLayout = (ExpandableLayout) itemView.findViewById(R.id.expandable_layout);
                expandableLayout.setInterpolator(new OvershootInterpolator());
                expandableLayout.setOnExpansionUpdateListener(this);



                //rlLayTitle.setOnClickListener(this);
            }


            public void bind(int position) {
                this.position = position;

                tvHeader.setText(arrProductDetail.get(position).title);
                tvDescription.setText(arrProductDetail.get(position).description);
                ivImg.setImageResource(Integer.parseInt(arrProductDetail.get(position).img));

                if(arrProductDetail.get(position).isButton.equalsIgnoreCase("1")){
                    btnReadAll.setVisibility(View.VISIBLE);
                }else {
                    btnReadAll.setVisibility(View.GONE);
                }

                rlLayTitle.setSelected(false);
                expandableLayout.collapse(false);
            }


            @Override
            public void onClick(View v) {
                /*VersionViewHolder holder = (VersionViewHolder) recycleViewExpand.findViewHolderForAdapterPosition(selectedItem);
                if (holder != null) {
                    holder.rlLayTitle.setSelected(false);
                    holder.expandableLayout.collapse();
                    ivExpandImg.setBackground(getResources().getDrawable(R.drawable.ic_arrow_down_24dp));
                }

                if (position == selectedItem) {
                    selectedItem = UNSELECTED;
                } else {
                    rlLayTitle.setSelected(true);
                    ivExpandImg.setBackground(getResources().getDrawable(R.drawable.ic_arrow_up_24dp));
                    expandableLayout.expand();
                    selectedItem = position;
                }*/
            }

            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
                recycleViewExpand.smoothScrollToPosition(getAdapterPosition());
            }


        }
    }



    public void setRecyclerViewLatestArrival2(){
        try{

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ActProductDetails.this, LinearLayoutManager.HORIZONTAL, false);
            recyclerViewLatestArrivals2.setLayoutManager(linearLayoutManager);
            recyclerViewLatestArrivals2.setHasFixedSize(true);


            String productid[] = {"1", "2", "3", "4", "5", "6"};
            int productimg[] = {R.drawable.img_transparent3, R.drawable.img_transparent4, R.drawable.img_transparent9,
                    R.drawable.img_transparent10, R.drawable.img_transparent11, R.drawable.img_transparent7};
            String productName[] = {"Air Conditioner", "Electric Fans", "Air Coolers", "Table Fan", "Exhaust Fans", "Air Ventilators"};
            String compamyName[] = {"Bharat Electronics", "Intex", "Voltas", "Intex", "Voltas", "Intex"};
            String rating[] = {"3", "4", "2", "5", "3", "1"};
            String price[] = {"412", "25", "130", "221", "150", "300"};


            for(int i=0; i<productid.length; i++){
                arrProductMayLiked.add(new ProductMayLikedModel(productid[i], String.valueOf(productimg[i]), productName[i], compamyName[i], rating[i], price[i]));
            }


            productMayLikeAdapter = new ProductMayLikeAdapter(ActProductDetails.this, arrProductMayLiked);
            recyclerViewLatestArrivals2.setAdapter(productMayLikeAdapter);
            recyclerViewLatestArrivals2.setItemAnimator(new DefaultItemAnimator());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class ProductMayLikeAdapter extends RecyclerView.Adapter<ProductMayLikeAdapter.VersionViewHolder> {
        ArrayList<ProductMayLikedModel> arrProductMayLiked;
        Context mContext;



        public ProductMayLikeAdapter(Context context, ArrayList<ProductMayLikedModel> arrProductMayLiked) {
            this.arrProductMayLiked = arrProductMayLiked;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_productmaylike, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
            try {

                versionViewHolder.ivImg.setImageResource(Integer.parseInt(arrProductMayLiked.get(i).productimg));
                versionViewHolder.tvProductName.setText(arrProductMayLiked.get(i).productName);
                versionViewHolder.tvCompanyName.setText(arrProductMayLiked.get(i).compamyName);
                versionViewHolder.tvRating.setText(arrProductMayLiked.get(i).rating);
                versionViewHolder.tvPrice.setText("€"+arrProductMayLiked.get(i).price);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrProductMayLiked.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder {
            ImageView ivImg, ivDots;
            TextView tvProductName, tvCompanyName, tvRating, tvPrice;

            public VersionViewHolder(View itemView) {
                super(itemView);

                ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
                tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
                ivDots = (ImageView) itemView.findViewById(R.id.ivDots);
                tvCompanyName = (TextView) itemView.findViewById(R.id.tvCompanyName);
                tvRating = (TextView) itemView.findViewById(R.id.tvRating);
                tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);

            }

        }
    }


    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        //super.onBackPressed();

       /* if (drawer.isDrawerOpen(left_drawer)) {
            drawer.closeDrawers();
        } else {
            finish();
        }*/
    }

}
