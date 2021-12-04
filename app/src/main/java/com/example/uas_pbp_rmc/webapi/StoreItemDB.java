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
    StoreItemDB.StoreDataNotifier notifierImplement;
    private DatabaseReference mDatabase;

    public StoreItemDB(Context context, StoreItemDB.StoreDataNotifier _notifierImplement){
        notifierImplement = _notifierImplement;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notifierImplement.onDataChange();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                notifierImplement.onCancelled();
            }
        });
    }

    public interface StoreDataNotifier {
        public void onDataChange();
        public void onCancelled();
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
