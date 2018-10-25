package com.heady.ecommerce.example.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.heady.ecommerce.example.db.dao.CategoryDao;
import com.heady.ecommerce.example.db.dao.ProductDao;
import com.heady.ecommerce.example.db.dao.RankingDao;
import com.heady.ecommerce.example.db.dao.TaxDao;
import com.heady.ecommerce.example.db.dao.VariantDao;
import com.heady.ecommerce.example.db.entity.Ranking;
import com.heady.ecommerce.example.db.entity.Tax;
import com.heady.ecommerce.example.db.entity.Variant;
import com.heady.ecommerce.example.db.entity.category.Category;
import com.heady.ecommerce.example.db.entity.product.Product;

@Database(entities = {Category.class, Ranking.class, Product.class, Tax.class, Variant.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase = null;

    /**
     * from developers android, made my own singleton
     *
     * @param context
     * @return
     */
    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "eCommmerce.db").allowMainThreadQueries().build();
        }
        return appDatabase;
    }

    public abstract CategoryDao categoryDao();

    public abstract RankingDao rankingDao();

    public abstract ProductDao productDao();

    public abstract TaxDao taxDao();

    public abstract VariantDao variantDao();

}