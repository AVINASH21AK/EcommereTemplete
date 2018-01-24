package com.eliteinfoworld.shoppingapp.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.eliteinfoworld.shoppingapp.R;
import com.eliteinfoworld.shoppingapp.api.model.CartModel;
import com.eliteinfoworld.shoppingapp.api.model.SpecialDetailsModel;

import java.util.ArrayList;

public class ActCart extends BaseActivity {

    String TAG = "ActCheckOut";
    RecyclerView recyclerViewCart, recyclerViewSpecial;

    ArrayList<CartModel> arrCartModel = new ArrayList<CartModel>();
    CartAdapter cartAdapter;

    ArrayList<SpecialDetailsModel> arrSpecialDetail = new ArrayList<SpecialDetailsModel>();
    SpecialAdapter specialAdapter;

    TextView tvPlaceOrder, tvTotalPrice;
    LinearLayout llTotalPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.act_home);
        ViewGroup.inflate(this, R.layout.act_cart, ll_SubMainContainer);


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
            tvTitle.setText("Cart");
            ivMenu.setVisibility(View.GONE);
            ivSearch.setVisibility(View.GONE);
            ivBack.setVisibility(View.VISIBLE);
            setEnableDrawer(false);  //closing menu bar


            /*----- This Activity -----*/
            tvPlaceOrder = (TextView) findViewById(R.id.tvPlaceOrder);
            tvTotalPrice = (TextView) findViewById(R.id.tvTotalPrice);
            llTotalPrice = (LinearLayout) findViewById(R.id.llTotalPrice);
            recyclerViewCart = (RecyclerView) findViewById(R.id.recyclerViewCart);
            recyclerViewSpecial = (RecyclerView) findViewById(R.id.recyclerViewSpecial);


            setCartData();
            setSpecialDetail();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void clickEvent() {
        try {

            llTotalPrice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog1();
                }
            });

            tvPlaceOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog2();
                }
            });


            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setCartData() {
        try {

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerViewCart.setLayoutManager(linearLayoutManager);
            recyclerViewCart.setHasFixedSize(true);


            String productid[] = {"1", "2", "3"};
            int productimg[] = {R.drawable.img_transparent1, R.drawable.img_transparent2, R.drawable.img_transparent3};
            String productName[] = {"Air Conditioner", "Electric Fans", "Electric Fans"};
            String compamyName[] = {"Bharat Electronics", "Intex", "Intex"};
            String qty[] = {"3", "4", "1"};
            String price[] = {"412", "130", "130"};


            for (int i = 0; i < productid.length; i++) {
                arrCartModel.add(new CartModel(productid[i], String.valueOf(productimg[i]), productName[i], compamyName[i], qty[i], price[i]));
            }


            cartAdapter = new CartAdapter(this, arrCartModel);
            recyclerViewCart.setAdapter(cartAdapter);
            recyclerViewCart.setItemAnimator(new DefaultItemAnimator());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class CartAdapter extends RecyclerView.Adapter<CartAdapter.VersionViewHolder> {
        ArrayList<CartModel> arrCartModel;
        Context mContext;
        String qtyList[] = {"1", "2", "3", "4", "5"};


        public CartAdapter(Context context, ArrayList<CartModel> arrCartModel) {
            this.arrCartModel = arrCartModel;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_cart, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder holder, final int i) {
            try {

                holder.ivImg.setImageResource(Integer.parseInt(arrCartModel.get(i).productimg));
                holder.tvProductName.setText(arrCartModel.get(i).productName);
                holder.tvCompanyName.setText(arrCartModel.get(i).compamyName);
                holder.tvPrice.setText("$" + arrCartModel.get(i).price);


                holder.spnQty.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                        //Snackbar.make(view, "QTY: " + item+" selected", Snackbar.LENGTH_LONG).show();
                        holder.spnQty.setText("QTY:" + item);
                        holder.tvPrice.setText("$" + Integer.parseInt(arrCartModel.get(i).price) * Integer.parseInt(item));


                         /*  -- best way to do it


                https://stackoverflow.com/questions/36422905/what-is-proper-logic-to-update-text-view-on-fragment-after-change-in-item-of-rec

                1. when spinner is click set that quantity and price to model
                2. and with for loop calculate it

                */

                      /*  arrCartModel.get(i).price = ""+ Integer.parseInt(arrCartModel.get(i).price) * Integer.parseInt(item);
                        for(int i1=0; i1<arrCartModel.size(); i1++){
                            totalPrice = totalPrice + ( Integer.parseInt(arrCartModel.get(i1).price) ) ;

                            tvTotalPrice.setText(""+totalPrice);
                        }*/


                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrCartModel.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder {
            ImageView ivImg, ivDots;
            TextView tvProductName, tvCompanyName, tvPrice;
            MaterialSpinner spnQty;

            public VersionViewHolder(View itemView) {
                super(itemView);

                ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
                tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
                ivDots = (ImageView) itemView.findViewById(R.id.ivDots);
                tvCompanyName = (TextView) itemView.findViewById(R.id.tvCompanyName);
                tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
                spnQty = (MaterialSpinner) itemView.findViewById(R.id.spnQty);

                spnQty.setItems(qtyList);
                spnQty.setText("QTY:"+qtyList[0]);

            }

        }
    }


    public void setSpecialDetail() {
        try {

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerViewSpecial.setLayoutManager(linearLayoutManager);
            recyclerViewSpecial.setHasFixedSize(true);


            String productid[] = {"1", "2"};
            int cardImg[] = {R.drawable.ic_payment_violet_24dp, R.drawable.ic_payment_grey_24dp};
            String cardName[] = {"Promo code", "Gift card"};
            String cardNumber[] = {"42** **** **** ****", "please login"};

            for (int i = 0; i < productid.length; i++) {
                arrSpecialDetail.add(new SpecialDetailsModel(productid[i], String.valueOf(cardImg[i]), cardName[i], cardNumber[i]));
            }


            specialAdapter = new SpecialAdapter(this, arrSpecialDetail);
            recyclerViewSpecial.setAdapter(specialAdapter);
            recyclerViewSpecial.setItemAnimator(new DefaultItemAnimator());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class SpecialAdapter extends RecyclerView.Adapter<SpecialAdapter.VersionViewHolder> {
        ArrayList<SpecialDetailsModel> arrSpecialDetail;
        Context mContext;


        public SpecialAdapter(Context context, ArrayList<SpecialDetailsModel> arrSpecialDetail) {
            this.arrSpecialDetail = arrSpecialDetail;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_specialdetail, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
            try {


                versionViewHolder.ivImg.setImageResource(Integer.parseInt(arrSpecialDetail.get(i).cardImg));
                versionViewHolder.tvtxt.setText(arrSpecialDetail.get(i).cardName);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrSpecialDetail.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder {
            ImageView ivImg;
            TextView tvtxt;

            public VersionViewHolder(View itemView) {
                super(itemView);

                ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
                tvtxt = (TextView) itemView.findViewById(R.id.tvtxt);

            }

        }
    }


    public void showDialog1() {
        {

            final Dialog dialog = new Dialog(ActCart.this);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            //dialog.getWindow().setBackgroundDrawableResource(R.drawable.prograss_bg); //temp removed
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.popup_checkout);
            dialog.show();

            TextView tvTrackOrder = (TextView) dialog.findViewById(R.id.tvTrackOrder);
            TextView tvContinueShopping = (TextView) dialog.findViewById(R.id.tvContinueShopping);
            RelativeLayout rl_notification = (RelativeLayout) dialog.findViewById(R.id.rl_notification);


            rl_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            tvTrackOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                    Toast.makeText(ActCart.this, "Order will be track soon", Toast.LENGTH_SHORT).show();
                }
            });


            tvContinueShopping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                    Toast.makeText(ActCart.this, "Goto home", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


    public void showDialog2() {
        {

            final Dialog dialog = new Dialog(ActCart.this);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            //dialog.getWindow().setBackgroundDrawableResource(R.drawable.prograss_bg); //temp removed
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.popup_checkout2);
            dialog.show();

            TextView tvTrackOrder = (TextView) dialog.findViewById(R.id.tvTrackOrder);
            TextView tvContinueShopping = (TextView) dialog.findViewById(R.id.tvContinueShopping);

            tvTrackOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                    Toast.makeText(ActCart.this, "Order will be track soon", Toast.LENGTH_SHORT).show();
                }
            });


            tvContinueShopping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                    Toast.makeText(ActCart.this, "Goto home", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
