package com.heady.ecommerce.example;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.heady.ecommerce.example.Utils.PopMessage;
import com.heady.ecommerce.example.adapter.CategoryAdapter;
import com.heady.ecommerce.example.adapter.ProductAdapter;
import com.heady.ecommerce.example.db.entity.Ranking;
import com.heady.ecommerce.example.db.entity.category.Category;
import com.heady.ecommerce.example.db.entity.product.Comparator.SortProductbyOrderCount;
import com.heady.ecommerce.example.db.entity.product.Comparator.SortProductbyShareCount;
import com.heady.ecommerce.example.db.entity.product.Comparator.SortProductbyViewCount;
import com.heady.ecommerce.example.db.entity.product.Product;
import com.heady.ecommerce.example.eventHandler.CategoryOnClickListner;
import com.heady.ecommerce.example.eventHandler.ProductOnClickListner;
import com.heady.ecommerce.example.network.NetworkOperations;
import com.heady.ecommerce.example.network.WebServiceCalls;
import com.heady.ecommerce.example.presenter.mainactivity.MainActivityPresenter;
import com.heady.ecommerce.example.service.serviceImpl.DatabaseServiceImpl;
import com.heady.ecommerce.example.view.IMainActivityView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements IMainActivityView {

    public static String service = "https://stark-spire-93433.herokuapp.com/";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textSearch)
    EditText editsearch;
    @BindView(R.id.cList)
    RecyclerView cList;
    @BindViews({R.id.textView1, R.id.textView2, R.id.textView3})
    TextView[] textViews;
    @BindViews({R.id.list1, R.id.list2, R.id.list3})
    RecyclerView[] lists;

    int itemWidthHieght;

    private Unbinder unbinder;
    private CategoryAdapter cAdapter;
    private ArrayList<Product> products[];
    private ProductAdapter[] pAdapters;
    private ArrayList<Category> hierarchyCategories;

    private MainActivityPresenter mainActivityPresenter;

    private DatabaseServiceImpl databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));

        cList.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        cList.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.HORIZONTAL));

        for (RecyclerView list : lists) {
            list.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
            list.setItemAnimator(new DefaultItemAnimator());

        }

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            itemWidthHieght = displaymetrics.widthPixels / 3;
            itemWidthHieght = displaymetrics.widthPixels - itemWidthHieght;
        } else {
            itemWidthHieght = displaymetrics.heightPixels / 3;
            itemWidthHieght = displaymetrics.heightPixels - itemWidthHieght;
        }

        databaseService = new DatabaseServiceImpl(MainActivity.this);

        if (isInternetOn()) {
            downloadData();
        } else {
            PopMessage.makelongtoast(getApplicationContext(), "NO INTERNET CONNECTION");

            List<Category> tmpCategories = databaseService.getCategoryAll();
            List<Ranking> tmpRankings = databaseService.getRankingAll();

            ArrayList<Category> categories = new ArrayList<Category>();
            ArrayList<Ranking> rankings = new ArrayList<Ranking>();

            categories.addAll(tmpCategories);
            rankings.addAll(tmpRankings);

            mainActivityPresenter = new MainActivityPresenter(MainActivity.this, categories, rankings);

        }

    }

    private void downloadData() {
        WebServiceCalls.Data data = new WebServiceCalls(service).new Data();

        data.getDetails(MainActivity.this, new NetworkOperations(true) {
            @Override
            public void onSuccess(Bundle msg) {
                System.out.println(msg);
                try {

                    ArrayList<Category> categories = (ArrayList<Category>) msg.getSerializable("catagory");
                    ArrayList<Ranking> rankings = (ArrayList<Ranking>) msg.getSerializable("ranking");

                    databaseService.insertCategoryAll(categories);
                    databaseService.insertRankingAll(rankings);

                    mainActivityPresenter = new MainActivityPresenter(MainActivity.this, categories, rankings);


                } catch (Exception e) {
                    System.out.println(e);
                }

            }

            @Override
            public void onFailure(Bundle msg) {

            }
        });

    }

    @Override
    public void setHierarchyCategories(ArrayList<Category> hierarchyCategories) {
        this.hierarchyCategories = hierarchyCategories;
    }

    @Override
    public void showChildCategoriesDetails(ArrayList<Category> childCategories) {

        cAdapter = new CategoryAdapter(childCategories, new CategoryOnClickListner() {

            @Override
            public void onItemClick(View v, int position, Category category) {
                System.out.println(position);
                if (category.getProducts().size() > 1) {
                    Intent intent = new Intent(MainActivity.this, ProductList.class);
                    intent.putExtra("title", category.getName());
                    intent.putExtra("products", category.getProducts());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, ProductDetail.class);
                    intent.putExtra("product", category.getProducts().get(0));
                    startActivity(intent);
                }
                System.out.println("==============================");
            }
        });

        cList.setAdapter(cAdapter);

    }

    @Override
    public void showRankingDetails(ArrayList<Ranking> rankings) {
        products = new ArrayList[rankings.size()];
        pAdapters = new ProductAdapter[rankings.size()];

        for (int i = 0; i < lists.length; i++) {

            textViews[i].setText(rankings.get(i).getRanking().toUpperCase(Locale.getDefault()));
            textViews[i].setVisibility(View.VISIBLE);
            products[i] = rankings.get(i).getProducts();

            if (i == 0) {
                Collections.sort(products[i], new SortProductbyViewCount("DESC"));
            } else if (i == 1) {
                Collections.sort(products[i], new SortProductbyOrderCount("DESC"));
            } else if (i == 2) {
                Collections.sort(products[i], new SortProductbyShareCount("DESC"));
            }

            pAdapters[i] = new ProductAdapter(products[i], itemWidthHieght, new ProductOnClickListner() {

                @Override
                public void onItemClick(View v, int position, Product product) {
                    System.out.println(position);
                    Intent intent = new Intent(MainActivity.this, ProductDetail.class);
                    intent.putExtra("product", product);
                    startActivity(intent);
                    System.out.println("==============================");
                }
            });

            lists[i].setAdapter(pAdapters[i]);
        }

    }

    @OnClick(R.id.textViewCategory)
    public void onTextViewCategoryClick() {
        Intent intent = new Intent(MainActivity.this, CategoryList.class);
        intent.putExtra("catagory", hierarchyCategories);
        startActivity(intent);
    }

    @OnTextChanged(R.id.textSearch)
    public void ontextSearch() {
        String text = editsearch.getText().toString().toLowerCase(Locale.ENGLISH);
        if (pAdapters != null) {
            for (int i = 0; i < pAdapters.length; i++) {
                pAdapters[i].filter(text);
                if (pAdapters[i].getItemCount() == 0) {
                    textViews[i].setVisibility(View.GONE);
                } else {
                    textViews[i].setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    public final boolean isInternetOn() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // ARE WE CONNECTED TO THE NET
        if (connectivityManager.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING ||
                connectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING ||
                connectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {
            // MESSAGE TO SCREEN FOR TESTING (IF REQ)
            // Toast.makeText(this, connectionType + ” connected”,
            // Toast.LENGTH_SHORT).show();
            return true;
        } else if (connectivityManager.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||
                connectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {
            // System.out.println(“Not Connected”);
            return false;
        }
        return false;
    }

}
