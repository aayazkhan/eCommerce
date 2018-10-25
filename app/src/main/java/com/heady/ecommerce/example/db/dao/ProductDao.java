package com.heady.ecommerce.example.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.heady.ecommerce.example.db.entity.product.Product;

import java.util.List;

/**
 * Created by ayyazkhan on 25/10/18.
 */

@Dao
public interface ProductDao {

    @Insert
    void insert(Product... products);

    @Update
    void update(Product... products);

    @Delete
    void delete(Product... products);

    @Query("Delete from Product")
    void deleteAll();

    @Query("Select * FROM Product")
    List<Product> loadAll();

    @Query("Select * FROM Product where category_id = :categoryID")
    List<Product> loadAllByCategoryID(Long categoryID);

}
