package com.heady.ecommerce.example.Utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.heady.ecommerce.example.network.Retrofit.RFClient;
import com.heady.ecommerce.example.network.Retrofit.RFInterface;

/**
 * Created by Brajendr on 2/8/2017.
 */

public class Utility {

    public static View initRecyclerView(ViewGroup parent, int id) {
        return LayoutInflater.from(parent.getContext()).inflate(id, parent, false);
    }

    public static RFInterface getRetrofitInterface(String BASE_URL) {
        return RFClient.getClient(BASE_URL).create(RFInterface.class);
    }

    public static Dialog createDialog(Context context, int layout) {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layout);
        dialog.show();
        return dialog;
    }

    public static Dialog createDialog(Context context, int layout, boolean isFullscreen) {
        Dialog dialog;
        if (isFullscreen) {
            dialog = new Dialog(context, android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        } else {
            dialog = new Dialog(context);
        }

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layout);
        dialog.show();
        return dialog;
    }

}
