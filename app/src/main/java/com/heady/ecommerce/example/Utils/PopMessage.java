package com.heady.ecommerce.example.Utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by ayyaz on 01/06/2018.
 */
public class PopMessage {

    public static void makeshorttoast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void makelongtoast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void makesimplesnack(ViewGroup layout, String message) {
        Snackbar snackbar = Snackbar.make(layout, message, Snackbar.LENGTH_SHORT);

        snackbar.show();
    }

}
