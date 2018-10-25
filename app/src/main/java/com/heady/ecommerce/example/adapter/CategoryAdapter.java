package com.heady.ecommerce.example.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heady.ecommerce.example.R;
import com.heady.ecommerce.example.db.entity.category.Category;
import com.heady.ecommerce.example.eventHandler.CategoryOnClickListner;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayyazkhan on 15/10/18.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private ArrayList<Category> categories;
    private ArrayList<Category> arraylist;

    private CategoryOnClickListner clickListner;

    public CategoryAdapter(ArrayList<Category> categories, CategoryOnClickListner clickListner) {
        this.categories = categories;
        this.arraylist = new ArrayList<Category>();
        this.arraylist.addAll(this.categories);

        this.clickListner = clickListner;
    }

    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item_row, parent, false);

        final CategoryAdapter.MyViewHolder myViewHolder = new CategoryAdapter.MyViewHolder(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListner.onItemClick(view, myViewHolder.getAdapterPosition(), categories.get(myViewHolder.getAdapterPosition()));
            }
        });


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.MyViewHolder holder, final int position) {
        Category category = categories.get(position);

        holder.textViewCategoryName.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void filter(String charText) {
        charText = charText.toUpperCase(Locale.ENGLISH);
        categories.clear();
        if (charText.length() == 0) {
            categories.addAll(arraylist);
        } else {
            for (Category wp : arraylist) {
                if (wp.getName().toUpperCase(Locale.ENGLISH).contains(charText)) {
                    categories.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewCategoryName)
        TextView textViewCategoryName;


        public MyViewHolder(View view) {
            super(view);

            // binding view
            ButterKnife.bind(this, view);
        }
    }

}
