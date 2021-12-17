package com.example.uas_pbp_rmc.model;

public class ProductItem {
    public int id;
    public String productName;
    public float price;
    public String productInfo;
    public String imageURL;

    public ProductItem(int id, String productName, float price, String productInfo, String imageURL){
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.productInfo = productInfo;
        this.imageURL = imageURL;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
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
