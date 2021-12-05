package com.example.uas_pbp_rmc.webapi;

import com.example.uas_pbp_rmc.model.ProductItem;
import com.example.uas_pbp_rmc.model.Profil;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductResponse {
    @SerializedName("product")
    private List<ProductItem> productList;

    public List<ProductItem> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductItem> productList) {
        this.productList = productList;
    }
}