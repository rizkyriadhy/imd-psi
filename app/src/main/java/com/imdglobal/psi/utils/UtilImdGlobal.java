package com.imdglobal.psi.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.imdglobal.psi.R;

/**
 * Created by rizkyriadhy on 19/06/17.
 */
public class UtilImdGlobal {

    /**
     * method to show dialog info without any listener
     *
     * @param context
     * @param title
     * @param message
     */
    public static void info(Context context, String title, String message) {
        info(context, title, message, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });
    }

    /**
     * method to show dialog info with custom listener
     *
     * @param context
     * @param title
     * @param message
     * @param listener
     */
    public static void info(Context context, String title, String message, DialogInterface.OnClickListener listener) {
        if (context != null && context instanceof Activity && !((Activity) context).isFinishing()) {
            new AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setIcon(R.mipmap.ic_launcher)
                    .setPositiveButton(context.getString(R.string.text_ok), listener)
                    .setNegativeButton(null, null)
                    .create()
                    .show();
        }
    }

    /**
     * method to show dialog info and cannot be cancelable
     *
     * @param context
     * @param title
     * @param message
     * @param listener
     */
    public static void infoFix(Context context, String title, String message, DialogInterface.OnClickListener listener) {
        if (context != null && context instanceof Activity && !((Activity) context).isFinishing()) {
            new AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setIcon(R.mipmap.ic_launcher)
                    .setPositiveButton(context.getString(R.string.text_ok), listener)
                    .setNegativeButton(null, null)
                    .setCancelable(false)
                    .create()
                    .show();
        }
    }

    /**
     * method to show dialog with custom selection
     *
     * @param context
     * @param title
     * @param message
     * @param positive
     * @param negative
     * @param positiveListener
     * @param negativeListener
     */
    public static void selection(Context context, String title, String message, String positive, String negative, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        if (context != null && context instanceof Activity && !((Activity) context).isFinishing()) {
            new AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setIcon(R.mipmap.ic_launcher)
                    .setPositiveButton(positive, positiveListener)
                    .setNegativeButton(negative, negativeListener)
                    .create()
                    .show();
        }
    }

    /**
     * method to convert dp to px
     * @param dp
     * @return
     */
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * method to convert px to dp
     * @param px
     * @return
     */
    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * method to check network is connected or no
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    /**
     * method to close keyboard
     *
     * @param activity
     * @param view
     */
    public static void closeKeyboard(Activity activity, View view) {
        View focusView = (view == null) ? activity.getCurrentFocus() : view;
        if (focusView != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
        }
    }

    /**
     * method to get screen width
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics;
        metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
}