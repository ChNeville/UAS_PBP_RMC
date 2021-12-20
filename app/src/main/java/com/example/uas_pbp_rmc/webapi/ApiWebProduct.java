package com.example.uas_pbp_rmc.webapi;

import com.example.uas_pbp_rmc.model.ProductItem;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiWebProduct {
    @Headers({"Accept: application/json"})
    @GET("product")
    Call<ProductListResponse> getAllProduct();

    @Headers({"Accept: application/json"})
    @GET("product/{id}")
    Call<ProductListResponse> getProductById(@Path("id") long id);

    @Headers({"Accept: application/json"})
    @POST("product")
    Call<ProductListResponse> createProduct(@Body ProductItem productItem);

    @Headers({"Accept: application/json"})
    @DELETE("product/{id}")
    Call<ProductListResponse> deleteProduct(@Path("id") long id);

    @Headers({"Accept: application/json"})
    @PUT("product/{id}")
    Call<ProductListResponse> updateProduct(@Path("id") long id, @Body ProductItem productItem);
}
