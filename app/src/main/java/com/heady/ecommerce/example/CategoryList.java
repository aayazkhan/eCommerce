package com.heady.ecommerce.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.heady.ecommerce.example.adapter.ParentCategoryAdapter;
import com.heady.ecommerce.example.model.category.Category;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CategoryList extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.expandableListView_categories)
    ExpandableListView expandableListViewCategories;
    ArrayList<Category> categories;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Category");

        savedInstanceState = getIntent().getExtras();

        if (savedInstanceState != null) {
            categories = (ArrayList<Category>) savedInstanceState.get("catagory");

            ParentCategoryAdapter adapter = new ParentCategoryAdapter(CategoryList.this, categories);

            expandableListViewCategories.setAdapter(adapter);

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return true;
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
