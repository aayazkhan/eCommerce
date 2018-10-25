
package com.heady.ecommerce.example.db.entity.product;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.heady.ecommerce.example.db.entity.Tax;
import com.heady.ecommerce.example.db.entity.Variant;
import com.heady.ecommerce.example.db.entity.category.Category;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(foreignKeys = @ForeignKey(entity = Category.class,
        parentColumns = "id",
        childColumns = "category_id"),
        indices = {@Index("category_id")})
public class Product implements Serializable {

    @PrimaryKey
    @Expose
    private Long id;

    @ColumnInfo
    @Expose
    private String name;

    @Ignore
    @Expose
    private Tax tax;

    @Ignore
    @Expose
    private ArrayList<Variant> variants;

    @ColumnInfo
    @SerializedName("view_count")
    private Long viewCount = 0l;

    @ColumnInfo
    @SerializedName("order_count")
    private Long orderCount = 0l;

    @ColumnInfo
    @SerializedName("shares")
    private Long shareCount = 0l;

    @ColumnInfo
    @SerializedName("date_added")
    private String dateAdded;

    @ColumnInfo
    private Long category_id;

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

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }
}
