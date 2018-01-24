package com.eliteinfoworld.shoppingapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eliteinfoworld.shoppingapp.R;
import com.eliteinfoworld.shoppingapp.api.model.ActionWeekModel;
import com.eliteinfoworld.shoppingapp.api.model.LatestArrivalTopModel;
import com.eliteinfoworld.shoppingapp.api.model.HomeViewPagerModel;
import com.eliteinfoworld.shoppingapp.utils.ActHomeTransformer;
import com.eliteinfoworld.shoppingapp.utils.App;
import com.eliteinfoworld.shoppingapp.utils.ParallaxPageTransformer;

import java.util.ArrayList;

public class ActHome extends BaseActivity {

    String TAG = "ActHome";

    TextView tvLatestArrivalMore, tvActionWeekMore, tvLatestArrivalMore2, tvActionWeekCount;

    OptionMenuAdapter optionMenuAdapter;
    RecyclerView recyclerViewOption, recyclerLatestTop, recyclerViewLatestArrivals2;

    ArrayList<LatestArrivalTopModel> arrModelLatestTop = new ArrayList<LatestArrivalTopModel>();
    LatestArrivalAdapter latestArrivalAdapter;

    ArrayList<LatestArrivalTopModel> arrModelHomeLatestArrive2 = new ArrayList<LatestArrivalTopModel>();
    LatestArrivalAdapter2 latestArrivalAdapter2;

    /*-------- viewpager parallex effect -----------*/
    ViewPager viewpagerAction;
    PagerAdapter adapterAction;
    ArrayList<ActionWeekModel> arrActionWeek = new ArrayList<ActionWeekModel>();


    ViewPager viewPager;
    PagerAdapter adapter;
    ArrayList<HomeViewPagerModel> arrHomeViewPager = new ArrayList<HomeViewPagerModel>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.act_home);
        ViewGroup.inflate(this, R.layout.act_home, ll_SubMainContainer);

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
            tvTitle.setText("Store");
            ivDot.setVisibility(View.VISIBLE);


            /*----- This Activity -----*/
            tvLatestArrivalMore = (TextView) findViewById(R.id.tvLatestArrivalMore);
            tvActionWeekMore = (TextView) findViewById(R.id.tvActionWeekMore);
            tvLatestArrivalMore2 = (TextView) findViewById(R.id.tvLatestArrivalMore2);
            tvActionWeekCount = (TextView) findViewById(R.id.tvActionWeekCount);

            viewPager = (ViewPager) findViewById(R.id.viewpager);
            viewpagerAction = (ViewPager) findViewById(R.id.viewpagerAction);
            recyclerViewOption = (RecyclerView) findViewById(R.id.recyclerViewOption);
            recyclerLatestTop = (RecyclerView) findViewById(R.id.recyclerLatestTop);
            recyclerViewLatestArrivals2 = (RecyclerView) findViewById(R.id.recyclerViewLatestArrivals2);

            setViewPagerAdapter();
            setRecyclerViewOption();
            setRecyclerViewLatestTop();
            setRecyclerViewActionWeek();
            setRecyclerViewLatestArrival2();




        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickEvent(){
        try{

            tvLatestArrivalMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(ActHome.this, ActBraun.class);
                    startActivity(i1);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });

            tvActionWeekMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(ActHome.this, ActPhoto.class);
                    startActivity(i1);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });

            tvLatestArrivalMore2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(ActHome.this, ActKitchenGride.class);
                    startActivity(i1);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    /*------------------ 1. ViewPager Top ------------------*/
    public void setViewPagerAdapter(){
        try{
            /*------------- Carousel Effect ---------------*/
            viewPager.setClipChildren(false);
            viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.size_30));
            //viewPager.setOffscreenPageLimit(3);
            viewPager.setPageTransformer(false, new ActHomeTransformer(this)); // Set transformer


           /*------------- Set Data ---------------*/
            int[] img = new int[] { R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6};
            String title[] = {"image1", "image2", "image3", "image4", "image5", "image6"};
            String content[] = {"image1 Content Here...", "image2 Content Here...", "image3 Content Here...", "image4 Content Here...", "image5 Content Here...", "image6 Content Here..."};

            for(int i=0; i<img.length; i++){
                arrHomeViewPager.add(new HomeViewPagerModel(String.valueOf(i), String.valueOf(img[i]), title[i], content[i]));
            }


            /*------------- Calling ViewPagerAdapter ---------------*/
            adapter = new ViewPagerAdapter(ActHome.this, arrHomeViewPager);
            viewPager.setAdapter(adapter);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class ViewPagerAdapter extends PagerAdapter {
        // Declare Variables
        Context context;
        ArrayList<HomeViewPagerModel> arrHomeViewPager;
        LayoutInflater inflater;

        public ViewPagerAdapter(Context context, ArrayList<HomeViewPagerModel> arrHomeViewPager) {
            this.context = context;
            this.arrHomeViewPager = arrHomeViewPager;
        }

        @Override
        public int getCount() {
            return arrHomeViewPager.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            // Declare Variables
            ImageView ivViewPager;
            TextView tvCurrentPage, tvTitle, tvContent;

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = inflater.inflate(R.layout.row_home_viewpager, container, false);


            ivViewPager = (ImageView) itemView.findViewById(R.id.ivViewPager);
            tvCurrentPage = (TextView) itemView.findViewById(R.id.tvCurrentPage);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);

            ivViewPager.setImageResource(Integer.parseInt(arrHomeViewPager.get(position).img));
            tvTitle.setText(arrHomeViewPager.get(position).title);
            tvContent.setText(arrHomeViewPager.get(position).content);
            tvCurrentPage.setText( (position+1)+"/"+arrHomeViewPager.size());


            ivViewPager.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(ActHome.this, ActExplore.class);
                    startActivity(i1);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });


            // Add viewpager_item.xml to ViewPager
            ((ViewPager) container).addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // Remove viewpager_item.xml from ViewPager
            ((ViewPager) container).removeView((RelativeLayout) object);

        }
    }




    /*------------------ 2. RecycleView Option Menu  ------------------*/
    public void setRecyclerViewOption(){
        try{

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ActHome.this, LinearLayoutManager.HORIZONTAL, false);
            recyclerViewOption.setLayoutManager(linearLayoutManager);
            recyclerViewOption.setNestedScrollingEnabled(false);  //recycleview smooth scrool inside nestedScrollview
            recyclerViewOption.setHasFixedSize(true);

            String strOptionMenu[] = {"ELECTROCIS", "AUDIO", "HOUSHOLD", "MY ORDER", "MY WISHLIST", "PAYMENT"};

            optionMenuAdapter = new OptionMenuAdapter(ActHome.this, strOptionMenu);
            recyclerViewOption.setAdapter(optionMenuAdapter);
            recyclerViewOption.setItemAnimator(new DefaultItemAnimator());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class OptionMenuAdapter extends RecyclerView.Adapter<OptionMenuAdapter.VersionViewHolder> {
        String strOptionMenu[];
        Context mContext;



        public OptionMenuAdapter(Context context, String strOptionMenu[]) {
            this.strOptionMenu = strOptionMenu;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_home_optionmenu, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
            try {

                versionViewHolder.tvOption.setText(strOptionMenu[i]);

                versionViewHolder.tvOption.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i1 = new Intent(ActHome.this, ActExplore2.class);
                        startActivity(i1);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return strOptionMenu.length;
        }


        class VersionViewHolder extends RecyclerView.ViewHolder {
            TextView tvOption;

            public VersionViewHolder(View itemView) {
                super(itemView);

                tvOption = (TextView) itemView.findViewById(R.id.tvOption);

            }

        }
    }



    /*------------------ 3. RecycleView Latest Arrive  ------------------*/
    public void setRecyclerViewLatestTop(){
        try{

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ActHome.this, LinearLayoutManager.HORIZONTAL, false);
            recyclerLatestTop.setLayoutManager(linearLayoutManager);
            recyclerLatestTop.setNestedScrollingEnabled(false);  //recycleview smooth scrool inside nestedScrollview
            recyclerLatestTop.setHasFixedSize(true);


            String productid[] = {"1", "2", "3", "4", "5", "6"};
            int productimg[] = {R.drawable.img_transparent1, R.drawable.img_transparent2, R.drawable.img_transparent3,
                    R.drawable.img_transparent6, R.drawable.img_transparent5, R.drawable.img_transparent7};
            String productName[] = {"Laser Projector FG245", "Laser Projector FG245", "Laser Projector FG245", "Laser Projector FG245", "Laser Projector FG245", "Laser Projector FG245"};
            String compamyName[] = {"Bharat Electronics", "Intex", "Voltas", "Intex", "Voltas", "Intex"};
            String rating[] = {"3", "4", "2", "5", "3", "1"};
            String price[] = {"412", "25", "130", "221", "150", "300"};


            for(int i=0; i<productid.length; i++){
                arrModelLatestTop.add(new LatestArrivalTopModel(productid[i], String.valueOf(productimg[i]), productName[i], compamyName[i], rating[i], price[i]));
            }


            latestArrivalAdapter = new LatestArrivalAdapter(ActHome.this, arrModelLatestTop);
            recyclerLatestTop.setAdapter(latestArrivalAdapter);
            recyclerLatestTop.setItemAnimator(new DefaultItemAnimator());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class LatestArrivalAdapter extends RecyclerView.Adapter<LatestArrivalAdapter.VersionViewHolder> {
        ArrayList<LatestArrivalTopModel> arrModelLatestTop;
        Context mContext;



        public LatestArrivalAdapter(Context context, ArrayList<LatestArrivalTopModel> arrModelLatestTop) {
            this.arrModelLatestTop = arrModelLatestTop;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_home_latestarrival, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
            try {

                versionViewHolder.ivImg.setImageResource(Integer.parseInt(arrModelLatestTop.get(i).productimg));
                versionViewHolder.tvProductName.setText(arrModelLatestTop.get(i).productName);
                versionViewHolder.tvCompanyName.setText(arrModelLatestTop.get(i).compamyName);
                versionViewHolder.tvRating.setText(arrModelLatestTop.get(i).rating);
                versionViewHolder.tvPrice.setText("$"+arrModelLatestTop.get(i).price);


                versionViewHolder.ivImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i1 = new Intent(ActHome.this, ActBraun2.class);
                        startActivity(i1);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrModelLatestTop.size();
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


    /*------------------ 4. Action Week ------------------*/
    public void setRecyclerViewActionWeek(){
        try{

            String productid[] = {"1", "2", "3"};
            int bgimg[] = {R.drawable.image4, R.drawable.image5, R.drawable.image6};
            int productimg[] = {R.drawable.img_transparent1, R.drawable.img_transparent2, R.drawable.img_transparent4};
            String productName[] = {"Laser Projector FG245", "Laser Projector FG245", "Laser Projector FG245", "Laser Projector FG245", "Laser Projector FG245", "Laser Projector FG245"};
            String compamyName[] = {"Voltas", "Intex", "Voltas", "Intex", "Voltas", "Intex"};
            String rating[] = {"3", "4", "2", "5", "3", "1"};
            String price[] = {"412", "25", "130", "221", "150", "300"};


            for(int i=0; i<productid.length; i++){
                arrActionWeek.add(new ActionWeekModel(productid[i], String.valueOf(bgimg[i]), String.valueOf(productimg[i]), productName[i], compamyName[i], rating[i], price[i]));
            }


            adapterAction = new ViewPagerAdapterAction(ActHome.this, arrActionWeek);
            viewpagerAction.setAdapter(adapterAction);

            ParallaxPageTransformer pageTransformer = new ParallaxPageTransformer()
                    .addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.cardView, 2f, 2f));

            viewpagerAction.setPageTransformer(true, pageTransformer); //set page transformer


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class ViewPagerAdapterAction extends PagerAdapter {
        // Declare Variables
        Context context;
        ArrayList<ActionWeekModel> arrActionWeek;
        LayoutInflater inflater;

        public ViewPagerAdapterAction(Context context, ArrayList<ActionWeekModel> arrActionWeek) {
            this.context = context;
            this.arrActionWeek = arrActionWeek;
        }

        @Override
        public int getCount() {
            return arrActionWeek.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            // Declare Variables
            ImageView ivImg;
            TextView tvProductName, tvCompanyName, tvRating, tvPrice;
            RelativeLayout rlBg;
            CardView cardView;

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = inflater.inflate(R.layout.row_home_actionweek, container, false);

            rlBg = (RelativeLayout)itemView.findViewById(R.id.rlBg);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
            ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvCompanyName = (TextView) itemView.findViewById(R.id.tvCompanyName);
            tvRating = (TextView) itemView.findViewById(R.id.tvRating);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);

           // rlBg.setBackgroundResource(Integer.parseInt(arrActionWeek.get(position).bgimg));
            ivImg.setBackgroundResource(Integer.parseInt(arrActionWeek.get(position).productimg));
            tvProductName.setText(arrActionWeek.get(position).productName);
            tvCompanyName.setText(arrActionWeek.get(position).compamyName);
            tvRating.setText(arrActionWeek.get(position).rating);
            tvPrice.setText("$"+arrActionWeek.get(position).price);

            tvActionWeekCount.setText( (viewpagerAction.getCurrentItem()+1) +" for "+arrActionWeek.size());

            ivImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(ActHome.this, ActBraun2.class);
                    startActivity(i1);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });



            // Add viewpager_item.xml to ViewPager
            ((ViewPager) container).addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // Remove viewpager_item.xml from ViewPager
            ((ViewPager) container).removeView((RelativeLayout) object);

        }
    }





    /*------------------ 5. RecycleView Latest Arrive-- bottom ------------------*/
    public void setRecyclerViewLatestArrival2(){
        try{

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ActHome.this, LinearLayoutManager.HORIZONTAL, false);
            recyclerViewLatestArrivals2.setLayoutManager(linearLayoutManager);
            recyclerViewLatestArrivals2.setNestedScrollingEnabled(false);  //recycleview smooth scrool inside nestedScrollview
            recyclerViewLatestArrivals2.setHasFixedSize(true);


            String productid[] = {"1", "2", "3", "4", "5", "6"};
            int productimg[] = {R.drawable.img_transparent3, R.drawable.img_transparent4, R.drawable.img_transparent9,
                    R.drawable.img_transparent10, R.drawable.img_transparent11, R.drawable.img_transparent7};
            String productName[] = {"Laser Projector FG245", "Laser ", "Laser Projector FG245", "Laser FG245", "Laser Projector FG245", "Laser Projector FG245"};
            String compamyName[] = {"Bharat Electronics", "Intex", "Voltas", "Intex", "Voltas", "Intex"};
            String rating[] = {"3", "4", "2", "5", "3", "1"};
            String price[] = {"412", "25", "130", "221", "150", "300"};


            for(int i=0; i<productid.length; i++){
                arrModelHomeLatestArrive2.add(new LatestArrivalTopModel(productid[i], String.valueOf(productimg[i]), productName[i], compamyName[i], rating[i], price[i]));
            }


            latestArrivalAdapter2 = new LatestArrivalAdapter2(ActHome.this, arrModelHomeLatestArrive2);
            recyclerViewLatestArrivals2.setAdapter(latestArrivalAdapter2);
            recyclerViewLatestArrivals2.setItemAnimator(new DefaultItemAnimator());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class LatestArrivalAdapter2 extends RecyclerView.Adapter<LatestArrivalAdapter2.VersionViewHolder> {
        ArrayList<LatestArrivalTopModel> arrModelHomeLatestArrive2;
        Context mContext;


        public LatestArrivalAdapter2(Context context, ArrayList<LatestArrivalTopModel> arrModelHomeLatestArrive2) {
            this.arrModelHomeLatestArrive2 = arrModelHomeLatestArrive2;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_home_latestarrival2, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
            try {

                versionViewHolder.ivImg.setImageResource(Integer.parseInt(arrModelHomeLatestArrive2.get(i).productimg));
                versionViewHolder.tvProductName.setText(arrModelHomeLatestArrive2.get(i).productName);
                versionViewHolder.tvCompanyName.setText(arrModelHomeLatestArrive2.get(i).compamyName);
                versionViewHolder.tvRating.setText(arrModelHomeLatestArrive2.get(i).rating);
                versionViewHolder.tvPrice.setText("$"+arrModelHomeLatestArrive2.get(i).price);

                versionViewHolder.ivImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i1 = new Intent(ActHome.this, ActBraun2.class);
                        startActivity(i1);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrModelHomeLatestArrive2.size();
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


    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(left_drawer)) {
            drawer.closeDrawers();
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                startActivity(intent);
                finish();
                System.exit(0);
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            App.showSnackBar(tvTitle, "Please click BACK again to exit.");

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }


    }


}
