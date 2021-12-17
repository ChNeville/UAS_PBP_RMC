package com.example.uas_pbp_rmc.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas_pbp_rmc.BR;
import com.example.uas_pbp_rmc.DetailActivity;
import com.example.uas_pbp_rmc.R;
import com.example.uas_pbp_rmc.databinding.ListItemStoreBinding;
import com.example.uas_pbp_rmc.model.ProductItem;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class HomeItemRVController
        extends RecyclerView.Adapter<HomeItemRVController.ViewHolder>
        implements HomeItemRVClickListener{

    private List<ProductItem> itemList;
    private Context context;
    private Activity activity;
    private LayoutInflater layoutInflater;

    public HomeItemRVController(List<ProductItem> itemList, Context context, Activity activity){
        this.itemList = itemList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public HomeItemRVController.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ListItemStoreBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.list_item_store,
                parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeItemRVController.ViewHolder holder, int position) {
        ProductItem item = itemList.get(position);
        holder.bind(item);
        holder.binding.setModelID(position);
        holder.binding.setItemClickListener(this);
    }

    @Override
    public int getItemCount() { return itemList.size(); }

    @Override
    public void itemClicked(int index) {
        Intent toDetailActivity = new Intent(activity, DetailActivity.class);
        toDetailActivity.putExtra("itemID", index);
        activity.startActivityForResult(toDetailActivity,24);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ListItemStoreBinding binding;

        public ViewHolder(@NonNull ListItemStoreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object obj){
            binding.setVariable(BR.model, obj);
            binding.executePendingBindings();
        }
    }
}
