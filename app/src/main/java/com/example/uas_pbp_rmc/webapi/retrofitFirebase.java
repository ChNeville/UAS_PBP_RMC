package com.example.uas_pbp_rmc.webapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitFirebase {
    public static final String BASE_URL = "https://pbp-uas-rmc-default-rtdb.asia-southeast1.firebasedatabase.app/";
    public static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
