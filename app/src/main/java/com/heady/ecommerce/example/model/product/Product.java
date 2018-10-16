
package com.heady.ecommerce.example.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.heady.ecommerce.example.model.Tax;
import com.heady.ecommerce.example.model.Variant;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {

    @SerializedName("date_added")
    private String dateAdded;
    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private Tax tax;
    @Expose
    private ArrayList<Variant> variants;
    @SerializedName("view_count")
    private Long viewCount = 0l;
    @SerializedName("order_count")
    private Long orderCount = 0l;
    @SerializedName("shares")
    private Long shareCount = 0l;

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
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

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public ArrayList<Variant> getVariants() {
        return variants;
    }

    public void setVariants(ArrayList<Variant> variants) {
        this.variants = variants;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public Long getShareCount() {
        return shareCount;
    }

    public void setShareCount(Long shareCount) {
        this.shareCount = shareCount;
    }
}
