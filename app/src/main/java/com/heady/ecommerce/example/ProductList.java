package com.heady.ecommerce.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.heady.ecommerce.example.adapter.ProductAdapter;
import com.heady.ecommerce.example.eventHandler.ProductOnClickListner;
import com.heady.ecommerce.example.model.Product;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProductList extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.list)
    RecyclerView list;
    private Unbinder unbinder;
    private String title;
    private ArrayList<Product> products;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list.setLayoutManager(new LinearLayoutManager(this));

        savedInstanceState = getIntent().getExtras();

        if (savedInstanceState != null) {
            title = savedInstanceState.getString("title");
            products = (ArrayList<Product>) savedInstanceState.getSerializable("products");
            setTitle(title);

            adapter = new ProductAdapter(products, new ProductOnClickListner() {

                @Override
                public void onItemClick(View v, int position, Product product) {
                    System.out.println(position);

                    Intent intent = new Intent(ProductList.this, ProductDetail.class);
                    intent.putExtra("product", product);
                    startActivity(intent);

                    System.out.println("==============================");
                }
            });

            list.setAdapter(adapter);

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
