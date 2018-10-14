
package com.heady.ecommerce.example.network.Retrofit.ResponseModels;

import com.google.gson.annotations.Expose;
import com.heady.ecommerce.example.model.Category;
import com.heady.ecommerce.example.model.Ranking;

import java.io.Serializable;
import java.util.ArrayList;

public class RmData implements Serializable {

    @Expose
    private ArrayList<Category> categories;
    @Expose
    private ArrayList<Ranking> rankings;

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public ArrayList<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(ArrayList<Ranking> rankings) {
        this.rankings = rankings;
    }

}
