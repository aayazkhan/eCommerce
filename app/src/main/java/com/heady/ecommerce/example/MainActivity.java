package com.heady.ecommerce.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.heady.ecommerce.example.adapter.ProductAdapter;
import com.heady.ecommerce.example.eventHandler.ProductOnClickListner;
import com.heady.ecommerce.example.model.Category;
import com.heady.ecommerce.example.model.Product;
import com.heady.ecommerce.example.model.Ranking;
import com.heady.ecommerce.example.model.product.SortProductbyOrderCount;
import com.heady.ecommerce.example.model.product.SortProductbyShareCount;
import com.heady.ecommerce.example.model.product.SortProductbyViewCount;
import com.heady.ecommerce.example.network.NetworkOperations;
import com.heady.ecommerce.example.network.WebServiceCalls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    public static String service = "https://stark-spire-93433.herokuapp.com/";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textSearch)
    EditText editsearch;
    @BindViews({R.id.textView1, R.id.textView2, R.id.textView3})
    TextView[] textViews;
    @BindViews({R.id.list1, R.id.list2, R.id.list3})
    RecyclerView[] lists;
    private Unbinder unbinder;
    private ArrayList<Product> products[];
    private ProductAdapter[] adapters;

    private ArrayList<Category> categories;
    private ArrayList<Ranking> rankings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));

        for (RecyclerView view : lists) {
            view.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
            view.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.HORIZONTAL));
        }

        WebServiceCalls.Data data = new WebServiceCalls(service).new Data();

        data.getDetails(MainActivity.this, new NetworkOperations(true) {
            @Override
            public void onSuccess(Bundle msg) {
                System.out.println(msg);
                try {

                    categories = (ArrayList<Category>) msg.getSerializable("catagory");
                    rankings = (ArrayList<Ranking>) msg.getSerializable("ranking");

                    getProductWithRanking();

                    products = new ArrayList[rankings.size()];

                    adapters = new ProductAdapter[rankings.size()];

                    for (int i = 0; i < lists.length; i++) {

                        textViews[i].setText(rankings.get(i).getRanking().toUpperCase(Locale.getDefault()));
                        products[i] = rankings.get(i).getProducts();

                        if (i == 0) {
                            Collections.sort(products[i], new SortProductbyViewCount("DESC"));
                        } else if (i == 1) {
                            Collections.sort(products[i], new SortProductbyOrderCount("DESC"));
                        } else if (i == 2) {
                            Collections.sort(products[i], new SortProductbyShareCount("DESC"));
                        }

                        adapters[i] = new ProductAdapter(products[i], new ProductOnClickListner() {

                            @Override
                            public void onItemClick(View v, int position, Product product) {
                                System.out.println(position);
                                Intent intent = new Intent(MainActivity.this, ProductDetail.class);
                                intent.putExtra("product", product);
                                startActivity(intent);
                                System.out.println("==============================");
                            }
                        });

                        lists[i].setAdapter(adapters[i]);

                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

            @Override
            public void onFailure(Bundle msg) {

            }
        });

    }

    private void getProductWithRanking() {

        for (Ranking ranking : rankings) {
            for (Product rProduct : ranking.getProducts()) {

                for (Category category : categories) {
                    for (Product cProduct : category.getProducts()) {
                        if (rProduct.getId() == cProduct.getId()) {
                            rProduct.setName(cProduct.getName());
                            rProduct.setDateAdded(cProduct.getDateAdded());
                            rProduct.setVariants(cProduct.getVariants());
                            rProduct.setTax(cProduct.getTax());

                            cProduct.setViewCount(rProduct.getViewCount());
                            cProduct.setOrderCount(rProduct.getOrderCount());
                            cProduct.setShareCount(rProduct.getShareCount());
                        }
                    }
                }
            }
        }
    }

    @OnClick(R.id.textViewCategory)
    public void onTextViewCategoryClick() {
        Intent intent = new Intent(MainActivity.this, CategoryList.class);
        intent.putExtra("catagory", categories);
        startActivity(intent);
    }

    @OnTextChanged(R.id.textSearch)
    public void ontextSearch() {
        String text = editsearch.getText().toString().toLowerCase(Locale.ENGLISH);
        for (ProductAdapter adapter : adapters) {
            adapter.filter(text);
        }
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
