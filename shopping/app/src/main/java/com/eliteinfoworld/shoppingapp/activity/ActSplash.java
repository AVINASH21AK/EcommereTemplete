package com.eliteinfoworld.shoppingapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eliteinfoworld.shoppingapp.R;
import com.eliteinfoworld.shoppingapp.api.model.SplashModel;
import com.eliteinfoworld.shoppingapp.utils.App;

import java.util.ArrayList;

import io.github.kshitij_jain.indicatorview.IndicatorView;

public class ActSplash extends Activity {

    String TAG = "ActSplash";

    // Declare Variables
    ViewPager viewPager;
    PagerAdapter adapter;
    //int[] imgSplash;

    ArrayList<SplashModel> arrSplash = new ArrayList<SplashModel>();

    IndicatorView mIndicatorView;
    TextView tvSkip, tvstart;
    ImageView ivNext;
    RelativeLayout rl_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);


        try{

            if(App.sharePrefrences.getStringPref(App.sh_skip) != null && App.sharePrefrences.getStringPref(App.sh_skip).length() > 0){
                if(App.sharePrefrences.getStringPref(App.sh_skip).equalsIgnoreCase(App.sh_skip)){
                    Intent i1 = new Intent(ActSplash.this, ActHome.class);
                    startActivity(i1);
                    //overridePendingTransition(0,0);
                }
            }


            initialize();
            clickEvent();

        }catch (Exception e){
            e.printStackTrace();
        }


    }


    public void initialize() {
        try {

           // App.setStatusBarTranslucent(true, this);

            viewPager = (ViewPager) findViewById(R.id.viewpager);
            ivNext = (ImageView) findViewById(R.id.ivNext);
            tvSkip = (TextView) findViewById(R.id.tvSkip);
            tvstart = (TextView) findViewById(R.id.tvstart);
            rl_bottom = (RelativeLayout) findViewById(R.id.rl_bottom);


            String id[] = {"1", "2", "3", "4"};
            int bgimg[] = {R.drawable.splash1, R.drawable.splash1, R.drawable.splash1, R.drawable.splash1};
            String title[] = {"Shopping everywear", "Shopping everywear", "Shopping everywear", "Let's start!"};
            String subtitle[] = {"Never lose your progress, so can keep working from any computer or device.",
                    "Never lose your progress, so can keep working from any computer or device.",
                    "Never lose your progress, so can keep working from any computer or device.",
                    "Tap \"Accept\" to agree to the Terms of Service and Privacy Policy"};

            for(int i=0; i<id.length; i++){
                arrSplash.add(new SplashModel(id[i], String.valueOf(bgimg[i]), title[i], subtitle[i]));
            }


            //imgSplash = new int[]{R.drawable.splash1, R.drawable.splash2, R.drawable.splash3, R.drawable.splash4 };

            adapter = new ViewPagerAdapter(ActSplash.this, arrSplash);
            viewPager.setAdapter(adapter);


            /*--------------   View Pager Indicator  ------------------*/
            mIndicatorView = (IndicatorView) findViewById(R.id.circle_indicator_view);
            mIndicatorView.setPageIndicators(arrSplash.size());

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    mIndicatorView.setCurrentPage(position);

                    if ((viewPager.getCurrentItem() + 1) == arrSplash.size()) {
                        tvstart.setVisibility(View.VISIBLE);
                        ivNext.setVisibility(View.INVISIBLE);
                        tvSkip.setVisibility(View.INVISIBLE);
                        mIndicatorView.setVisibility(View.INVISIBLE);
                        rl_bottom.setBackgroundColor(getResources().getColor(R.color.white));
                    } else {
                        tvstart.setVisibility(View.INVISIBLE);
                        ivNext.setVisibility(View.VISIBLE);
                        tvSkip.setVisibility(View.VISIBLE);
                        mIndicatorView.setVisibility(View.VISIBLE);
                        rl_bottom.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    }

                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void clickEvent() {
        try {

            tvSkip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    App.sharePrefrences.setPref(App.sh_skip, App.sh_skip);
                    Intent i1 = new Intent(ActSplash.this, ActHome.class);
                    startActivity(i1);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });


            ivNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "viewPager position: " + viewPager.getCurrentItem());

                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    /*if ((viewPager.getCurrentItem() + 1) == imgSplash.length) {
                        Intent i1 = new Intent(ActSplash.this, ActHome.class);
                        startActivity(i1);
                    } else {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }*/

                }
            });

            tvstart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    App.sharePrefrences.setPref(App.sh_skip, App.sh_skip);
                    Intent i1 = new Intent(ActSplash.this, ActHome.class);
                    startActivity(i1);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class ViewPagerAdapter extends PagerAdapter {
        // Declare Variables
        Context context;
        ArrayList<SplashModel> arrSplash;
        LayoutInflater inflater;

        public ViewPagerAdapter(Context context, ArrayList<SplashModel> arrSplash) {
            this.context = context;
            this.arrSplash = arrSplash;
        }

        @Override
        public int getCount() {
            return arrSplash.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            // Declare Variables
            ImageView ivSplash;
            TextView tvTitle, tvSubTitle;

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = inflater.inflate(R.layout.row_splashscreen_viewpager, container, false);


            ivSplash = (ImageView) itemView.findViewById(R.id.ivSplash);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvSubTitle = (TextView) itemView.findViewById(R.id.tvSubTitle);


            ivSplash.setImageResource(Integer.parseInt(arrSplash.get(position).bgimg));
            tvTitle.setText(arrSplash.get(position).title);
            tvSubTitle.setText(arrSplash.get(position).subtitle);


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


}
