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

    public HomeItemRVController(List<ProductItem> itemList, Context context, Activity activity){
        this.itemList = itemList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public HomeItemRVController.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemStoreBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item_store,
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
    public int getItemCount() {
        itemList = new List<ProductItem>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(@Nullable Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<ProductItem> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] ts) {
                return null;
            }

            @Override
            public boolean add(ProductItem productItem) {
                return false;
            }

            @Override
            public boolean remove(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends ProductItem> collection) {
                return false;
            }

            @Override
            public boolean addAll(int i, @NonNull Collection<? extends ProductItem> collection) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public ProductItem get(int i) {
                return null;
            }

            @Override
            public ProductItem set(int i, ProductItem productItem) {
                return null;
            }

            @Override
            public void add(int i, ProductItem productItem) {

            }

            @Override
            public ProductItem remove(int i) {
                return null;
            }

            @Override
            public int indexOf(@Nullable Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(@Nullable Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<ProductItem> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<ProductItem> listIterator(int i) {
                return null;
            }

            @NonNull
            @Override
            public List<ProductItem> subList(int i, int i1) {
                return null;
            }
        };
        return itemList.size();
    }

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
