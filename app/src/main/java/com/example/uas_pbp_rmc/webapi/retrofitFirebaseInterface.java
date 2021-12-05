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

// TODO : Rangkai routing databasenya kyk gini Rama
public interface retrofitFirebaseInterface {
    //CRUD Profil ----------------------------------------------
    @Headers({"Accept: application/json"})
    @GET("profil")
    Call<ProfilResponse> getAllProfil();

    @Headers({"Accept: application/json"})
    @GET("profil/{id}")
    Call<ProfilResponse> getProfilById(@Path("id") int id);

    @Headers({"Accept: application/json"})
    @POST("profil")
    Call<ProfilResponse> createProfil(@Body Profil profil);

    @Headers({"Accept: application/json"})
    @DELETE("profil/{id}")
    Call<ProfilResponse> deleteProfil(@Path("id") int id);

    @Headers({"Accept: application/json"})
    @PATCH("profil/{id}")
    Call<ProfilResponse> updateProfil(@Path("id") int id, @Body Profil profil);

    //CRUD Product ----------------------------------------------
    @Headers({"Accept: application/json"})
    @GET("product")
    Call<ProductResponse> getAllProduct();

    @Headers({"Accept: application/json"})
    @GET("product/{id}")
    Call<ProductResponse> getProductById(@Path("id") int id);

    @Headers({"Accept: application/json"})
    @POST("product")
    Call<ProductResponse> createProduct(@Body ProductItem productItem);

    @Headers({"Accept: application/json"})
    @DELETE("product/{id}")
    Call<ProductResponse> deleteProduct(@Path("id") int id);

    @Headers({"Accept: application/json"})
    @PATCH("product/{id}")
    Call<ProductResponse> updateProduct(@Path("id") int id, @Body ProductItem productItem);
}
