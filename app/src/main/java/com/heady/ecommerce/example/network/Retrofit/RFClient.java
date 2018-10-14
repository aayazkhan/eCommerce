package com.heady.ecommerce.example.network.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RFClient {

    public static String BASE_URL = null;

    private static Retrofit retrofitInstanceOne = null;
    private static Retrofit retrofitInstancetwo = null;
    private static Retrofit retrofitInstancethree = null;

    public RFClient(String BASE_URL) {

    }

    public static Retrofit getClient(String BASE_URL1) {
        BASE_URL = BASE_URL1;
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);


        if (retrofitInstanceOne == null) {
            Gson gson = new GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
            retrofitInstanceOne = new Retrofit.Builder().client(clientBuilder.build()).baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofitInstanceOne;
    }

    //for adding dynamic headers with okhttpclient
    public static Retrofit getClient(OkHttpClient okHttpClient) {
        if (retrofitInstancetwo == null) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

            retrofitInstancetwo = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofitInstancetwo;
    }

    //for getting response as String
    public static Retrofit getClientWithStringResponse() {
        if (retrofitInstancethree == null) {
            retrofitInstancethree = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(new ToStringConverterFactory())
                    .build();
        }
        return retrofitInstancethree;
    }
}
