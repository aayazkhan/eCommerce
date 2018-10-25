
package com.heady.ecommerce.example.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.heady.ecommerce.example.db.entity.product.Product;

import java.io.Serializable;

@Entity(foreignKeys = @ForeignKey(entity = Product.class,
        parentColumns = "id",
        childColumns = "product_id"),
        indices = {@Index("product_id")})
public class Variant implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long vid;

    @ColumnInfo
    @Expose
    private Long id;

    @ColumnInfo
    @Expose
    private String color;

    @ColumnInfo
    @Expose
    private Long price;

    @ColumnInfo
    @Expose
    private Long size;

    @ColumnInfo
    private Long product_id;

    public long getVid() {
        return vid;
    }

    public void setVid(long vid) {
        this.vid = vid;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }
}
