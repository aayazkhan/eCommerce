package com.heady.ecommerce.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.heady.ecommerce.example.adapter.ParentCategoryAdapter;
import com.heady.ecommerce.example.model.Category;
import com.heady.ecommerce.example.model.category.SortCategorybyChild;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CategoryList extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.expandableListView_categories)
    ExpandableListView expandableListViewCategories;
    ArrayList<Category> categories, list;
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
            list = new ArrayList<Category>();
            list.addAll(categories);

            Collections.sort(list, new SortCategorybyChild("ASC"));

            ArrayList<Category> tmpList = getCategory(list);

//            CategoryAdapter adapter = new CategoryAdapter(CategoryList.this, tmpList);
            ParentCategoryAdapter adapter = new ParentCategoryAdapter(CategoryList.this, tmpList);

            expandableListViewCategories.setAdapter(adapter);

        }

    }

    private ArrayList<Category> getCategory(ArrayList<Category> tmpCategories) {

        try {
            for (int i = 0; i < tmpCategories.size(); i++) {
                boolean flag = false;

                for (int j = 0; j < tmpCategories.size(); j++) {

                    for (int k = 0; k < tmpCategories.get(j).getChildCategories().size(); k++) {
                        Long iID = tmpCategories.get(i).getId();
                        Long kID = tmpCategories.get(j).getChildCategories().get(k);

                        if (String.valueOf(iID).equals(String.valueOf(kID))) {
                            flag = true;
                            if (tmpCategories.get(j).getCategories() == null) {
                                ArrayList<Category> tmp = new ArrayList<Category>();
                                tmp.add(tmpCategories.get(i));
                                tmpCategories.get(j).setCategories(tmp);

                            } else {
                                tmpCategories.get(j).getCategories().add(tmpCategories.get(i));
                            }

                        }

                    }

                }

                if (flag) {
                    tmpCategories.remove(i--);
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return tmpCategories;

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
