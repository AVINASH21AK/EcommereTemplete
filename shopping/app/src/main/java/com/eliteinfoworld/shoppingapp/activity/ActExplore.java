package com.eliteinfoworld.shoppingapp.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.eliteinfoworld.shoppingapp.R;
import com.eliteinfoworld.shoppingapp.fragment.ExploreHightlights;
import com.eliteinfoworld.shoppingapp.fragment.ExploreNewProducts;
import com.eliteinfoworld.shoppingapp.fragment.ExplorePopular;

public class ActExplore extends BaseActivity {

    String TAG = "ActExplore";
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.act_explore);

        ViewGroup.inflate(this, R.layout.act_explore, ll_SubMainContainer);

        try{
            initialize();
            clickEvent();

        }catch (Exception e){
            e.printStackTrace();
        }


    }


    public void initialize() {
        try {

             /*----- BaseActivity -----*/
            tvTitle.setText("Explore");
            //ivDot.setVisibility(View.GONE);


            /*----- This Activity -----*/
            tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            tabLayout.addTab(tabLayout.newTab().setText("WHAT'S NEW"));
            tabLayout.addTab(tabLayout.newTab().setText("POPULAR"));
            tabLayout.addTab(tabLayout.newTab().setText("HIGHLIGHTS"));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            viewPager = (ViewPager) findViewById(R.id.pager);
            adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void clickEvent() {
        try {

            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    ExploreNewProducts tab1 = new ExploreNewProducts();
                    return tab1;
                case 1:
                    ExplorePopular tab2 = new ExplorePopular();
                    return tab2;
                case 2:
                    ExploreHightlights tab3 = new ExploreHightlights();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

}
