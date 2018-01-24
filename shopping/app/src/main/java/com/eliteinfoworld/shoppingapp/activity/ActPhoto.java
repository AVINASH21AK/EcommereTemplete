package com.eliteinfoworld.shoppingapp.activity;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eliteinfoworld.shoppingapp.R;
import com.eliteinfoworld.shoppingapp.api.model.PhotosModel;
import com.eliteinfoworld.shoppingapp.utils.App;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;

public class ActPhoto extends BaseActivity {

    String TAG = "ActPhoto";
    RecyclerView recyclerViewPhotos;
    ArrayList<PhotosModel> arrayListPhotos= new ArrayList<PhotosModel>();
    PhotosAdapter photosAdapter;
    private static final String photo_list[] = {"Photo", "Furniture", "Computer", "Mobile"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.act_home);
        ViewGroup.inflate(this, R.layout.act_photo, ll_SubMainContainer);

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
            ivMenu.setVisibility(View.GONE);
            ivBack.setVisibility(View.VISIBLE);
            tvTitle.setVisibility(View.GONE);
            ivFilter.setVisibility(View.VISIBLE);
            spn_photos.setVisibility(View.VISIBLE);
            setEnableDrawer(false);  //closing menu bar



            /*----- This Activity -----*/
            spn_photos.setItems(photo_list);

            recyclerViewPhotos = (RecyclerView)findViewById(R.id.recyclerViewPhotos);
            recyclerViewPhotos.setNestedScrollingEnabled(false);  //recycleview smooth scrool inside nestedScrollview


            setRecycleviewData();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickEvent(){
        try{

            spn_photos.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                @Override
                public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                    spn_photos.setText(""+item);
                    Toast.makeText(ActPhoto.this, item+" pass & call api", Toast.LENGTH_SHORT).show();
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


    public void setRecycleviewData(){
        try{

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerViewPhotos.setLayoutManager(linearLayoutManager);
            recyclerViewPhotos.setHasFixedSize(true);


            String productid[] = {"1", "2", "3", "4", "5", "6"};
            int productimg[] = {R.drawable.img_transparent1, R.drawable.img_transparent2, R.drawable.img_transparent3,
                    R.drawable.img_transparent6, R.drawable.img_transparent5, R.drawable.img_transparent7};
            String productName[] = {"Air Conditioner", "Electric Fans", "Air Coolers", "Table Fan", "Exhaust Fans", "Air Ventilators"};
            String compamyName[] = {"Bharat Electronics", "Intex", "Voltas", "Intex", "Voltas", "Intex"};
            String price[] = {"412", "25", "130", "221", "150", "300"};
            String rating[] = {"3", "4", "2", "5", "3", "1"};
            String totalRating[] = {"412", "25", "130", "221", "150", "300"};
           

            for(int i=0; i<productid.length; i++){
                arrayListPhotos.add(new PhotosModel(productid[i], String.valueOf(productimg[i]),
                        productName[i], compamyName[i], rating[i], price[i], totalRating[i]));
            }


            photosAdapter = new PhotosAdapter(this, arrayListPhotos);
            recyclerViewPhotos.setAdapter(photosAdapter);
            recyclerViewPhotos.setItemAnimator(new DefaultItemAnimator());

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.VersionViewHolder> {
        ArrayList<PhotosModel> arrayListPhotos;
        Context mContext;



        public PhotosAdapter(Context context, ArrayList<PhotosModel> arrayListPhotos) {
            this.arrayListPhotos = arrayListPhotos;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_photos, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
            try {

                versionViewHolder.ivImg.setImageResource(Integer.parseInt(arrayListPhotos.get(i).productimg));
                versionViewHolder.tvProductName.setText(arrayListPhotos.get(i).productName);
                versionViewHolder.tvCompanyName.setText(arrayListPhotos.get(i).compamyName);
                versionViewHolder.tvPrice.setText("$"+arrayListPhotos.get(i).price);
                versionViewHolder.tvTotalRating.setText(arrayListPhotos.get(i).totalrating);
                versionViewHolder.tvRatingBar.setRating(Float.parseFloat(arrayListPhotos.get(i).rating));


                LayerDrawable stars = (LayerDrawable) versionViewHolder.tvRatingBar.getProgressDrawable();
                stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.greyDark), PorterDuff.Mode.SRC_ATOP);
                stars.getDrawable(1).setColorFilter(getResources().getColor(R.color.greyDark), PorterDuff.Mode.SRC_ATOP);
                stars.getDrawable(0).setColorFilter(getResources().getColor(R.color.greyLight), PorterDuff.Mode.SRC_ATOP);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrayListPhotos.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder {
            ImageView ivImg;
            TextView tvProductName, tvCompanyName, tvPrice, tvTotalRating;
            RatingBar tvRatingBar;

            public VersionViewHolder(View itemView) {
                super(itemView);

                ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
                tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
                tvCompanyName = (TextView) itemView.findViewById(R.id.tvCompanyName);
                tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
                tvTotalRating = (TextView) itemView.findViewById(R.id.tvTotalRating);
                tvRatingBar = (RatingBar) itemView.findViewById(R.id.tvRatingBar);

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
