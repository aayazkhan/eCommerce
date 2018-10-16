package com.heady.ecommerce.example.eventHandler;

import android.view.View;

import com.heady.ecommerce.example.model.product.Product;

/**
 * Created by Ronil on 07-09-2017.
 */

public interface ProductOnClickListner {
    void onItemClick(View v, int position, Product product);
}
