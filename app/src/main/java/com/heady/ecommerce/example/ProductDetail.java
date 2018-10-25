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
import com.heady.ecommerce.example.db.entity.Tax;
import com.heady.ecommerce.example.db.entity.Variant;
import com.heady.ecommerce.example.db.entity.product.Product;
import com.heady.ecommerce.example.eventHandler.VariantOnClickListner;
import com.heady.ecommerce.example.presenter.productdetail.ProductDetailPresenter;
import com.heady.ecommerce.example.view.IProductDetailView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProductDetail extends AppCompatActivity implements IProductDetailView {

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
    @BindView(R.id.textColorSizeTitle)
    TextView textColorSizeTitle;
    @BindView(R.id.vList)
    RecyclerView vList;
    @BindView(R.id.textTex)
    TextView textTex;
    private Unbinder unbinder;
    private VariantAdapter vAdapter;

    private ProductDetailPresenter productDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        savedInstanceState = getIntent().getExtras();

        if (savedInstanceState != null) {
            Product product = (Product) savedInstanceState.getSerializable("product");
            setTitle(product.getName());

            productDetailPresenter = new ProductDetailPresenter(this, product);


        }
    }

    @Override
    public void showProductName(String productName) {
        textProductName.setText(productName);
    }

    @Override
    public void showPrice(Long price) {
        textPrice.setText("Rs. " + price);
    }

    @Override
    public void showColor(String color) {
        textColor.setText(color);
    }

    @Override
    public void showSize(Long size) {
        if (size != null) {
            textSize.setText("Size " + size);
        } else {
            textSize.setVisibility(View.GONE);
        }
    }


    @Override
    public void showCount(Long viewCount, Long orderedCount, Long shareCount) {
        if (viewCount > orderedCount) {
            if (viewCount > shareCount) {
                textCount.setText(viewCount + " views");
            } else {
                textCount.setText(shareCount + " shares");
            }
        } else if (orderedCount > shareCount) {
            textCount.setText(orderedCount + " ordered");
        } else {
            textCount.setText(shareCount + " shares");
        }
    }

    @Override
    public void showColorSizeTitle(String colorSizeTitle) {
        textColorSizeTitle.setText(colorSizeTitle);
    }


    @Override
    public void showVariant(ArrayList<Variant> variants) {
        vList.setLayoutManager(new LinearLayoutManager(ProductDetail.this, LinearLayoutManager.HORIZONTAL, false));
        vList.addItemDecoration(new DividerItemDecoration(ProductDetail.this, LinearLayoutManager.HORIZONTAL));

        vAdapter = new VariantAdapter(variants, new VariantOnClickListner() {
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

    @Override
    public void showTax(Tax tax) {
        textTex.setText(tax.getName() + " : " + tax.getValue() + " %");
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
