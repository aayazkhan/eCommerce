package com.heady.ecommerce.example.service;

/**
 * Created by dinakar.maurya on 25-01-2018.
 */

import com.heady.ecommerce.example.db.entity.Ranking;
import com.heady.ecommerce.example.db.entity.category.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * writing my own service interface
 */
public interface IDatabaseService {

    List<Category> getCategoryAll();

    void insertCategoryAll(ArrayList<Category> categories);

    List<Ranking> getRankingAll();

    void insertRankingAll(ArrayList<Ranking> rankings);

}
