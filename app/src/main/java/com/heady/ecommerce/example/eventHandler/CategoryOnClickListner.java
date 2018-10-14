package com.heady.ecommerce.example.eventHandler;

import android.view.View;

import com.heady.ecommerce.example.model.Category;

/**
 * Created by Ronil on 07-09-2017.
 */

public interface CategoryOnClickListner {
    void onItemClick(View v, int position, Category category);
}
