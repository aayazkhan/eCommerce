
package com.heady.ecommerce.example.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import com.heady.ecommerce.example.db.entity.product.Product;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

@Entity
public class Ranking implements Serializable {

    @PrimaryKey
    @NonNull
    @Expose
    private String ranking;

    @ColumnInfo
    @TypeConverters(ProductTypeConverter.class)
    @Expose
    private ArrayList<Product> products;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public static class ProductTypeConverter implements Serializable {

        @TypeConverter
        public String fromProductsList(ArrayList<Product> products) {

            if (products == null)
                return null;

            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Product>>() {
            }.getType();
            String json = gson.toJson(products, type);
            return json;
        }

        @TypeConverter // note this annotation
        public ArrayList<Product> toProductsList(String value) {
            if (value == null) {
                return (null);
            }
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Product>>() {
            }.getType();
            ArrayList<Product> productCategoriesList = gson.fromJson(value, type);
            return productCategoriesList;
        }

    }

}
