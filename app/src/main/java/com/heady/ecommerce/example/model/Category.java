
package com.heady.ecommerce.example.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {

    @SerializedName("child_categories")
    private ArrayList<Long> childCategories;
    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private ArrayList<Product> products;

    private ArrayList<Category> categories;

    private boolean visible = false;

    public ArrayList<Long> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(ArrayList<Long> childCategories) {
        this.childCategories = childCategories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
