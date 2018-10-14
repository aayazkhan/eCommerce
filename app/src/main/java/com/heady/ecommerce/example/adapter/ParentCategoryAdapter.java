package com.heady.ecommerce.example.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.heady.ecommerce.example.R;
import com.heady.ecommerce.example.customControl.CustExpListview;
import com.heady.ecommerce.example.model.Category;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayyazkhan on 14/10/18.
 */

public class ParentCategoryAdapter extends BaseExpandableListAdapter {

    @BindView(R.id.textViewCategoryName)
    TextView textView_catName;
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Category> categories;

    public ParentCategoryAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.categories = categories;
    }

    @Override
    public Category getChild(int groupPosition, int childPosition) {
        return categories.get(groupPosition).getCategories().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return categories.get(groupPosition).getCategories().get(childPosition).getId();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        CustExpListview SecondLevelexplv = new CustExpListview(context);
        SecondLevelexplv.setAdapter(new ChildCategoryAdapter(context, categories.get(groupPosition).getCategories()));
        SecondLevelexplv.setGroupIndicator(null);
        SecondLevelexplv.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
        return SecondLevelexplv;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categories.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return categories.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return categories.get(groupPosition).getId();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.category_item_row, parent, false);
        ButterKnife.bind(this, convertView);


        Category current = categories.get(groupPosition);
        textView_catName.setText(current.getName());
        textView_catName.setTypeface(null, Typeface.BOLD);
        textView_catName.setTextSize(18.0f);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}