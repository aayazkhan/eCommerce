package com.heady.ecommerce.example.view;

import com.heady.ecommerce.example.model.Tax;
import com.heady.ecommerce.example.model.Variant;

import java.util.ArrayList;

/**
 * Created by ayyazkhan on 18/10/18.
 */

public interface IProductDetailView {

    void showProductName(String productName);

    void showPrice(Long price);

    void showColor(String color);

    void showSize(Long size);

    void showCount(Long viewCount, Long orderedCount, Long shareCount);

    void showColorSizeTitle(String colorSizeTitle);

    void showVariant(ArrayList<Variant> variants);

    void showTax(Tax tax);

}
