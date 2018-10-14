package com.heady.ecommerce.example.model.category;

import com.heady.ecommerce.example.model.Category;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by ayyazkhan on 13/10/18.
 */

public class SortCategorybyChild implements Comparator<Category> {
    private String sortBy = "ASC";

    public SortCategorybyChild(String sortBy) {
        this.sortBy = sortBy;
    }

    public int compare(Category a, Category b) {
        if (sortBy.equals("ASC")) {
            if (b.getChildCategories().size() == 0) {
                return 1;
            } else if (a.getChildCategories().size() == 0) {
                return -1;
            } else {
                ArrayList<Long> aList = a.getChildCategories();

                boolean flag = false;
                for (Long aID : aList) {
                    if (b.getId().equals(aID)) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    return 1;
                } else {
                    return -1;
                }

            }

        } else {

            if (a.getChildCategories().size() == 0) {
                return 1;
            } else if (b.getChildCategories().size() == 0) {
                return -1;
            } else {
                ArrayList<Long> bList = b.getChildCategories();

                boolean flag = false;
                for (Long bID : bList) {
                    if (a.getId().equals(bID)) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    return 1;
                } else {
                    return -1;
                }

            }
        }
    }
}
