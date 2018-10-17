package com.heady.ecommerce.example.presenter.mainactivity;

import com.heady.ecommerce.example.model.Ranking;
import com.heady.ecommerce.example.model.category.Category;
import com.heady.ecommerce.example.view.IMainActivityView;

import java.util.ArrayList;

/**
 * Created by ayyazkhan on 18/10/18.
 */

public class MainActivityPresenter implements IMainActivityPresenter {

    private IMainActivityView mainActivityView;
    private ArrayList<Category> childCategories;
    private ArrayList<Ranking> rankings;

    public MainActivityPresenter(IMainActivityView mainActivityView, ArrayList<Category> childCategories, ArrayList<Ranking> rankings) {
        this.mainActivityView = mainActivityView;
        this.childCategories = childCategories;
        this.rankings = rankings;
    }

    @Override
    public void showChildCategoriesDetails() {
        mainActivityView.showChildCategoriesDetails(childCategories);
    }

    @Override
    public void showRankingDetails() {
        mainActivityView.showRankingDetails(rankings);
    }

}
