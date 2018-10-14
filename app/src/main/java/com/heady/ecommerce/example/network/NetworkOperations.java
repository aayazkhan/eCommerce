package com.heady.ecommerce.example.network;

import android.app.ProgressDialog;
import android.content.Context;

import com.heady.ecommerce.example.network.Callback.NetworkCallback;


public abstract class NetworkOperations implements NetworkCallback {
    private ProgressDialog progressDialog;
    private boolean isProgrssDialogVisible;

    protected NetworkOperations(boolean isProgrssDialogVisible) {
        this.isProgrssDialogVisible = isProgrssDialogVisible;
    }

    protected void onStart(Context context, String msg) {
        if (isProgrssDialogVisible) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(msg);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    public void onComplete() {
        if (isProgrssDialogVisible) {
            progressDialog.dismiss();
        }
    }
}
