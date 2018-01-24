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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.eliteinfoworld.shoppingapp.R;
import com.eliteinfoworld.shoppingapp.api.model.CheckoutModel;
import com.eliteinfoworld.shoppingapp.api.model.PaymentDetailsModel;
import com.eliteinfoworld.shoppingapp.api.model.ShippingDetailsModel;

import java.util.ArrayList;

public class ActCheckOut extends BaseActivity {

    String TAG = "ActCheckOut";
    RecyclerView recyclerViewCart, recyclerViewShippDetail, recyclerViewPayment;
    
    ArrayList<CheckoutModel> arrCheckoutModel = new ArrayList<CheckoutModel>();
    CheckOutAdapter checkOutAdapter;

    ArrayList<ShippingDetailsModel> arrShipDetailModel = new ArrayList<ShippingDetailsModel>();
    ShipDeatilAdapter shipDeatilAdapter;

    ArrayList<PaymentDetailsModel> arrpaymentDetail = new ArrayList<PaymentDetailsModel>();
    PaymentAdapter paymentAdapter;

    TextView tvPlaceOrder;
    LinearLayout llTotalPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.act_home);
        ViewGroup.inflate(this, R.layout.act_checkout, ll_SubMainContainer);



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
            tvTitle.setText("Checkout");
            ivMenu.setVisibility(View.GONE);
            ivSearch.setVisibility(View.GONE);
            ivBack.setVisibility(View.VISIBLE);
            setEnableDrawer(false);  //closing menu bar


            /*----- This Activity -----*/
            tvPlaceOrder = (TextView)findViewById(R.id.tvPlaceOrder);
            llTotalPrice = (LinearLayout) findViewById(R.id.llTotalPrice);
            recyclerViewCart = (RecyclerView) findViewById(R.id.recyclerViewCart);
            recyclerViewShippDetail = (RecyclerView) findViewById(R.id.recyclerViewShippDetail);
            recyclerViewPayment = (RecyclerView) findViewById(R.id.recyclerViewPayment);


            setCheckoutData();
            setShippingDetail();
            setPayementDetail();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickEvent(){
        try{

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

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void setCheckoutData(){
        try{

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerViewCart.setLayoutManager(linearLayoutManager);
            recyclerViewCart.setHasFixedSize(true);


            String productid[] = {"1", "2"};
            int productimg[] = {R.drawable.img_transparent1, R.drawable.img_transparent2};
            String productName[] = {"Air Conditioner", "Electric Fans"};
            String compamyName[] = {"Bharat Electronics", "Intex"};
            String qty[] = {"3", "4"};
            String price[] = {"412", "130"};


            for(int i=0; i<productid.length; i++){
                arrCheckoutModel.add(new CheckoutModel(productid[i], String.valueOf(productimg[i]), productName[i], compamyName[i], qty[i], price[i]));
            }


            checkOutAdapter = new CheckOutAdapter(this, arrCheckoutModel);
            recyclerViewCart.setAdapter(checkOutAdapter);
            recyclerViewCart.setItemAnimator(new DefaultItemAnimator());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class CheckOutAdapter extends RecyclerView.Adapter<CheckOutAdapter.VersionViewHolder> {
        ArrayList<CheckoutModel> arrCheckoutModel;
        Context mContext;
        String qtyList[] = {"1", "2", "3", "4", "5"};



        public CheckOutAdapter(Context context, ArrayList<CheckoutModel> arrCheckoutModel) {
            this.arrCheckoutModel = arrCheckoutModel;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_checkout, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
            try {

                versionViewHolder.ivImg.setImageResource(Integer.parseInt(arrCheckoutModel.get(i).productimg));
                versionViewHolder.tvProductName.setText(arrCheckoutModel.get(i).productName);
                versionViewHolder.tvCompanyName.setText(arrCheckoutModel.get(i).compamyName);
                versionViewHolder.tvPrice.setText("$"+arrCheckoutModel.get(i).price);

                versionViewHolder.spnQty.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                        //Snackbar.make(view, "QTY: " + item+" selected", Snackbar.LENGTH_LONG).show();
                        versionViewHolder.spnQty.setText("QTY:" + item);
                        versionViewHolder.tvPrice.setText("$" + Integer.parseInt(arrCheckoutModel.get(i).price) * Integer.parseInt(item));
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrCheckoutModel.size();
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
                spnQty.setText("QTY:" + qtyList[0]);

            }

        }
    }



    public void setShippingDetail(){
        try{

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerViewShippDetail.setLayoutManager(linearLayoutManager);
            recyclerViewShippDetail.setHasFixedSize(true);


            String productid[] = {"1", "2"};
            String compamyName[] = {"Bharat Electronics", "Intex"};
            String days[] = {"1 day", "1-2 weeks"};
            String price[] = {"$20", "Free"};
            String checkbox[] = {"1", "0"};


            for(int i=0; i<productid.length; i++){
                arrShipDetailModel.add(new ShippingDetailsModel(productid[i], compamyName[i], days[i], price[i], checkbox[i]));
            }


            shipDeatilAdapter = new ShipDeatilAdapter(this, arrShipDetailModel);
            recyclerViewShippDetail.setAdapter(shipDeatilAdapter);
            recyclerViewShippDetail.setItemAnimator(new DefaultItemAnimator());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class ShipDeatilAdapter extends RecyclerView.Adapter<ShipDeatilAdapter.VersionViewHolder> {
        ArrayList<ShippingDetailsModel> arrShipDetailModel;
        Context mContext;



        public ShipDeatilAdapter(Context context, ArrayList<ShippingDetailsModel> arrShipDetailModel) {
            this.arrShipDetailModel = arrShipDetailModel;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_shipdetail, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
            try {

                versionViewHolder.tvCompanyName.setText(arrShipDetailModel.get(i).compamyName);
                versionViewHolder.tvDay.setText(arrShipDetailModel.get(i).days);
                versionViewHolder.tvPrice.setText(arrShipDetailModel.get(i).price);

                if(arrShipDetailModel.get(i).checkbox.equalsIgnoreCase("1")){
                    versionViewHolder.checkbox.setChecked(true);
                }else {
                    versionViewHolder.checkbox.setChecked(false);
                }


                versionViewHolder.rl_fullLay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(versionViewHolder.checkbox.isChecked() == true){
                            versionViewHolder.checkbox.setChecked(false);
                        }else {
                            versionViewHolder.checkbox.setChecked(true);
                        }
                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrShipDetailModel.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder {
            RelativeLayout rl_fullLay;
            TextView tvCompanyName, tvDay, tvPrice;
            CheckBox checkbox;

            public VersionViewHolder(View itemView) {
                super(itemView);

                rl_fullLay = (RelativeLayout) itemView.findViewById(R.id.rl_fullLay);
                tvCompanyName = (TextView) itemView.findViewById(R.id.tvCompanyName);
                tvDay = (TextView) itemView.findViewById(R.id.tvDay);
                checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
                tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);

            }

        }
    }



    public void setPayementDetail(){
        try{

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerViewPayment.setLayoutManager(linearLayoutManager);
            recyclerViewPayment.setHasFixedSize(true);


            String productid[] = {"1", "2"};
            int cardImg[] = {R.drawable.img_mastercard, R.drawable.img_paypal};
            String cardName[] = {"Credit card:", "Pay pal"};
            String cardNumber[] = {"42** **** **** ****", "please log in"};

            for(int i=0; i<productid.length; i++){
                arrpaymentDetail.add(new PaymentDetailsModel(productid[i], String.valueOf(cardImg[i]), cardName[i], cardNumber[i]));
            }


            paymentAdapter = new PaymentAdapter(this, arrpaymentDetail);
            recyclerViewPayment.setAdapter(paymentAdapter);
            recyclerViewPayment.setItemAnimator(new DefaultItemAnimator());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.VersionViewHolder> {
        ArrayList<PaymentDetailsModel> arrpaymentDetail;
        Context mContext;



        public PaymentAdapter(Context context, ArrayList<PaymentDetailsModel> arrpaymentDetail) {
            this.arrpaymentDetail = arrpaymentDetail;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_paymentdetail, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
            try {


                versionViewHolder.ivCardImg.setImageResource(Integer.parseInt(arrpaymentDetail.get(i).cardImg));
                versionViewHolder.tvCardName.setText(arrpaymentDetail.get(i).cardName);
                versionViewHolder.tvCardNumber.setText(arrpaymentDetail.get(i).cardNumber);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrpaymentDetail.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder {
            ImageView ivCardImg;
            TextView tvCardName, tvCardNumber;

            public VersionViewHolder(View itemView) {
                super(itemView);

                ivCardImg = (ImageView) itemView.findViewById(R.id.ivCardImg);
                tvCardName = (TextView) itemView.findViewById(R.id.tvCardName);
                tvCardNumber = (TextView) itemView.findViewById(R.id.tvCardNumber);

            }

        }
    }


    public void showDialog1(){
        {

            final Dialog dialog = new Dialog(ActCheckOut.this);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            //dialog.getWindow().setBackgroundDrawableResource(R.drawable.prograss_bg); //temp removed
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.popup_checkout);
            dialog.show();

            TextView tvTrackOrder = (TextView) dialog.findViewById(R.id.tvTrackOrder);
            TextView tvContinueShopping = (TextView) dialog.findViewById(R.id.tvContinueShopping);

            tvTrackOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                    Toast.makeText(ActCheckOut.this, "Order will be track soon", Toast.LENGTH_SHORT).show();
                }
            });


            tvContinueShopping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                    Toast.makeText(ActCheckOut.this, "Goto home", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


    public void showDialog2(){
        {

            final Dialog dialog = new Dialog(ActCheckOut.this);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            //dialog.getWindow().setBackgroundDrawableResource(R.drawable.prograss_bg); //temp removed
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.popup_checkout2);
            dialog.show();

            TextView tvTrackOrder = (TextView) dialog.findViewById(R.id.tvTrackOrder);
            Button tvContinueShopping = (Button) dialog.findViewById(R.id.tvContinueShopping);
            TextView tvNewsletter = (TextView) dialog.findViewById(R.id.tvNewsletter);



            tvNewsletter.setText(R.string.newsLetter);


            tvTrackOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                    Toast.makeText(ActCheckOut.this, "Order will be track soon", Toast.LENGTH_SHORT).show();
                }
            });


            tvContinueShopping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                    Toast.makeText(ActCheckOut.this, "Goto home", Toast.LENGTH_SHORT).show();
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
