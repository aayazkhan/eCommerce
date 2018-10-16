package com.heady.ecommerce.example.model.product.Comparator;

import com.heady.ecommerce.example.model.product.Product;

import java.util.Comparator;

/**
 * Created by ayyazkhan on 13/10/18.
 */

public class SortProductbyOrderCount implements Comparator<Product> {
    private String sortBy = "ASC";

    public SortProductbyOrderCount(String sortBy) {
        this.sortBy = sortBy;
    }

    public int compare(Product a, Product b) {
        if (sortBy.equals("ASC")) {
            return a.getOrderCount().compareTo(b.getOrderCount());
        } else {
            return b.getOrderCount().compareTo(a.getOrderCount());
        }
    }
}
