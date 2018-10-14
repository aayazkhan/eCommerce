package com.heady.ecommerce.example.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heady.ecommerce.example.R;
import com.heady.ecommerce.example.eventHandler.ProductOnClickListner;
import com.heady.ecommerce.example.model.Product;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private ArrayList<Product> products;
    private ArrayList<Product> arraylist;

    private ProductOnClickListner clickListner;

    public ProductAdapter(ArrayList<Product> products, ProductOnClickListner clickListner) {
        this.products = products;
        this.arraylist = new ArrayList<Product>();
        this.arraylist.addAll(products);

        this.clickListner = clickListner;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row, parent, false);

        final MyViewHolder myViewHolder = new MyViewHolder(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListner.onItemClick(view, myViewHolder.getAdapterPosition(), products.get(myViewHolder.getAdapterPosition()));
            }
        });


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Product product = products.get(position);

        holder.textProductName.setText(product.getName());

        holder.textPrice.setText("Rs. " + product.getVariants().get(0).getPrice());

        holder.textColor.setText(product.getVariants().get(0).getColor());

        if (product.getVariants().get(0).getSize() != null) {
            holder.textSize.setText("Size " + product.getVariants().get(0).getSize());
        } else {
            holder.textSize.setVisibility(View.GONE);
        }


        if (product.getViewCount() > product.getOrderCount()) {
            if (product.getViewCount() > product.getShareCount()) {
                holder.textCount.setText(product.getViewCount() + " views");
            } else {
                holder.textCount.setText(product.getShareCount() + " shares");
            }
        } else if (product.getOrderCount() > product.getShareCount()) {
            holder.textCount.setText(product.getOrderCount() + " ordered");
        } else {
            holder.textCount.setText(product.getShareCount() + " shares");
        }

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void filter(String charText) {
        charText = charText.toUpperCase(Locale.ENGLISH);
        products.clear();
        if (charText.length() == 0) {
            products.addAll(arraylist);
        } else {
            for (Product wp : arraylist) {
                if (wp.getName().toUpperCase(Locale.ENGLISH).contains(charText)) {
                    products.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textProductName)
        TextView textProductName;

        @BindView(R.id.textPrice)
        TextView textPrice;

        @BindView(R.id.textSize)
        TextView textSize;

        @BindView(R.id.textColor)
        TextView textColor;

        @BindView(R.id.textCount)
        TextView textCount;


        public MyViewHolder(View view) {
            super(view);

            // binding view
            ButterKnife.bind(this, view);
        }
    }

}
