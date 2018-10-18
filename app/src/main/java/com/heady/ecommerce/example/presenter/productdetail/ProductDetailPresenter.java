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

        productDetailView.showProductName(product.getName());

        productDetailView.showPrice(product.getVariants().get(0).getPrice());

        productDetailView.showColor(product.getVariants().get(0).getColor());

        productDetailView.showSize(product.getVariants().get(0).getSize());

        productDetailView.showCount(product.getViewCount(), product.getOrderCount(), product.getShareCount());

        if (product.getVariants().get(0).getSize() != null) {
            productDetailView.showColorSizeTitle("Color / Size :");
        } else {
            productDetailView.showColorSizeTitle("Color :");
        }

        productDetailView.showVariant(product.getVariants());

        productDetailView.showTax(product.getTax());

    }

}
