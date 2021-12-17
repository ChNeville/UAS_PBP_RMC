package com.example.uas_pbp_rmc.webapi;

import com.example.uas_pbp_rmc.model.Profil;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfilResponse {
    private String message;
    @SerializedName("profil")
    private Profil profil;
}
