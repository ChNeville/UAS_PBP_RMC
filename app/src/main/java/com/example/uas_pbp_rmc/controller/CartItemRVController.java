package com.example.uas_pbp_rmc.controller;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas_pbp_rmc.BR;
import com.example.uas_pbp_rmc.R;
import com.example.uas_pbp_rmc.databinding.ListItemCartBinding;
import com.example.uas_pbp_rmc.model.ProductItem;
import com.example.uas_pbp_rmc.model.Profil;
import com.example.uas_pbp_rmc.webapi.ApiWebProfil;
import com.example.uas_pbp_rmc.webapi.ProductListResponse;
import com.example.uas_pbp_rmc.webapi.ApiWebProduct;
import com.example.uas_pbp_rmc.webapi.ProfilResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartItemRVController
        extends RecyclerView.Adapter<CartItemRVController.ViewHolder>
        implements CartItemRVClickListener{
    ApiWebProduct apiService;
    ApiWebProfil apiServProf;

    Context context;
    Activity activity;

    String username;
    Profil profil;

    private List<Integer> itemList;
    private List<ProductItem> itemDB;

    public CartItemRVController(List<Integer> itemList, String username, ApiWebProfil apiService, Context context, Activity activity){
        this.itemList = itemList;
        this.context = context;
        this.activity = activity;
        this.apiServProf = apiService;
        this.username = username;
        getUserCartData();
    }

    @NonNull
    @Override
    public CartItemRVController.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemCartBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item_cart,
                parent, false);
        getUserCartData();
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

    public List<Integer> getItemList() {
        return itemList;
    }

    public void setItemList(List<Integer> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @Override
    public void itemDeleteAction(int index) {
        itemList.remove(index);
        profil.setCartData(itemList);
        updateUserCartData();
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
        Call<ProductListResponse> call = apiService.getAllProduct();

        call.enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(Call<ProductListResponse> call, Response<ProductListResponse> response) {
                if (response.isSuccessful()){
                    itemDB = response.body().getProductList();
                }
            }
            @Override
            public void onFailure(Call<ProductListResponse> call, Throwable t) {}
        });
    }

    private void getUserCartData(){
        Call<ProfilResponse> call = apiServProf.getProfilByUsername(username);

        call.enqueue(new Callback<ProfilResponse>() {
            @Override
            public void onResponse(Call<ProfilResponse> call, Response<ProfilResponse> response) {
                if (response.body() != null){
                    if(response.isSuccessful()){
                        profil = response.body().getProfil();
                        setItemList(profil.getCartData());
                        //Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ProfilResponse> call, Throwable t) {
                Toast.makeText(context,"Failed to connect to Web API!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUserCartData(){
        Call<ProfilResponse> call = apiServProf.updateProfil(profil.id,profil);

        call.enqueue(new Callback<ProfilResponse>() {
            @Override
            public void onResponse(Call<ProfilResponse> call, Response<ProfilResponse> response) {
                if (response.body() != null){
                    if(response.isSuccessful()){
                        profil = response.body().getProfil();
                        setItemList(profil.getCartData());
                        //Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ProfilResponse> call, Throwable t) {
                Toast.makeText(context,"Failed to connect to Web API!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
