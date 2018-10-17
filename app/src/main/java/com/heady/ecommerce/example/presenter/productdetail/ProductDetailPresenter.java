package com.heady.ecommerce.example.presenter.productdetail;

import com.heady.ecommerce.example.model.product.Product;
import com.heady.ecommerce.example.view.IProductDetailView;

/**
 * Created by ayyazkhan on 18/10/18.
 */

public class ProductDetailPresenter implements IProductDetailPresenter {

    private IProductDetailView productDetailView;
    private Product product;

    public ProductDetailPresenter(IProductDetailView productDetailView, Product product) {
        this.productDetailView = productDetailView;
        this.product = product;
    }


    @Override
    public void showProductDetail() {
        productDetailView.showProductDetails(product);
    }
}
