package com.example.uas_pbp_rmc.controller;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas_pbp_rmc.BR;
import com.example.uas_pbp_rmc.R;
import com.example.uas_pbp_rmc.databinding.ListItemCartBinding;
import com.example.uas_pbp_rmc.model.ProductItem;
import com.example.uas_pbp_rmc.webapi.CartDataStore;

import java.util.List;

public class CartItemRVController
        extends RecyclerView.Adapter<CartItemRVController.ViewHolder>
        implements CartItemRVClickListener{

    CartDataStore cartDataStore;
    private List<Integer> itemList;
    private List<ProductItem> itemDB;
    Context context;
    Activity activity;

    public CartItemRVController(CartDataStore cartDataStore, Context context, Activity activity){
        this.cartDataStore = cartDataStore;
        this.context = context;
        this.activity = activity;

        itemList = cartDataStore.getCartData();

        // TODO : INSERT WEBAPI IMPLEMENTATION HERE (STOREITEM)
        //StoreItemDatagen generator = new StoreItemDatagen();
        //itemDB = generator.retrieveItemList();
    }

    @NonNull
    @Override
    public CartItemRVController.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemCartBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item_cart,
                parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemRVController.ViewHolder holder, int position) {
        int item = itemList.get(position);
        ProductItem itemInfo = itemDB.get(item);
        holder.bind(itemInfo);
        holder.binding.setItemID(position);
        holder.binding.setItemClickListener(this);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public void itemDeleteAction(int index) {
        itemList.remove(index);
        cartDataStore.setCartData(itemList);
        itemList = cartDataStore.getCartData();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ListItemCartBinding binding;

        public ViewHolder(@NonNull ListItemCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object obj){
            binding.setVariable(BR.model, obj);
            binding.executePendingBindings();
        }
    }
}
