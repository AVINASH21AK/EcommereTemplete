package com.eliteinfoworld.shoppingapp.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.eliteinfoworld.shoppingapp.R;

public class ActCardDetail extends BaseActivity {

    String TAG = "ActAddressDetail";
    CountryCodePicker ccp;
    MaterialEditText etCreditCard, etCardMMYY;
    WebView wvTermsCondition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.act_home);
        ViewGroup.inflate(this, R.layout.act_card_detail, ll_SubMainContainer);



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
            tvTitle.setText("Enter card details");
            ivMenu.setVisibility(View.GONE);
            ivSearch.setVisibility(View.GONE);
            ivBack.setVisibility(View.VISIBLE);
            setEnableDrawer(false);  //closing menu bar



            /*----- This Activity -----*/
            ccp = (CountryCodePicker) findViewById(R.id.ccp);


            etCreditCard = (MaterialEditText) findViewById(R.id.etCreditCard);
            etCardMMYY = (MaterialEditText) findViewById(R.id.etCardMMYY);

            String termsNcondition = "<html><head>"
                    + "<style type=\"text/css\">body{color: #ababab;}"   // background-color: #ababab;
                    + "</style></head>"
                    + "<body>"
                    + "By continuing, you add this payment method to your Payments account and agree to the Payments <u>Terms of Service</u> and <u>Privacy Notice</u>"
                    + "</body></html>";

            wvTermsCondition = (WebView) findViewById(R.id.wvTermsCondition);

            WebSettings webSettings = wvTermsCondition.getSettings();
            webSettings.setDefaultFontSize(14);
            wvTermsCondition.loadData(termsNcondition, "text/html; charset=utf-8", "utf-8");

            etCreditCard.addTextChangedListener(new CreditCardNumberFormattingTextWatcher());
            etCardMMYY.addTextChangedListener(new CreditCardDateFormattingTextWatcher());

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static class CreditCardNumberFormattingTextWatcher implements TextWatcher {

        private boolean lock;

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (lock || s.length() > 16) {
                return;
            }
            lock = true;
            for (int i = 4; i < s.length(); i += 5) {
                if (s.toString().charAt(i) != ' ') {
                    s.insert(i, " ");
                }
            }
            lock = false;
        }
    }

    public static class CreditCardDateFormattingTextWatcher implements TextWatcher {

        private boolean lock;

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (lock || s.length() > 4) {
                return;
            }
            lock = true;
            for (int i = 2; i < s.length(); i += 3) {
                if (s.toString().charAt(i) != ' ') {
                    s.insert(i, "/");
                }
            }
            lock = false;
        }
    }


    public void clickEvent(){
        try{
            ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
                @Override
                public void onCountrySelected() {
                    Snackbar.make(ccp, "Country: " + ccp.getSelectedCountryName(), Snackbar.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(), "Updated " + ccp.getSelectedCountryName(), Toast.LENGTH_SHORT).show();
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
