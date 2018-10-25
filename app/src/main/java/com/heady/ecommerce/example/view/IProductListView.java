package com.heady.ecommerce.example.view;

import com.heady.ecommerce.example.db.entity.product.Product;

import java.util.ArrayList;

/**
 * Created by ayyazkhan on 18/10/18.
 */

public interface IProductListView {

    void showProductsDetails(ArrayList<Product> product);

}
