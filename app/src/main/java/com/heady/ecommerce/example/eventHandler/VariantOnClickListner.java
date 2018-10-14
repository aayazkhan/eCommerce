package com.heady.ecommerce.example.eventHandler;

import android.view.View;

import com.heady.ecommerce.example.model.Variant;

/**
 * Created by Ronil on 07-09-2017.
 */

public interface VariantOnClickListner {
    void onItemClick(View v, int position, Variant variant);
}
