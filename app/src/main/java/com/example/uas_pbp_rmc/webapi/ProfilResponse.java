package com.example.uas_pbp_rmc.webapi;

import com.example.uas_pbp_rmc.model.Profil;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfilResponse {
    @SerializedName("profil")
    private List<Profil> profilList;

    public List<Profil> getProfilList() {
        return profilList;
    }

    public void setProfilList(List<Profil> profilList) {
        this.profilList = profilList;
    }
}
