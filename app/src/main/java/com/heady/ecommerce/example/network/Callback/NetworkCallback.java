package com.heady.ecommerce.example.network.Callback;

import android.os.Bundle;

public interface NetworkCallback {
    //  public void onSuccess(String msg);
//  public void onFailure(String msg);
    void onSuccess(Bundle msg);

    void onFailure(Bundle msg);
}
