package com.heady.ecommerce.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.heady.ecommerce.example.ProductList;
import com.heady.ecommerce.example.R;
import com.heady.ecommerce.example.model.Category;

import java.util.ArrayList;

/**
 * Created by ayyazkhan on 14/10/18.
 */

public class ChildCategoryAdapter extends BaseExpandableListAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Category> categories;

    public ChildCategoryAdapter(Context context, ArrayList<Category> categories) {
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
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.category_item_row, parent, false);
        TextView textView_catName = convertView.findViewById(R.id.textViewCategoryName);
        Category current = categories.get(groupPosition).getCategories().get(childPosition);
        textView_catName.setText("      " + current.getName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductList.class);
                intent.putExtra("title", categories.get(groupPosition).getCategories().get(childPosition).getName());
                intent.putExtra("products", categories.get(groupPosition).getCategories().get(childPosition).getProducts());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return categories.get(groupPosition).getCategories().size();
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
        TextView textView_catName = convertView.findViewById(R.id.textViewCategoryName);
        Category current = categories.get(groupPosition);
        textView_catName.setText("  " + current.getName());
        textView_catName.setTypeface(null, Typeface.BOLD);
        textView_catName.setTextSize(16.0f);

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