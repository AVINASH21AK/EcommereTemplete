package com.eliteinfoworld.shoppingapp.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.eliteinfoworld.shoppingapp.R;
import com.eliteinfoworld.shoppingapp.fragment.BraunHighLightFrag;
import com.eliteinfoworld.shoppingapp.fragment.BraunDiscriptionFrag;
import com.eliteinfoworld.shoppingapp.fragment.BraunSpecificationFrag;

public class ActBraun2 extends BaseActivity {

    String TAG = "ActBraun";
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.act_braun);
        ViewGroup.inflate(this, R.layout.act_braun2, ll_SubMainContainer);

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
            tvTitle.setText("Braun");
            ivBack.setVisibility(View.VISIBLE);
            ivMenu.setVisibility(View.GONE);
            setEnableDrawer(false);  //closing menu bar


            /*----- This Activity -----*/
            tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            tabLayout.addTab(tabLayout.newTab().setText("HIGHLIGHTS"));
            tabLayout.addTab(tabLayout.newTab().setText("DESCRIPTION"));
            tabLayout.addTab(tabLayout.newTab().setText("SPECIFICATION"));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            viewPager = (ViewPager) findViewById(R.id.pager);
            adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickEvent(){
        try{
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
                    BraunHighLightFrag tab1 = new BraunHighLightFrag();
                    return tab1;
                case 1:
                    BraunDiscriptionFrag tab2 = new BraunDiscriptionFrag();
                    return tab2;
                case 2:
                    BraunSpecificationFrag tab3 = new BraunSpecificationFrag();
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
