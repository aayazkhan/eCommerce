package com.heady.ecommerce.example.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.heady.ecommerce.example.db.entity.Tax;

import java.util.List;

/**
 * Created by ayyazkhan on 25/10/18.
 */

@Dao
public interface TaxDao {

    @Insert
    void insert(Tax... taxes);

    @Update
    void update(Tax... taxes);

    @Delete
    void delete(Tax... taxes);

    @Query("Delete from Tax")
    void deleteAll();

    @Query("Select * FROM Tax")
    List<Tax> loadAll();

    @Query("Select * FROM Tax where product_id = :productID")
    Tax loadByProductID(Long productID);

}
