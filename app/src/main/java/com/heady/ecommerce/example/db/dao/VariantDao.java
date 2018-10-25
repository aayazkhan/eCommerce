package com.heady.ecommerce.example.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.heady.ecommerce.example.db.entity.Variant;

import java.util.List;

/**
 * Created by ayyazkhan on 25/10/18.
 */

@Dao
public interface VariantDao {

    @Insert
    void insert(Variant... variants);

    @Update
    void update(Variant... variants);

    @Delete
    void delete(Variant... variants);

    @Query("Delete from Variant")
    void deleteAll();

    @Query("Select * FROM Variant")
    List<Variant> loadAll();

    @Query("Select * FROM Variant where product_id = :productID")
    List<Variant> loadAllByProductID(Long productID);

}
