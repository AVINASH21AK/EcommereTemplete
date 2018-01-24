package com.eliteinfoworld.shoppingapp.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eliteinfoworld.shoppingapp.R;
import com.eliteinfoworld.shoppingapp.api.model.HighLightBraunModel;
import com.eliteinfoworld.shoppingapp.api.model.RelatedProductModel;
import com.eliteinfoworld.shoppingapp.api.model.ReviewListModel;
import com.eliteinfoworld.shoppingapp.utils.App;

import java.util.ArrayList;

/**
 * Created by Avinash Kahal on 22-Sep-17.
 */

public class BraunHighLightFrag extends Fragment {

    String TAG = "BraunHighLightFrag";
    View viewFragment;


    ImageView ivimg;
    TextView tvProductName, tvProductPrice, tvProductDiscription, tvProductAvgPt, tvProductTotalReview;
    RatingBar ratingBarAvg;
    RecyclerView recyclerViewReview, recyclerViewRelatedItems;
    View view1, view2, view3, view4, view5;

    ArrayList<ReviewListModel> arrListReview = new ArrayList<ReviewListModel>();
    ReviewAdapter reviewAdapter;

    ArrayList<RelatedProductModel> arrListRelatedProduct = new ArrayList<RelatedProductModel>();
    RelatedProductAdapter relatedProductAdapter;
    FloatingActionButton fabHighLight;
    
    

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.frag_braun_highlight, container, false);

        try{

            initialize();
            clickEvent();

        }catch (Exception e){
            e.printStackTrace();
        }

        return viewFragment;
    }

    public void initialize(){
        try{

            ivimg = (ImageView)viewFragment.findViewById(R.id.ivimg);
            tvProductName = (TextView)viewFragment.findViewById(R.id.tvProductName);
            tvProductPrice = (TextView)viewFragment.findViewById(R.id.tvProductPrice);
            tvProductDiscription = (TextView)viewFragment.findViewById(R.id.tvProductDiscription);
            tvProductAvgPt = (TextView)viewFragment.findViewById(R.id.tvProductAvgPt);
            tvProductTotalReview = (TextView)viewFragment.findViewById(R.id.tvProductTotalReview);
            ratingBarAvg = (RatingBar)viewFragment.findViewById(R.id.ratingBarAvg);
            fabHighLight = (FloatingActionButton) viewFragment.findViewById(R.id.fabHighLight);
            recyclerViewRelatedItems = (RecyclerView)viewFragment.findViewById(R.id.recyclerViewRelatedItems);
            recyclerViewReview = (RecyclerView)viewFragment.findViewById(R.id.recyclerViewReview);

            recyclerViewRelatedItems.setNestedScrollingEnabled(false);  //recycleview smooth scrool inside nestedScrollview
            recyclerViewReview.setNestedScrollingEnabled(false);  //recycleview smooth scrool inside nestedScrollview

            view1 = (View) viewFragment.findViewById(R.id.view1);
            view2 = (View) viewFragment.findViewById(R.id.view2);
            view3 = (View) viewFragment.findViewById(R.id.view3);
            view4 = (View) viewFragment.findViewById(R.id.view4);
            view5 = (View) viewFragment.findViewById(R.id.view5);



            view1.getLayoutParams().width = (int) getResources().getDimension(R.dimen.size_150);
            view2.getLayoutParams().width = (int) getResources().getDimension(R.dimen.size_100);
            view3.getLayoutParams().width = (int) getResources().getDimension(R.dimen.size_80);
            view4.getLayoutParams().width = (int) getResources().getDimension(R.dimen.size_30);
            view5.getLayoutParams().width = (int) getResources().getDimension(R.dimen.size_50);

            int productImage = R.drawable.image4;
            String productName = "Braun Pocket Calculator";
            String productPrice = "$345";
            String productDis = "Disption of Braun Pocket Calculator........ Braun Pocket Calculator......... Braun Pocket Calculator";
            String ProductAvgPt = "4.5";
            String AvgRatingBar = "3";
            String ProductTotalReview = "55";


            HighLightBraunModel model = new HighLightBraunModel(String.valueOf(productImage), productName, productPrice, productDis,
                    ProductAvgPt, AvgRatingBar, ProductTotalReview);



            setData(model);
            setUserReview();
            setRelatedProduct();




        }catch (Exception e){
            e.printStackTrace();
        }
    }




    public void clickEvent(){
        try{
            fabHighLight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    App.showSnackBar(v, "Fab button click");
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void setData(HighLightBraunModel model){
        try{

            ivimg.setImageResource(Integer.parseInt(model.img));
            tvProductName.setText(model.productName);
            tvProductPrice.setText(model.productPrice);
            tvProductDiscription.setText(model.productDiscription);
            tvProductAvgPt.setText(model.productAvgPt);
            tvProductTotalReview.setText(model.productTotalReview);

            ratingBarAvg.setRating(Float.parseFloat(model.ratingBarAvg));
            //DrawableCompat.setTint(ratingBarAvg.getProgressDrawable(), getResources().getColor(R.color.grey));

            LayerDrawable stars = (LayerDrawable) ratingBarAvg.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.greyDark), PorterDuff.Mode.SRC_ATOP);
            stars.getDrawable(1).setColorFilter(getResources().getColor(R.color.greyDark), PorterDuff.Mode.SRC_ATOP);
            stars.getDrawable(0).setColorFilter(getResources().getColor(R.color.greyLight), PorterDuff.Mode.SRC_ATOP);



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setUserReview(){
        try{

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerViewReview.setLayoutManager(linearLayoutManager);
            recyclerViewReview.setNestedScrollingEnabled(false);  //recycleview smooth scrool inside nestedScrollview
            recyclerViewReview.setHasFixedSize(true);

            String userId[] = {"1", "2", "3"};
            int userImg[] = {R.drawable.img_transparent1, R.drawable.img_transparent2, R.drawable.img_transparent3,};
            String userName[] = {"Air Conditioner", "Electric Fans", "Air Coolers"};
            String userRating[] = {"3", "4", "2"};
            String commentTitle[] = {"Bharat", "Intex", "Voltas"};
            String userComment[] = {"Bharat Electronics qwertt sadflj asdlfj alsdjf", "Intex Electronics qwertt sadflj asdlfj alsdjf", "Voltas Electronics qwertt sadflj asdlfj alsdjf"};


            for(int i=0; i<userId.length; i++){
                arrListReview.add(new ReviewListModel(userId[i], String.valueOf(userImg[i]), userName[i], userRating[i], commentTitle[i], userComment[i]));
            }


            reviewAdapter = new ReviewAdapter(getActivity(), arrListReview);
            recyclerViewReview.setAdapter(reviewAdapter);
            recyclerViewReview.setItemAnimator(new DefaultItemAnimator());
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.VersionViewHolder> {
        ArrayList<ReviewListModel> arrListReview;
        Context mContext;



        public ReviewAdapter(Context context, ArrayList<ReviewListModel> arrListReview) {
            this.arrListReview = arrListReview;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_highlight_review, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
            try {

                versionViewHolder.ivUserImg.setImageResource(Integer.parseInt(arrListReview.get(i).userImg));
                versionViewHolder.tvUserName.setText(arrListReview.get(i).userName);
                versionViewHolder.tvCommentHeader.setText(arrListReview.get(i).commentTitle);
                versionViewHolder.tvUserComment.setText(arrListReview.get(i).userComment);
                versionViewHolder.ratingBarUser.setRating(Float.parseFloat(arrListReview.get(i).userRating));


                LayerDrawable stars = (LayerDrawable) versionViewHolder.ratingBarUser.getProgressDrawable();
                stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.greyDark), PorterDuff.Mode.SRC_ATOP);
                stars.getDrawable(1).setColorFilter(getResources().getColor(R.color.greyDark), PorterDuff.Mode.SRC_ATOP);
                stars.getDrawable(0).setColorFilter(getResources().getColor(R.color.greyLight), PorterDuff.Mode.SRC_ATOP);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrListReview.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder {
            ImageView ivUserImg, ivDots;
            TextView tvUserName, tvCommentHeader, tvUserComment;
            RatingBar ratingBarUser;

            public VersionViewHolder(View itemView) {
                super(itemView);

                ivUserImg = (ImageView) itemView.findViewById(R.id.ivUserImg);
                tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
                ivDots = (ImageView) itemView.findViewById(R.id.ivDots);
                tvCommentHeader = (TextView) itemView.findViewById(R.id.tvCommentHeader);
                tvUserComment = (TextView) itemView.findViewById(R.id.tvUserComment);
                ratingBarUser = (RatingBar) itemView.findViewById(R.id.ratingBarUser);

            }

        }
    }



    public void setRelatedProduct(){
        try{

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerViewRelatedItems.setLayoutManager(linearLayoutManager);
            recyclerViewRelatedItems.setNestedScrollingEnabled(false);  //recycleview smooth scrool inside nestedScrollview
            recyclerViewRelatedItems.setHasFixedSize(true);

            String proId[] = {"1", "2", "3"};
            int proImg[] = {R.drawable.img_transparent1, R.drawable.img_transparent2, R.drawable.img_transparent3,};
            String proName[] = {"Laser Projector FG245", "Laser Projector FG245", "Laser Projector FG245"};
            String proOldPrice[] = {"123", "234", "334"};
            String proNewPrice[] = {"100", "221", "312"};


            for(int i=0; i<proId.length; i++){
                arrListRelatedProduct.add(new RelatedProductModel(proId[i], String.valueOf(proImg[i]), proName[i], proOldPrice[i], proNewPrice[i]));
            }


            relatedProductAdapter = new RelatedProductAdapter(getActivity(), arrListRelatedProduct);
            recyclerViewRelatedItems.setAdapter(relatedProductAdapter);
            recyclerViewRelatedItems.setItemAnimator(new DefaultItemAnimator());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class RelatedProductAdapter extends RecyclerView.Adapter<RelatedProductAdapter.VersionViewHolder> {
        ArrayList<RelatedProductModel> arrListRelatedProduct;
        Context mContext;



        public RelatedProductAdapter(Context context, ArrayList<RelatedProductModel> arrListRelatedProduct) {
            this.arrListRelatedProduct = arrListRelatedProduct;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_highlight_relatedprodct, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
            try {

                versionViewHolder.ivImg.setImageResource(Integer.parseInt(arrListRelatedProduct.get(i).img));
                versionViewHolder.tvProductName.setText(arrListRelatedProduct.get(i).productName);
                versionViewHolder.tvProOldPrice.setText("$"+arrListRelatedProduct.get(i).productOldPrice);
                versionViewHolder.tvProNewPrice.setText("$"+arrListRelatedProduct.get(i).productNewPrice);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrListRelatedProduct.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder {
            ImageView ivImg;
            TextView tvProductName, tvProOldPrice, tvProNewPrice;

            public VersionViewHolder(View itemView) {
                super(itemView);

                ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
                tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
                tvProOldPrice = (TextView) itemView.findViewById(R.id.tvProOldPrice);
                tvProNewPrice = (TextView) itemView.findViewById(R.id.tvProNewPrice);


            }

        }
    }

}
