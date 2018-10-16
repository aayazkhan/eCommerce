package com.heady.ecommerce.example.model.product.Comparator;

import com.heady.ecommerce.example.model.product.Product;

import java.util.Comparator;

/**
 * Created by ayyazkhan on 13/10/18.
 */

public class SortProductbyName implements Comparator<Product> {
    private String sortBy = "ASC";

    public SortProductbyName(String sortBy) {
        this.sortBy = sortBy;
    }

    public int compare(Product a, Product b) {
        if (sortBy.equals("ASC")) {
            return a.getName().compareTo(b.getName());
        } else {
            return b.getName().compareTo(a.getName());
        }
    }
}
