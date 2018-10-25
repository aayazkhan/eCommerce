package com.heady.ecommerce.example.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heady.ecommerce.example.R;
import com.heady.ecommerce.example.db.entity.Variant;
import com.heady.ecommerce.example.eventHandler.VariantOnClickListner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayyazkhan on 14/10/18.
 */

public class VariantAdapter extends RecyclerView.Adapter<VariantAdapter.MyViewHolder> {

    private ArrayList<Variant> variants;
    private ArrayList<Variant> arraylist;

    private VariantOnClickListner clickListner;

    public VariantAdapter(ArrayList<Variant> variants, VariantOnClickListner clickListner) {
        this.variants = variants;
        this.arraylist = new ArrayList<Variant>();
        this.arraylist.addAll(variants);

        this.clickListner = clickListner;
    }

    @Override
    public VariantAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.variant_item_row, parent, false);

        final VariantAdapter.MyViewHolder myViewHolder = new VariantAdapter.MyViewHolder(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListner.onItemClick(view, myViewHolder.getAdapterPosition(), variants.get(myViewHolder.getAdapterPosition()));
            }
        });


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Variant variant = variants.get(position);

        String value = "";
        if (variant.getSize() != null) {
            value = variant.getColor() + " - " + variant.getSize();
        } else {
            value = variant.getColor();
        }

        holder.textValue.setText(value);

    }

    @Override
    public int getItemCount() {
        return variants.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textValue)
        TextView textValue;

        public MyViewHolder(View view) {
            super(view);

            // binding view
            ButterKnife.bind(this, view);
        }
    }


}
