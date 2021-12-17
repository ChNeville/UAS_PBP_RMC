package com.example.uas_pbp_rmc.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.uas_pbp_rmc.BR;

public class ProductItem extends BaseObservable {
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
    @Bindable
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName){
        this.productName = productName;
        notifyPropertyChanged(BR.productName);
    }
    @Bindable
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }
    @Bindable
    public String getProductInfo() {
        return productInfo;
    }
    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
        notifyPropertyChanged(BR.productInfo);
    }
    @Bindable
    public String getImageURL() { return imageURL; }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
        notifyPropertyChanged(BR.imageURL);
    }
}
