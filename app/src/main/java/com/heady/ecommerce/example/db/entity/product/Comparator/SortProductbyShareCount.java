package com.heady.ecommerce.example.db.entity.product.Comparator;

import com.heady.ecommerce.example.db.entity.product.Product;

import java.util.Comparator;

/**
 * Created by ayyazkhan on 13/10/18.
 */

public class SortProductbyShareCount implements Comparator<Product> {
    private String sortBy = "ASC";

    public SortProductbyShareCount(String sortBy) {
        this.sortBy = sortBy;
    }

    public int compare(Product a, Product b) {
        if (sortBy.equals("ASC")) {
            return a.getShareCount().compareTo(b.getShareCount());
        } else {
            return b.getShareCount().compareTo(a.getShareCount());
        }
    }
}
