package com.eliteinfoworld.shoppingapp.activity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.eliteinfoworld.shoppingapp.api.model.BaseBottomMenuModel;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.eliteinfoworld.shoppingapp.R;
import com.eliteinfoworld.shoppingapp.api.model.BaseTopMenuModel;
import com.eliteinfoworld.shoppingapp.utils.App;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends FragmentActivity {

    String TAG = "BaseActivity";

    protected DrawerLayout drawer;

    protected RelativeLayout left_drawer, rl_baseToolbar;
    protected LinearLayout ll_FullContainer, ll_SubMainContainer;

    TextView tvTitle, tvName, tvEmailID;
    MaterialEditText etSearch;
    protected ImageView ivMenu, ivCorss, ivSearch, ivDelete, ivCart, ivBack, ivDot, ivFilter, ivCorssright;
    CircularImageView circularUserImg;
    MaterialSpinner spn_photos;
    protected RecyclerView recyclerViewTopMenu, recyclerViewBottomMenu;

    BaseActivityAdapter baseActivityAdapter;
    public ArrayList<BaseTopMenuModel> arrModelBaseAct = new ArrayList<BaseTopMenuModel>();

    BaseActivityBottomAdapter baseBottomAdapter;
    public ArrayList<BaseBottomMenuModel> arrBottomMenu = new ArrayList<BaseBottomMenuModel>();

    //For getting class name
    ComponentName componentInfo;
    List<ActivityManager.RunningTaskInfo> taskInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_base);

        /*-------  Different Name from other activity as its EXTENT to all activity ----------*/
        initializeBaseAct();
        clickEventBaseAct();

    }


    public void initializeBaseAct(){
        try{

            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            left_drawer = (RelativeLayout) findViewById(R.id.left_drawer);
            rl_baseToolbar = (RelativeLayout) findViewById(R.id.rl_baseToolbar);
            ll_FullContainer = (LinearLayout) findViewById(R.id.ll_FullContainer);
            ll_SubMainContainer = (LinearLayout) findViewById(R.id.ll_SubMainContainer);

            ivMenu = (ImageView) findViewById(R.id.ivMenu);
            ivCorss = (ImageView) findViewById(R.id.ivCorss);
            ivCorssright = (ImageView) findViewById(R.id.ivCorssright);
            ivSearch = (ImageView) findViewById(R.id.ivSearch);
            ivDelete = (ImageView) findViewById(R.id.ivDelete);
            ivCart = (ImageView) findViewById(R.id.ivCart);
            ivBack = (ImageView) findViewById(R.id.ivBack);
            ivDot = (ImageView) findViewById(R.id.ivDot);
            ivFilter = (ImageView) findViewById(R.id.ivFilter);
            circularUserImg = (CircularImageView) findViewById(R.id.circularUserImg);

            tvTitle = (TextView) findViewById(R.id.tvTitle);
            tvName = (TextView) findViewById(R.id.tvName);
            tvEmailID = (TextView) findViewById(R.id.tvEmailID);
            spn_photos = (MaterialSpinner) findViewById(R.id.spn_photos);
            etSearch = (MaterialEditText) findViewById(R.id.etSearch);

            recyclerViewTopMenu = (RecyclerView) findViewById(R.id.recyclerViewTopMenu);
            recyclerViewBottomMenu = (RecyclerView) findViewById(R.id.recyclerViewBottomMenu);
            recyclerViewTopMenu.setNestedScrollingEnabled(false);  //recycleview smooth scrool inside nestedScrollview
            recyclerViewBottomMenu.setNestedScrollingEnabled(false);  //recycleview smooth scrool inside nestedScrollview

            setRecyclerViewTopMenu();
            setRecyclerViewBottomMenu();


            ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(ACTIVITY_SERVICE);
            taskInfo = am.getRunningTasks(1);
            App.showLog(TAG, "----------------------------------TopActivity: "+ taskInfo.get(0).topActivity.getClassName());
            componentInfo = taskInfo.get(0).topActivity;
            componentInfo.getPackageName();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setEnableDrawer(boolean blnEnable) {
        if (blnEnable == true) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }

    }


    public void clickEventBaseAct(){
        try{
            ivMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {

                    if (drawer.isDrawerOpen(left_drawer)) {
                        drawer.closeDrawers();
                    } else {
                        drawer.openDrawer(left_drawer);
                    }
                }
            });


            ivSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(BaseActivity.this, ActSearchBar.class);
                    startActivity(i1);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });


            /*circularUserImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(BaseActivity.this, ActKitchen.class);
                    startActivity(i1);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });

            tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(BaseActivity.this, ActKitchenRB.class);
                    startActivity(i1);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });

            tvEmailID.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(BaseActivity.this, ActKitchenGride.class);
                    startActivity(i1);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });*/



        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void setRecyclerViewTopMenu(){
        try{

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseActivity.this);
            recyclerViewTopMenu.setHasFixedSize(true);
            recyclerViewTopMenu.setLayoutManager(linearLayoutManager);
            recyclerViewTopMenu.setItemAnimator(new DefaultItemAnimator());



            String id[] = {"1", "2", "3", "4", "5", "6", "7"};
            int img[] = {R.drawable.ic_home_18dp, R.drawable.ic_user_grey_18dp, R.drawable.ic_shopping_cart_18dp, R.drawable.ic_addbook_18dp,
                    R.drawable.ic_order_list_18dp, R.drawable.ic_star_black_18dp, R.drawable.ic_payment_18dp};
            String name[] = {App.strHome, App.strProfile, App.strCart, App.strAddressBook, App.strMyOrder, App.strMyWishlist, App.strPayment};


            for(int i=0; i<id.length; i++){
                arrModelBaseAct.add(new BaseTopMenuModel(id[i], String.valueOf(img[i]), name[i]));
            }

            baseActivityAdapter = new BaseActivityAdapter(BaseActivity.this, arrModelBaseAct);
            recyclerViewTopMenu.setAdapter(baseActivityAdapter);
            recyclerViewTopMenu.setItemAnimator(new DefaultItemAnimator());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class BaseActivityAdapter extends RecyclerView.Adapter<BaseActivityAdapter.VersionViewHolder> {
        ArrayList<BaseTopMenuModel> arrModelBaseAct;
        Context mContext;


        public BaseActivityAdapter(Context context, ArrayList<BaseTopMenuModel> arrModelBaseAct) {
            this.arrModelBaseAct = arrModelBaseAct;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_baseactivity_menu, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder viewHolder, final int i) {
            try {

                viewHolder.tvName.setText(arrModelBaseAct.get(i).bname);
                viewHolder.ivImg.setImageResource(Integer.parseInt(arrModelBaseAct.get(i).bimg));


                viewHolder.ll_lay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (taskInfo.get(0).topActivity.getClassName().equals("com.eliteinfoworld.shoppingapp.activity.ActHome"))
                        {
                            drawer.closeDrawers();
                        }
                        else if(viewHolder.tvName.getText().toString().trim().equalsIgnoreCase(App.strHome))
                        {
                            Intent i1 = new Intent(BaseActivity.this, ActHome.class);
                            startActivity(i1);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (drawer.isDrawerOpen(left_drawer)) {
                                        drawer.closeDrawers();
                                    }
                                }
                            }, 600);
                        }


                        if (taskInfo.get(0).topActivity.getClassName().equals("com.eliteinfoworld.shoppingapp.activity.ActDesigner"))
                        {
                            drawer.closeDrawers();
                        }
                        else if(viewHolder.tvName.getText().toString().trim().equalsIgnoreCase(App.strProfile))
                        {
                            Intent i1 = new Intent(BaseActivity.this, ActDesigner.class);
                            startActivity(i1);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (drawer.isDrawerOpen(left_drawer)) {
                                        drawer.closeDrawers();
                                    }
                                }
                            }, 600);
                        }

                        if (taskInfo.get(0).topActivity.getClassName().equals("com.eliteinfoworld.shoppingapp.activity.ActWishList"))
                        {
                            drawer.closeDrawers();
                        }
                        else if(viewHolder.tvName.getText().toString().trim().equalsIgnoreCase(App.strMyWishlist))
                        {
                            Intent i1 = new Intent(BaseActivity.this, ActWishList.class);
                            startActivity(i1);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (drawer.isDrawerOpen(left_drawer)) {
                                        drawer.closeDrawers();
                                    }
                                }
                            }, 600);
                        }


                        if (taskInfo.get(0).topActivity.getClassName().equals("com.eliteinfoworld.shoppingapp.activity.ActCheckOut"))
                        {
                            drawer.closeDrawers();
                        }
                        else  if(viewHolder.tvName.getText().toString().trim().equalsIgnoreCase(App.strPayment))
                        {
                            Intent i1 = new Intent(BaseActivity.this, ActCheckOut.class);
                            startActivity(i1);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (drawer.isDrawerOpen(left_drawer)) {
                                        drawer.closeDrawers();
                                    }
                                }
                            }, 600);
                        }


                        if (taskInfo.get(0).topActivity.getClassName().equals("com.eliteinfoworld.shoppingapp.activity.ActCart"))
                        {
                            drawer.closeDrawers();
                        }
                        else if(viewHolder.tvName.getText().toString().trim().equalsIgnoreCase(App.strCart))
                        {
                            Intent i1 = new Intent(BaseActivity.this, ActCart.class);
                            startActivity(i1);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (drawer.isDrawerOpen(left_drawer)) {
                                        drawer.closeDrawers();
                                    }
                                }
                            }, 600);
                        }



                        if (taskInfo.get(0).topActivity.getClassName().equals("com.eliteinfoworld.shoppingapp.activity.ActAddressDetail"))
                        {
                            drawer.closeDrawers();
                        }
                        else if(viewHolder.tvName.getText().toString().trim().equalsIgnoreCase(App.strAddressBook))
                        {
                            Intent i1 = new Intent(BaseActivity.this, ActAddressDetail.class);
                            startActivity(i1);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (drawer.isDrawerOpen(left_drawer)) {
                                        drawer.closeDrawers();
                                    }
                                }
                            }, 600);
                        }



                        if (taskInfo.get(0).topActivity.getClassName().equals("com.eliteinfoworld.shoppingapp.activity.ActCardDetail"))
                        {
                            drawer.closeDrawers();
                        }
                        else if(viewHolder.tvName.getText().toString().trim().equalsIgnoreCase(App.strMyOrder))
                        {
                            Intent i1 = new Intent(BaseActivity.this, ActCardDetail.class);
                            startActivity(i1);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (drawer.isDrawerOpen(left_drawer)) {
                                        drawer.closeDrawers();
                                    }
                                }
                            }, 600);
                        }
                    }
                });


                /*viewHolder.ll_lay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        switch(event.getAction()) {

                            case MotionEvent.ACTION_DOWN:
                                // PRESSED
                                viewHolder.tvName.setTextColor(getResources().getColor(R.color.colorPrimary));
                                viewHolder.ivImg.setColorFilter(ContextCompat.getColor(BaseActivity.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
                                viewHolder.ll_lay.setBackgroundColor(getResources().getColor(R.color.greyLight));
                                return true; // if you want to handle the touch event

                            case MotionEvent.ACTION_UP:
                                // RELEASED
                                viewHolder.tvName.setTextColor(getResources().getColor(R.color.black));
                                viewHolder.ivImg.setColorFilter(ContextCompat.getColor(BaseActivity.this, R.color.grey), android.graphics.PorterDuff.Mode.MULTIPLY);
                                viewHolder.ll_lay.setBackgroundColor(getResources().getColor(R.color.white));
                                return true; // if you want to handle the touch event

                            case MotionEvent.ACTION_CANCEL:
                                // RELEASED
                                viewHolder.tvName.setTextColor(getResources().getColor(R.color.black));
                                viewHolder.ivImg.setColorFilter(ContextCompat.getColor(BaseActivity.this, R.color.grey), android.graphics.PorterDuff.Mode.MULTIPLY);
                                viewHolder.ll_lay.setBackgroundColor(getResources().getColor(R.color.white));
                                return true; // if you want to handle the touch event
                        }
                        return true;


                    }
                });*/


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrModelBaseAct.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder {
            LinearLayout ll_lay;
            ImageView ivImg;
            TextView tvName;

            public VersionViewHolder(View itemView) {
                super(itemView);
                ll_lay = (LinearLayout) itemView.findViewById(R.id.ll_lay);
                ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
                tvName = (TextView) itemView.findViewById(R.id.tvName);

            }

        }
    }




    public void setRecyclerViewBottomMenu(){
        try{

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseActivity.this);
            recyclerViewBottomMenu.setHasFixedSize(true);
            recyclerViewBottomMenu.setLayoutManager(linearLayoutManager);
            recyclerViewBottomMenu.setItemAnimator(new DefaultItemAnimator());



            String id[] = {"1", "2", "3", "4", "5"};
            int img[] = {R.drawable.ic_user_grey_18dp, R.drawable.ic_kitchen_black_18dp, R.drawable.ic_addbook_grey_24dp,
                    R.drawable.ic_order_list_24dp, R.drawable.ic_star_black_18dp, R.drawable.ic_payment_grey_24dp};
            String name[] = {App.strAccount, App.strKitchen, App.strDesigners, App.strProductDetail, App.strKitchenRadio, App.strPayment};


            for(int i=0; i<id.length; i++){
                arrBottomMenu.add(new BaseBottomMenuModel(id[i], String.valueOf(img[i]), name[i]));
            }

            baseBottomAdapter = new BaseActivityBottomAdapter(BaseActivity.this, arrBottomMenu);
            recyclerViewBottomMenu.setAdapter(baseBottomAdapter);
            recyclerViewBottomMenu.setItemAnimator(new DefaultItemAnimator());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class BaseActivityBottomAdapter extends RecyclerView.Adapter<BaseActivityBottomAdapter.VersionViewHolder> {
        ArrayList<BaseBottomMenuModel> arrBottomMenu;
        Context mContext;


        public BaseActivityBottomAdapter(Context context, ArrayList<BaseBottomMenuModel> arrBottomMenu) {
            this.arrBottomMenu = arrBottomMenu;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_baseactivity_menu, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder viewHolder, final int i) {
            try {

                viewHolder.tvName.setText(arrBottomMenu.get(i).bname);
                viewHolder.ivImg.setImageResource(Integer.parseInt(arrBottomMenu.get(i).bimg));

                /*String mPackageName = mActivityManager.getRunningAppProcesses().get(0).processName;
                App.showLog(TAG, "---------------------------baseActivity---------------------------"+taskInfo.get(0).baseActivity.getClassName());
                App.showLog(TAG, "---------------------------baseActivity---------------------------"+this.getClass().getSimpleName());
*/
                if(viewHolder.tvName.getText().toString().trim().equalsIgnoreCase(taskInfo.get(0).baseActivity.getClassName())){

                }


                viewHolder.ll_lay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (taskInfo.get(0).topActivity.getClassName().equals("com.eliteinfoworld.shoppingapp.activity.ActKitchen"))
                        {
                            drawer.closeDrawers();
                        }
                        else if(viewHolder.tvName.getText().toString().trim().equalsIgnoreCase(App.strKitchen))
                        {
                            Intent i1 = new Intent(BaseActivity.this, ActKitchen.class);
                            startActivity(i1);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (drawer.isDrawerOpen(left_drawer)) {
                                        drawer.closeDrawers();
                                    }
                                }
                            }, 600);
                        }



                        if (taskInfo.get(0).topActivity.getClassName().equals("com.eliteinfoworld.shoppingapp.activity.ActDesigner"))
                        {
                            drawer.closeDrawers();
                        }
                        else if(viewHolder.tvName.getText().toString().trim().equalsIgnoreCase(App.strDesigners))
                        {
                            Intent i1 = new Intent(BaseActivity.this, ActDesigner.class);
                            startActivity(i1);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (drawer.isDrawerOpen(left_drawer)) {
                                        drawer.closeDrawers();
                                    }
                                }
                            }, 600);
                        }



                        if (taskInfo.get(0).topActivity.getClassName().equals("com.eliteinfoworld.shoppingapp.activity.ActProductDetails"))
                        {
                            drawer.closeDrawers();
                        }
                        else if(viewHolder.tvName.getText().toString().trim().equalsIgnoreCase(App.strProductDetail))
                        {
                            Intent i1 = new Intent(BaseActivity.this, ActProductDetails.class);
                            startActivity(i1);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (drawer.isDrawerOpen(left_drawer)) {
                                        drawer.closeDrawers();
                                    }
                                }
                            }, 600);
                        }




                        if (taskInfo.get(0).topActivity.getClassName().equals("com.eliteinfoworld.shoppingapp.activity.ActKitchenRB"))
                        {
                            drawer.closeDrawers();
                        }
                        else if(viewHolder.tvName.getText().toString().trim().equalsIgnoreCase(App.strKitchenRadio))
                        {
                            Intent i1 = new Intent(BaseActivity.this, ActKitchenRB.class);
                            startActivity(i1);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (drawer.isDrawerOpen(left_drawer)) {
                                        drawer.closeDrawers();
                                    }
                                }
                            }, 600);
                        }



                    }
                });



            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrBottomMenu.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder {
            LinearLayout ll_lay;
            ImageView ivImg;
            TextView tvName;

            public VersionViewHolder(View itemView) {
                super(itemView);
                ll_lay = (LinearLayout) itemView.findViewById(R.id.ll_lay);
                ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
                tvName = (TextView) itemView.findViewById(R.id.tvName);

            }

        }
    }



    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        //	setCloseDrawerMenu(true);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(ll_FullContainer.getWindowToken(), 0);
        super.onPause();
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
