package com.example.uas_pbp_rmc.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.uas_pbp_rmc.BR;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Profil extends BaseObservable {
    public int id = 0;
    public String username = ""; // TODO: Username pakai email ram
    public String membership = "";
    public String nama = "";
    public int age = 0;
    public String address = "";
    public String picture = "";
    public String userData = "";

    public Profil(){}
    public Profil(String username, String nama){
        this.username = username;
        this.nama = nama;
        this.membership = "bronze";
        this.age = 0;
        this.address = "No Address";
        this.picture = "NONE";
        this.userData = "invalid";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
        notifyPropertyChanged(BR.membership);
    }

    @Bindable
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
        notifyPropertyChanged(BR.nama);
    }

    @Bindable
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

    @Bindable
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    //Decode Gambar dari String
    public Bitmap getPictureBitmap(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        imageBytes = Base64.decode(this.picture, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        return decodedImage;
    }

    //Encode Gambar ke String
    public void setPictureBitmap(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        this.picture = Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public List<Integer> getCartData(){
        if(userData.equals("invalid")){
            return new ArrayList<Integer>();
        }
        Gson gson = new Gson();
        DUserData pdata = gson.fromJson(userData, DUserData.class);
        return pdata.getList();
    }

    public void setCartData(List<Integer> list){
        Gson gson = new Gson();
        DUserData pdata = new DUserData(list);
    }
}
