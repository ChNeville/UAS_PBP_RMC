package com.example.uas_pbp_rmc.webapi;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.uas_pbp_rmc.model.ProductItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class StoreItemDB {
    String userRef;

    public StoreItemDB(){

    }

    private class DProductList{
        List<ProductItem> itemList;
        public DProductList(List<ProductItem> itemList){
            this.itemList = itemList;
        }
        public List<ProductItem> get(){
            return itemList;
        }
    }
}
