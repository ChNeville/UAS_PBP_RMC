package com.example.uas_pbp_rmc.webapi;

import com.example.uas_pbp_rmc.model.ProductItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductResponse {
    private String message;
    @SerializedName("product")
    private ProductItem product;

    public String getMessage() {
        return message;
    }
    public ProductItem getProduct() {
        return product;
    }
}
