package com.eliteinfoworld.shoppingapp.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.eliteinfoworld.shoppingapp.R;

/**
 * Created by Avinash Kahal on 20-Sep-17.
 */

public class App extends Application {

    Context context;
    public static String PREF_NAME = "Shopping_PREFERENCE";
    public static SharePrefrences sharePrefrences;

        /*
        1. App.sharePrefrences.setPref("bpmPreviousvalue", beatsAvg + "");
        2. App.sharePrefrences.getStringPref("fname");
        */

    /*---- sharePrefrences string ---*/
    public static String sh_skip = "Skip";



    /*---- Side Menu Name ----*/
    public static String strHome = "Home";
    public static String strProfile = "Profile";
    public static String strCart = "Cart";
    public static String strAddressBook = "Address Book";
    public static String strMyOrder = "My Orders";
    public static String strMyWishlist = "My Wishlist";
    public static String strPayment = "Payment";

    public static String strAccount = "Account";
    public static String strKitchen = "Kitchen";
    public static String strDesigners = "Designers";
    public static String strProductDetail = "Product Details";
    public static String strKitchenRadio = "Kitchen Radio";
    public static String strLogout = "Logout";

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        sharePrefrences = new SharePrefrences(App.this);

    }


    public static void showLog(String ActivityName, String strMessage) {
        Log.d("From: ", ActivityName+" -- "+strMessage);
    }


    /*--------- FOR STATUS BAR TRANSPARENT ----------*/
    public static void setStatusBarTranslucent(boolean makeTranslucent, Activity activity) {
        if (makeTranslucent) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }



    public static void setStatusBarColor(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            window.setStatusBarColor(ContextCompat.getColor(activity, R.color.greyDark));
        }
    }

    public static void showSnackBar(View view, String strMessage) {
        Snackbar snackbar = Snackbar.make(view, strMessage, Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.BLACK);
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }
}
