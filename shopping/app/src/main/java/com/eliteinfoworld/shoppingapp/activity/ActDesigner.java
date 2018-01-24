package com.eliteinfoworld.shoppingapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.eliteinfoworld.shoppingapp.R;
import com.eliteinfoworld.shoppingapp.api.model.FragmentModel;
import com.eliteinfoworld.shoppingapp.fragment.DesignerFragment;

import java.util.ArrayList;
import java.util.List;

public class ActDesigner extends BaseActivity {

    String TAG = "ActDesigner";

    ViewPager viewPager;
    PagerSlidingTabStrip tabsStrip;
    ViewPagerAdapter viewPagerAdapter;

    ArrayList<FragmentModel> arraylistFragmentModels = new ArrayList<FragmentModel>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.act_braun);
        ViewGroup.inflate(this, R.layout.act_designer, ll_SubMainContainer);

        try{
            initialize();
            setCommonData();
            clickEvent();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void initialize(){
        try{

            /*----- BaseActivity -----*/
            tvTitle.setText("Desinger");
            //ivBack.setVisibility(View.VISIBLE);
            //ivMenu.setVisibility(View.GONE);
            //setEnableDrawer(false);  //closing menu bar


            /*----- This Activity -----*/
            tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
            tabsStrip.setAllCaps(false);
            viewPager = (ViewPager) findViewById(R.id.htab_viewpager);


            String id[] = {"1", "2", "3"};
            String name[] = {"WOMAN", "MAN", "KIDS"};

            for(int i=0; i<id.length; i++){
                arraylistFragmentModels.add(new FragmentModel(id[i], name[i]));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickEvent(){
        try{

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



    private void setCommonData() {
        try {
            setupViewPager(viewPager);
            tabsStrip.setViewPager(viewPager);
            viewPager.setCurrentItem(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setupViewPager(ViewPager viewPager) {
        try {
            viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

            for (int i = 0; i < arraylistFragmentModels.size(); i++) {
                DesignerFragment fragobj = new DesignerFragment(arraylistFragmentModels.get(i).id, arraylistFragmentModels);
                viewPagerAdapter.addFrag(fragobj, arraylistFragmentModels.get(i).fragName);
            }

            viewPager.setAdapter(viewPagerAdapter);
            viewPager.setOffscreenPageLimit(viewPagerAdapter.getCount());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        public void addFrag(Fragment fragment) {
            mFragmentList.add(fragment);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
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
