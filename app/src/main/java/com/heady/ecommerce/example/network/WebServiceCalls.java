package com.heady.ecommerce.example.network;

import android.content.Context;
import android.os.Bundle;

import com.heady.ecommerce.example.Utils.Utility;
import com.heady.ecommerce.example.model.Category;
import com.heady.ecommerce.example.model.Ranking;
import com.heady.ecommerce.example.network.Retrofit.RFInterface;
import com.heady.ecommerce.example.network.Retrofit.ResponseModels.RmData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WebServiceCalls {

    private final String MyPREFERENCES = "Details";
    private RFInterface rfInterface = null;

    public WebServiceCalls(String service) {
        rfInterface = Utility.getRetrofitInterface(service);
    }

    public class Data {

        public void getDetails(final Context activity, final NetworkOperations nwCall) {
            nwCall.onStart(activity, "Downloading... ");
            rfInterface.getDetails().enqueue(new Callback<RmData>() {
                @Override
                public void onResponse(Call<RmData> call, Response<RmData> response) {
                    try {
                        nwCall.onComplete();

                        ArrayList<Category> categories = response.body().getCategories();
                        ArrayList<Ranking> rankings = response.body().getRankings();

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("catagory", categories);
                        bundle.putSerializable("ranking", rankings);

                        nwCall.onSuccess(bundle);

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

                @Override
                public void onFailure(Call<RmData> call, Throwable t) {
                    nwCall.onComplete();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("message", "Something went wrong, please try again");
                    nwCall.onFailure(bundle);
                }
            });
        }


    }


}
