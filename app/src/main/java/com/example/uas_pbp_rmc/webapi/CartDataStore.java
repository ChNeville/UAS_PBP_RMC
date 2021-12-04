package com.example.uas_pbp_rmc.webapi;

import android.content.Context;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CartDataStore {
    Context context;

    public CartDataStore(Context context){
        this.context = context;
    }

    public void setCartData(List<Integer> itemList){
        Gson gson = new Gson();
        DItemList _itemList = new DItemList(itemList);
        String itemListJson = gson.toJson(_itemList);
        // TODO : CRUD CREATE - WEB API USER CART
    }

    public List<Integer> getCartData(){
        Gson gson = new Gson();
        // TODO : CRUD GET - WEB API USER CART
        String stringData = "";
        DItemList dItemList = gson.fromJson(stringData, DItemList.class);
        List<Integer> itemList = new ArrayList<>();
        if(dItemList != null){
            itemList = dItemList.get();
        }
        return itemList;
    }

    private class DItemList{
        List<Integer> itemList;
        public DItemList(List<Integer> itemList){
            this.itemList = itemList;
        }
        public List<Integer> get(){
            return itemList;
        }
    }
}
