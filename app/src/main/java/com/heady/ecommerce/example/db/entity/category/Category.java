
package com.heady.ecommerce.example.db.entity.category;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.heady.ecommerce.example.db.entity.product.Product;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

@Entity
public class Category implements Serializable {

    @PrimaryKey
    @Expose
    private Long id;

    @ColumnInfo
    @Expose
    private String name;

    @ColumnInfo
    @TypeConverters(ChildCategoriesTypeConverter.class)
    @SerializedName("child_categories")
    private ArrayList<Long> childCategories;

    @Ignore
    @Expose
    private ArrayList<Product> products;

    @Ignore
    private ArrayList<Category> categories;

    @Ignore
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

    public static class ChildCategoriesTypeConverter implements Serializable {

        @TypeConverter
        public String fromProductsList(ArrayList<Long> childCategories) {

            if (childCategories == null)
                return null;

            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Long>>() {
            }.getType();
            String json = gson.toJson(childCategories, type);
            return json;
        }

        @TypeConverter // note this annotation
        public ArrayList<Long> toProductsList(String value) {
            if (value == null) {
                return (null);
            }
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Long>>() {
            }.getType();
            ArrayList<Long> productCategoriesList = gson.fromJson(value, type);
            return productCategoriesList;
        }

    }

}
