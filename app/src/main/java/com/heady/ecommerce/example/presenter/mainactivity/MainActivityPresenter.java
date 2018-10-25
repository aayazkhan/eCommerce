package com.heady.ecommerce.example.presenter.mainactivity;

import com.heady.ecommerce.example.db.entity.Ranking;
import com.heady.ecommerce.example.db.entity.category.Category;
import com.heady.ecommerce.example.db.entity.category.Comparator.SortCategorybyChild;
import com.heady.ecommerce.example.db.entity.product.Product;
import com.heady.ecommerce.example.view.IMainActivityView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ayyazkhan on 18/10/18.
 */

public class MainActivityPresenter implements IMainActivityPresenter {

    private IMainActivityView mainActivityView;
    private ArrayList<Category> categories;
    private ArrayList<Ranking> rankings;

    public MainActivityPresenter(IMainActivityView mainActivityView, ArrayList<Category> categories, ArrayList<Ranking> rankings) {
        this.mainActivityView = mainActivityView;
        this.categories = categories;
        this.rankings = rankings;

        ArrayList<Category> hierarchyCategories = getHierarchyCategory();
        mainActivityView.setHierarchyCategories(hierarchyCategories);

        ArrayList<Category> childCategories = getChildCategory();
        mainActivityView.showChildCategoriesDetails(childCategories);

        this.rankings = getProductWithRanking(childCategories);

        mainActivityView.showRankingDetails(this.rankings);

    }

    private ArrayList<Category> getHierarchyCategory() {

        ArrayList<Category> hierarchyCategories = new ArrayList<Category>();
        hierarchyCategories.addAll(categories);
        Collections.sort(hierarchyCategories, new SortCategorybyChild("ASC"));

        try {

            for (int i = 0; i < hierarchyCategories.size(); i++) {
                boolean flag = false;

                for (int j = 0; j < hierarchyCategories.size(); j++) {

                    for (int k = 0; k < hierarchyCategories.get(j).getChildCategories().size(); k++) {
                        Long iID = hierarchyCategories.get(i).getId();
                        Long kID = hierarchyCategories.get(j).getChildCategories().get(k);

                        if (String.valueOf(iID).equals(String.valueOf(kID))) {
                            flag = true;
                            if (hierarchyCategories.get(j).getCategories() == null) {
                                ArrayList<Category> tmp = new ArrayList<Category>();
                                tmp.add(hierarchyCategories.get(i));
                                hierarchyCategories.get(j).setCategories(tmp);

                            } else {
                                hierarchyCategories.get(j).getCategories().add(hierarchyCategories.get(i));
                            }

                        }

                    }

                }

                if (flag) {
                    hierarchyCategories.remove(i--);
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return hierarchyCategories;

    }

    private ArrayList<Category> getChildCategory() {

        ArrayList<Category> childCategories = new ArrayList<Category>();
        childCategories.addAll(categories);

        try {
            for (int i = 0; i < childCategories.size(); i++) {

                if (childCategories.get(i).getChildCategories().size() != 0) {
                    childCategories.remove(i--);
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return childCategories;

    }

    private ArrayList<Ranking> getProductWithRanking(ArrayList<Category> childCategories) {

        for (Ranking ranking : rankings) {
            for (Product rProduct : ranking.getProducts()) {

                for (Category category : childCategories) {

                    for (Product cProduct : category.getProducts()) {

                        if (rProduct.getId() == cProduct.getId()) {

                            rProduct.setName(cProduct.getName());
                            rProduct.setDateAdded(cProduct.getDateAdded());
                            rProduct.setVariants(cProduct.getVariants());
                            rProduct.setTax(cProduct.getTax());

                            cProduct.setViewCount(rProduct.getViewCount());
                            cProduct.setOrderCount(rProduct.getOrderCount());
                            cProduct.setShareCount(rProduct.getShareCount());
                        }
                    }
                }
            }
        }

        return rankings;
    }


}
