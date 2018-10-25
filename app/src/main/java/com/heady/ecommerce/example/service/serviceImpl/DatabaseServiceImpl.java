package com.heady.ecommerce.example.service.serviceImpl;

import android.content.Context;

import com.heady.ecommerce.example.db.AppDatabase;
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
import com.heady.ecommerce.example.service.IDatabaseService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dinakar.maurya on 25-01-2018.
 */

/**
 * writing my own impls
 */
public class DatabaseServiceImpl implements IDatabaseService {

    private CategoryDao categoryDao;
    private RankingDao rankingDao;
    private ProductDao productDao;
    private TaxDao taxDao;
    private VariantDao variantDao;

    public DatabaseServiceImpl(Context context) {
        categoryDao = AppDatabase.getInstance(context).categoryDao();
        rankingDao = AppDatabase.getInstance(context).rankingDao();
        productDao = AppDatabase.getInstance(context).productDao();
        taxDao = AppDatabase.getInstance(context).taxDao();
        variantDao = AppDatabase.getInstance(context).variantDao();
    }

    @Override
    public List<Category> getCategoryAll() {
        List<Category> categories = categoryDao.loadAll();

        for (Category category : categories) {

            List<Product> products = productDao.loadAllByCategoryID(category.getId());

            for (Product product : products) {
                product.setTax(taxDao.loadByProductID(product.getId()));

                List<Variant> variants = variantDao.loadAllByProductID(product.getId());
                ArrayList<Variant> list = new ArrayList<Variant>();
                list.addAll(variants);
                product.setVariants(list);
            }

            ArrayList<Product> list = new ArrayList<Product>();
            list.addAll(products);
            category.setProducts(list);
        }


        return categories;
    }

    @Override
    public void insertCategoryAll(ArrayList<Category> categories) {

        taxDao.deleteAll();
        variantDao.deleteAll();
        productDao.deleteAll();
        categoryDao.deleteAll();

        for (Category category : categories) {
            categoryDao.insert(category);

            if (!category.getProducts().isEmpty()) {

                for (Product product : category.getProducts()) {

                    product.setCategory_id(category.getId());
                    productDao.insert(product);

                    if (product.getTax() != null) {
                        Tax tax = product.getTax();
                        tax.setProduct_id(product.getId());
                        taxDao.insert(tax);
                    }

                    if (!product.getVariants().isEmpty()) {
                        for (Variant variant : product.getVariants()) {
                            variant.setProduct_id(product.getId());
                            variantDao.insert(variant);
                        }
                    }

                }

            }

        }

    }

    @Override
    public List<Ranking> getRankingAll() {
        return rankingDao.loadAll();
    }

    @Override
    public void insertRankingAll(ArrayList<Ranking> rankings) {

        rankingDao.deleteAll();

        for (Ranking ranking : rankings) {
            rankingDao.insert(ranking);
        }
    }
}
