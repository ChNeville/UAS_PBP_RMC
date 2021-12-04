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
    CartDataNotifier notifierImplement;
    private DatabaseReference mDatabase;

    public CartDataStore(Context context, CartDataNotifier _notifierImplement){
        notifierImplement = _notifierImplement;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DItemList dItemList = dataSnapshot.child("users").child(userRef).child("cart").getValue(DItemList.class);
                notifierImplement.onDataChange(dItemList.get());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                notifierImplement.onCancelled();
            }
        });
    }

    public void setCartData(List<Integer> itemList){
        DItemList _itemList = new DItemList(itemList);
        // TODO : CRUD CREATE - WEB API USER CART
        mDatabase.child("users").child(userRef).child("cart").setValue(_itemList);
    }

    public interface CartDataNotifier {
        public void onDataChange(List<Integer> itemList);
        public void onCancelled();
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
