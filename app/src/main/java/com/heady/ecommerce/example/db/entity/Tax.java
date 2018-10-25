
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
public class Tax implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo
    @Expose
    private String name;

    @ColumnInfo
    @Expose
    private Double value;

    @ColumnInfo
    private Long product_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }
}
