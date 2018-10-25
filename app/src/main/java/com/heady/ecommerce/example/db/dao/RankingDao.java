package com.heady.ecommerce.example.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.heady.ecommerce.example.db.entity.Ranking;

import java.util.List;

/**
 * Created by ayyazkhan on 25/10/18.
 */

@Dao
public interface RankingDao {

    @Insert
    void insert(Ranking... rankings);

    @Update
    void update(Ranking... rankings);

    @Delete
    void delete(Ranking... rankings);

    @Query("Delete from Ranking")
    void deleteAll();

    @Query("Select * FROM Ranking")
    List<Ranking> loadAll();

}
