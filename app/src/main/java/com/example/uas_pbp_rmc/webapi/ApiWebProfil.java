package com.example.uas_pbp_rmc.webapi;

import com.example.uas_pbp_rmc.model.Profil;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiWebProfil {
    @Headers({"Accept: application/json"})
    @GET("profil")
    Call<ProfilResponse> getAllProfil();

    @Headers({"Accept: application/json"})
    @GET("profil/{id}")
    Call<ProfilResponse> getProfilById(@Path("id") int id);

    @Headers({"Accept: application/json"})
    @GET("profil/username/{username}")
    Call<ProfilResponse> getProfilByUsername(@Path("username") String username);

    @Headers({"Accept: application/json"})
    @POST("profil")
    Call<ProfilResponse> createProfil(@Body Profil profil);

    @Headers({"Accept: application/json"})
    @DELETE("profil/{id}")
    Call<ProfilResponse> deleteProfil(@Path("id") int id);

    @Headers({"Accept: application/json"})
    @PUT("profil/{id}")
    Call<ProfilResponse> updateProfil(@Path("id") int id, @Body Profil profil);
}
