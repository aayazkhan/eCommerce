package com.heady.ecommerce.example.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.heady.ecommerce.example.db.entity.category.Category;

import java.util.List;


/**
 * Created by ayyazkhan on 25/10/18.
 */

@Dao
public interface CategoryDao {

    @Insert
    void insert(Category... categories);

    @Update
    void update(Category... categories);

    @Delete
    void delete(Category... categories);

    @Query("Delete from Category")
    void deleteAll();


    @Query("Select * FROM Category")
    List<Category> loadAll();

}
