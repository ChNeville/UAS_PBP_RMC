package com.example.uas_pbp_rmc.controller;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas_pbp_rmc.BR;
import com.example.uas_pbp_rmc.R;
import com.example.uas_pbp_rmc.databinding.ListItemCartBinding;
import com.example.uas_pbp_rmc.model.ProductItem;
import com.example.uas_pbp_rmc.webapi.ProductResponse;
import com.example.uas_pbp_rmc.webapi.retrofitFirebaseInterface;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartItemRVController
        extends RecyclerView.Adapter<CartItemRVController.ViewHolder>
        implements CartItemRVClickListener{
    retrofitFirebaseInterface apiService;

    Context context;
    Activity activity;

    private List<Integer> itemList;
    private List<ProductItem> itemDB;

    public CartItemRVController(List<Integer> itemList, Context context, Activity activity){
        this.itemList = itemList;
        this.context = context;
        this.activity = activity;
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
        itemList = new List<Integer>() {
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
            public Iterator<Integer> iterator() {
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
            public boolean add(Integer integer) {
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
            public boolean addAll(@NonNull Collection<? extends Integer> collection) {
                return false;
            }

            @Override
            public boolean addAll(int i, @NonNull Collection<? extends Integer> collection) {
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
            public Integer get(int i) {
                return null;
            }

            @Override
            public Integer set(int i, Integer integer) {
                return null;
            }

            @Override
            public void add(int i, Integer integer) {

            }

            @Override
            public Integer remove(int i) {
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
            public ListIterator<Integer> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Integer> listIterator(int i) {
                return null;
            }

            @NonNull
            @Override
            public List<Integer> subList(int i, int i1) {
                return null;
            }
        };
        return itemList.size();
    }

    @Override
    public void itemDeleteAction(int index) {
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

    private void getProductDB(){
        Call<ProductResponse> call = apiService.getAllProduct();

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()){
                    itemDB = response.body().getProductList();
                }
            }
            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {}
        });
    }
}
