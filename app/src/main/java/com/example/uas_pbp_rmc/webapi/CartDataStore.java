package com.example.uas_pbp_rmc.webapi;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CartDataStore {
    String userRef;

    public CartDataStore(){
    }

    public void addCartData(Integer itemID){

    }

    public void deleteCartData(Integer listIndex){

    }

    private void setCartData(List<Integer> itemList){

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
