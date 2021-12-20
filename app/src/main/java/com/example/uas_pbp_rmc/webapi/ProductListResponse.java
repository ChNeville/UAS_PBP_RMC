package com.example.uas_pbp_rmc.webapi;

import com.example.uas_pbp_rmc.model.ProductItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductListResponse {
    private String message;
    @SerializedName("product")
    private List<ProductItem> productList;

    public String getMessage() {
        return message;
    }
    public List<ProductItem> getProductList() {
        return productList;
    }
}
