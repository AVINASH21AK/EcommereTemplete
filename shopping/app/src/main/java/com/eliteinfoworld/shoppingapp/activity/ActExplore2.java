package com.eliteinfoworld.shoppingapp.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eliteinfoworld.shoppingapp.R;
import com.eliteinfoworld.shoppingapp.api.model.Explore2CarouselModel;
import com.eliteinfoworld.shoppingapp.api.model.Explore2CategoriesModel;
import com.eliteinfoworld.shoppingapp.api.model.HotDealsModel;
import com.eliteinfoworld.shoppingapp.utils.ActHomeTransformer;
import com.eliteinfoworld.shoppingapp.utils.App;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ActExplore2 extends BaseActivity {

    String TAG = "ActExplore2";
    
    ViewPager viewPager;
    PagerAdapter adapter;
    ArrayList<Explore2CarouselModel> arrModelExplore2Carousel = new ArrayList<Explore2CarouselModel>();

    RecyclerView recyclerViewCategories, recyclerViewHotDeals;
    ArrayList<Explore2CategoriesModel> arrModelExplore2Categories = new ArrayList<Explore2CategoriesModel>();
    CategoriesAdapter categoriesAdapter;

    ArrayList<HotDealsModel> arrModelHotDeals = new ArrayList<HotDealsModel>();
    HotDealsAdapter hotDealsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.act_explore);

        ViewGroup.inflate(this, R.layout.act_explore2, ll_SubMainContainer);

        try {
            initialize();
            clickEvent();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void initialize() {
        try {

             /*----- BaseActivity -----*/
            tvTitle.setText("Explore");
            //ivDot.setVisibility(View.GONE);


            /*----- This Activity -----*/
            viewPager = (ViewPager) findViewById(R.id.viewpager);
            recyclerViewCategories = (RecyclerView) findViewById(R.id.recyclerViewCategories);
            recyclerViewHotDeals = (RecyclerView) findViewById(R.id.recyclerViewHotDeals);

            setViewPagerAdapter();
            setRecyclerViewCategories();
            setRecyclerViewHotDeals();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void clickEvent() {
        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setViewPagerAdapter() {
        try {
            /*------------- Carousel Effect ---------------*/
            viewPager.setClipChildren(false);
            viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.size_30));
            //viewPager.setOffscreenPageLimit(3);
            viewPager.setPageTransformer(false, new ActHomeTransformer(this)); // Set transformer


           /*------------- Set Data ---------------*/
            int[] img = new int[]{R.drawable.img_transparent1, R.drawable.img_transparent2, R.drawable.img_transparent3,
                    R.drawable.img_transparent6, R.drawable.img_transparent5, R.drawable.img_transparent7};
            String tvProductName[] = {"Laser Projector FG245", "Laser Projector FG245", "Laser Projector FG245", "Laser Projector FG245", "Laser Projector FG245", "Laser Projector FG245"};
            String tvTotalRating[] = {"123", "234", "345", "456", "567", "34"};
            String tvRating[] = {"4", "5", "3", "2", "1", "0"};
            String tvPrice[] = {"25", "52", "36", "63", "47", "58"};

            for (int i = 0; i < img.length; i++) {
                arrModelExplore2Carousel.add(new Explore2CarouselModel(String.valueOf(i), String.valueOf(img[i]), tvProductName[i], tvTotalRating[i], tvRating[i], tvPrice[i]));
            }


            /*------------- Calling ViewPagerAdapter ---------------*/
            adapter = new ViewPagerAdapter(ActExplore2.this, arrModelExplore2Carousel);
            viewPager.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public class ViewPagerAdapter extends PagerAdapter {
        // Declare Variables
        Context context;
        ArrayList<Explore2CarouselModel> arrModelExplore2Carousel;
        LayoutInflater inflater;

        public ViewPagerAdapter(Context context, ArrayList<Explore2CarouselModel> arrModelExplore2Carousel) {
            this.context = context;
            this.arrModelExplore2Carousel = arrModelExplore2Carousel;
        }

        @Override
        public int getCount() {
            return arrModelExplore2Carousel.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            // Declare Variables
            ImageView ivImg;
            TextView tvProductName, tvTotalRating, tvPrice;
            RatingBar tvRatingBar;

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = inflater.inflate(R.layout.row_explore2_carousel, container, false);


            ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvTotalRating = (TextView) itemView.findViewById(R.id.tvTotalRating);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            tvRatingBar = (RatingBar) itemView.findViewById(R.id.tvRatingBar);

            LayerDrawable stars = (LayerDrawable) tvRatingBar.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.greyDark), PorterDuff.Mode.SRC_ATOP);
            stars.getDrawable(1).setColorFilter(getResources().getColor(R.color.greyDark), PorterDuff.Mode.SRC_ATOP);
            stars.getDrawable(0).setColorFilter(getResources().getColor(R.color.greyLight), PorterDuff.Mode.SRC_ATOP);

            ivImg.setImageResource(Integer.parseInt(arrModelExplore2Carousel.get(position).img));
            tvProductName.setText(arrModelExplore2Carousel.get(position).tvProductName);
            tvTotalRating.setText(arrModelExplore2Carousel.get(position).tvTotalRating);
            tvPrice.setText("$"+arrModelExplore2Carousel.get(position).tvPrice);
            tvRatingBar.setRating(Float.parseFloat(arrModelExplore2Carousel.get(position).tvRating));


            // Add viewpager_item.xml to ViewPager
            ((ViewPager) container).addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // Remove viewpager_item.xml from ViewPager
            ((ViewPager) container).removeView((LinearLayout) object);

        }


    }


    public void setRecyclerViewCategories(){
        try{

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ActExplore2.this);
            recyclerViewCategories.setLayoutManager(linearLayoutManager);
            recyclerViewCategories.setNestedScrollingEnabled(false);  //recycleview smooth scrool inside nestedScrollview
            recyclerViewCategories.setHasFixedSize(true);


            String categoryid[] = {"1", "2", "3", "4"};
            int categoryimg[] = {R.drawable.img_transparent1, R.drawable.img_transparent2, R.drawable.img_transparent3,
                    R.drawable.img_transparent6};
            String categoryName[] = {"Air Conditioner", "Electric Fans", "Air Coolers", "Table Fan"};
            String categorytotal[] = {"412", "25", "130", "221"};


            for(int i=0; i<categoryid.length; i++){
                arrModelExplore2Categories.add(new Explore2CategoriesModel(categoryid[i], String.valueOf(categoryimg[i]), categoryName[i], categorytotal[i]));
            }


            categoriesAdapter = new CategoriesAdapter(ActExplore2.this, arrModelExplore2Categories);
            recyclerViewCategories.setAdapter(categoriesAdapter);
            recyclerViewCategories.setItemAnimator(new DefaultItemAnimator());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.VersionViewHolder> {
        ArrayList<Explore2CategoriesModel> arrModelHomeLatestArrive;
        Context mContext;



        public CategoriesAdapter(Context context, ArrayList<Explore2CategoriesModel> arrModelHomeLatestArrive) {
            this.arrModelHomeLatestArrive = arrModelHomeLatestArrive;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_explore2_category, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
            try {

                versionViewHolder.ivImg.setImageResource(Integer.parseInt(arrModelHomeLatestArrive.get(i).categoryimg));
                versionViewHolder.tvCategoryName.setText(arrModelHomeLatestArrive.get(i).categoryName);
                versionViewHolder.tvCategoryTotal.setText(arrModelHomeLatestArrive.get(i).categorytotal+" items");


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrModelHomeLatestArrive.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder {
            ImageView ivImg;
            TextView tvCategoryName, tvCategoryTotal;

            public VersionViewHolder(View itemView) {
                super(itemView);

                ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
                tvCategoryName = (TextView) itemView.findViewById(R.id.tvCategoryName);
                tvCategoryTotal = (TextView) itemView.findViewById(R.id.tvCategoryTotal);

            }

        }
    }


    public void setRecyclerViewHotDeals(){
        try{

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ActExplore2.this, LinearLayoutManager.HORIZONTAL, false);
            recyclerViewHotDeals.setLayoutManager(linearLayoutManager);
            recyclerViewHotDeals.setNestedScrollingEnabled(false);  //recycleview smooth scrool inside nestedScrollview
            recyclerViewHotDeals.setHasFixedSize(true);


            String dealId[] = {"1", "2", "3", "4"};
            int dealImg[] = {R.drawable.img_transparent3, R.drawable.img_transparent4, R.drawable.img_transparent9,
                    R.drawable.img_transparent10};
            String dealName[] = {"Air Conditioner", "Electric Fans", "Air Coolers", "Table Fan"};

            String dealOldPrice[] = {"545", "54", "345", "345"};
            String dealNewPrice[] = {"412", "25", "130", "221"};

            String dealFullTime[] = {"5", "5", "5", "3"};
            String dealLeftTime[] = {"1", "2", "3", "4"};


            for(int i=0; i<dealId.length; i++){
                arrModelHotDeals.add(new HotDealsModel(dealId[i], String.valueOf(dealImg[i]), dealName[i], dealOldPrice[i], dealNewPrice[i], dealFullTime[i], dealLeftTime[i]));
            }


            hotDealsAdapter = new HotDealsAdapter(ActExplore2.this, arrModelHotDeals);
            recyclerViewHotDeals.setAdapter(hotDealsAdapter);
            recyclerViewHotDeals.setItemAnimator(new DefaultItemAnimator());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class HotDealsAdapter extends RecyclerView.Adapter<HotDealsAdapter.VersionViewHolder> {
        ArrayList<HotDealsModel> arrModelHotDeals;
        Context mContext;



        public HotDealsAdapter(Context context, ArrayList<HotDealsModel> arrModelHotDeals) {
            this.arrModelHotDeals = arrModelHotDeals;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_explore2_hotdeals, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder viewHolder, final int i) {
            try {

                viewHolder.ivImg.setImageResource(Integer.parseInt(arrModelHotDeals.get(i).dealImg));
                viewHolder.tvDealName.setText(arrModelHotDeals.get(i).dealName);
                viewHolder.tvDealOldPrice.setText("$"+arrModelHotDeals.get(i).dealOldPrice);
                viewHolder.tvDealNewPrice.setText("$"+arrModelHotDeals.get(i).dealNewPrice);

                //viewHolder.tvDealLeftTime.setText(arrModelHotDeals.get(i).dealLeftTime);
                //viewHolder.progressBar.setMax(Integer.parseInt(arrModelHotDeals.get(i).dealFullTime) );


                viewHolder.progressBar.setMax(Integer.parseInt(arrModelHotDeals.get(i).dealLeftTime) * 60 );
                viewHolder.progressBar.setProgress(Integer.parseInt(arrModelHotDeals.get(i).dealLeftTime));
                viewHolder.progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);



                final long strSecMilli =  Long.parseLong(arrModelHotDeals.get(i).dealLeftTime) * 60 * 1000;
                App.showLog(TAG, "strSecMilli: "+strSecMilli);

                new CountDownTimer(strSecMilli, 1000) {

                    public void onTick(long millisUntilFinished) {

                        String text = String.format(Locale.getDefault(), "%02d:%02d:%02d",
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished) % 60,
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);

                        viewHolder.tvDealLeftTime.setText(text);
                        //int finalsec = (int) ((strSecMilli - millisUntilFinished)/1000);
                        viewHolder.progressBar.setProgress((int) ((strSecMilli - millisUntilFinished)/1000));
                        //App.showLog(TAG, "finalsec: "+finalsec);
                        //viewHolder.progressBar.setProgress((int) ( millisUntilFinished/ 1000 ) );

                    }

                    public void onFinish() {
                        viewHolder.cardView.setEnabled(false);
                        viewHolder.cardView.setCardBackgroundColor(getResources().getColor(R.color.greyLight));
                        viewHolder.tvDealLeftTime.setText("Deal Finish..!!!");
                        //Toast.makeText(ActExplore2.this, "Deal Finish..!!!", Toast.LENGTH_SHORT).show();
                    }

                }.start();




            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrModelHotDeals.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder {
            CardView cardView;
            ImageView ivImg;
            TextView tvDealName, tvDealOldPrice, tvDealNewPrice, tvDealLeftTime;
            ProgressBar progressBar;

            public VersionViewHolder(View itemView) {
                super(itemView);

                cardView = (CardView) itemView.findViewById(R.id.cardView);
                ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
                tvDealName = (TextView) itemView.findViewById(R.id.tvDealName);
                tvDealOldPrice = (TextView) itemView.findViewById(R.id.tvDealOldPrice);
                tvDealNewPrice = (TextView) itemView.findViewById(R.id.tvDealNewPrice);
                tvDealLeftTime = (TextView) itemView.findViewById(R.id.tvDealLeftTime);
                progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);

            }

        }
    }



}
