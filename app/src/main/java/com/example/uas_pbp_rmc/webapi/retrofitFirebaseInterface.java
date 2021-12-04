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
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface retrofitFirebaseInterface {
    //CRUD Profil ----------------------------------------------
    @Headers({"Accept: application/json"})
    @GET("user")
    Call<ProfilResponse> getAllProfil();

    @Headers({"Accept: application/json"})
    @GET("user/{id}")
    Call<ProfilResponse> getProfilById(@Path("id") long id);

    @Headers({"Accept: application/json"})
    @POST("user")
    Call<ProfilResponse> createProfil(@Body Profil profil);

    @Headers({"Accept: application/json"})
    @DELETE("user/{id}")
    Call<ProfilResponse> deleteProfil(@Path("id") long id);

    @Headers({"Accept: application/json"})
    @PATCH("user/{id}")
    Call<ProfilResponse> updateProfil(@Path("id") long id, @Body Profil profil);

    //CRUD Product ----------------------------------------------
    @Headers({"Accept: application/json"})
    @GET("product")
    Call<ProfilResponse> getAllProduct();

    @Headers({"Accept: application/json"})
    @GET("product/{id}")
    Call<ProfilResponse> getProductById(@Path("id") long id);

    @Headers({"Accept: application/json"})
    @POST("product")
    Call<ProfilResponse> createProduct(@Body ProductItem productItem);

    @Headers({"Accept: application/json"})
    @DELETE("product/{id}")
    Call<ProfilResponse> deleteProduct(@Path("id") long id);

    @Headers({"Accept: application/json"})
    @PATCH("product/{id}")
    Call<ProfilResponse> updateProduct(@Path("id") long id, @Body ProductItem productItem);
}
