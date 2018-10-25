package com.heady.ecommerce.example.presenter.productlist;

import com.heady.ecommerce.example.db.entity.product.Product;
import com.heady.ecommerce.example.view.IProductListView;

import java.util.ArrayList;

/**
 * Created by ayyazkhan on 18/10/18.
 */

public class ProductListlPresenter implements IProductListlPresenter {

    private IProductListView productListView;
    private ArrayList<Product> product;

    public ProductListlPresenter(IProductListView productListView, ArrayList<Product> product) {
        this.productListView = productListView;
        this.product = product;
    }


    @Override
    public void showProductsDetails() {
        productListView.showProductsDetails(product);
    }
}
