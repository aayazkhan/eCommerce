package com.heady.ecommerce.example.db.entity.product.Comparator;

import com.heady.ecommerce.example.db.entity.product.Product;

import java.util.Comparator;

/**
 * Created by ayyazkhan on 13/10/18.
 */

public class SortProductbyViewCount implements Comparator<Product> {
    private String sortBy = "ASC";

    public SortProductbyViewCount(String sortBy) {
        this.sortBy = sortBy;
    }

    public int compare(Product a, Product b) {
        if (sortBy.equals("ASC")) {
            return a.getViewCount().compareTo(b.getViewCount());
        } else {
            return b.getViewCount().compareTo(a.getViewCount());
        }
    }
}
