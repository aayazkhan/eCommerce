package com.heady.ecommerce.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.heady.ecommerce.example.adapter.VariantAdapter;
import com.heady.ecommerce.example.eventHandler.VariantOnClickListner;
import com.heady.ecommerce.example.model.Product;
import com.heady.ecommerce.example.model.Variant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProductDetail extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
    @BindView(R.id.vList)
    RecyclerView vList;
    private Unbinder unbinder;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        savedInstanceState = getIntent().getExtras();

        if (savedInstanceState != null) {
            product = (Product) savedInstanceState.getSerializable("product");
            setTitle(product.getName());

            textProductName.setText(product.getName());

            textPrice.setText("Rs. " + product.getVariants().get(0).getPrice());

            textColor.setText(product.getVariants().get(0).getColor());

            if (product.getVariants().get(0).getSize() != null) {
                textSize.setText("Size " + product.getVariants().get(0).getSize());
            } else {
                textSize.setVisibility(View.GONE);
            }


            if (product.getViewCount() > product.getOrderCount()) {
                if (product.getViewCount() > product.getShareCount()) {
                    textCount.setText(product.getViewCount() + " views");
                } else {
                    textCount.setText(product.getShareCount() + " shares");
                }
            } else if (product.getOrderCount() > product.getShareCount()) {
                textCount.setText(product.getOrderCount() + " ordered");
            } else {
                textCount.setText(product.getShareCount() + " shares");
            }

            vList.setLayoutManager(new LinearLayoutManager(ProductDetail.this, LinearLayoutManager.HORIZONTAL, false));
            vList.addItemDecoration(new DividerItemDecoration(ProductDetail.this, LinearLayoutManager.HORIZONTAL));

            VariantAdapter vAdapter = new VariantAdapter(product.getVariants(), new VariantOnClickListner() {
                @Override
                public void onItemClick(View v, int position, Variant variant) {

                    textPrice.setText("Rs. " + variant.getPrice());
                    textColor.setText(variant.getColor());

                    if (variant.getSize() != null) {
                        textSize.setText("Size " + variant.getSize());
                    } else {
                        textSize.setVisibility(View.GONE);
                    }

                }
            });

            vList.setAdapter(vAdapter);

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
