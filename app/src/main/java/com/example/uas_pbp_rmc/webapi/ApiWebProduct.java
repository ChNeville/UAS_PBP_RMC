package com.example.uas_pbp_rmc.webapi;

import com.example.uas_pbp_rmc.model.ProductItem;
import com.example.uas_pbp_rmc.model.Profil;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

// TODO : Rangkai routing databasenya kyk gini Rama
public interface ApiWebProduct {
    @Headers({"Accept: application/json"})
    @GET("product")
    Call<ProductResponse> getAllProduct();

    @Headers({"Accept: application/json"})
    @GET("product/{id}")
    Call<ProductResponse> getProductById(@Path("id") long id);

    @Headers({"Accept: application/json"})
    @POST("product")
    Call<ProductResponse> createProduct(@Body ProductItem productItem);

    @Headers({"Accept: application/json"})
    @DELETE("product/{id}")
    Call<ProductResponse> deleteProduct(@Path("id") long id);

    @Headers({"Accept: application/json"})
    @PUT("product/{id}")
    Call<ProductResponse> updateProduct(@Path("id") long id, @Body ProductItem productItem);
}
