package com.example.uas_pbp_rmc.model;

public class ProductItem {
    public String productName;
    public float price;
    public String productInfo;
    public String imageURL;

    public ProductItem(String productName, float price, String productInfo, String imageURL){
        this.productName = productName;
        this.price = price;
        this.productInfo = productInfo;
        this.imageURL = imageURL;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName){
        this.productName = productName;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getProductInfo() {
        return productInfo;
    }
    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }
    public String getImageURL() { return imageURL; }
    public void setImageURL(String imageURL) { this.imageURL = imageURL; }
}