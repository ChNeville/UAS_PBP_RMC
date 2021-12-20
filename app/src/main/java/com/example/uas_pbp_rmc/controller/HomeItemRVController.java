package com.example.uas_pbp_rmc.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas_pbp_rmc.BR;
import com.example.uas_pbp_rmc.DetailActivity;
import com.example.uas_pbp_rmc.ProductEditActivity;
import com.example.uas_pbp_rmc.R;
import com.example.uas_pbp_rmc.databinding.ListItemStoreBinding;
import com.example.uas_pbp_rmc.model.ProductItem;
import com.example.uas_pbp_rmc.webapi.ApiWebProduct;
import com.example.uas_pbp_rmc.webapi.ProductResponse;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeItemRVController
        extends RecyclerView.Adapter<HomeItemRVController.ViewHolder>
        implements HomeItemRVClickListener{

    private ApiWebProduct apiService;
    private List<ProductItem> itemList;
    private Context context;
    private Activity activity;
    private LayoutInflater layoutInflater;

    private EditorVisibility editorVisibility;

    public static final int LAUNCH_ADD_ACTIVITY = 123;

    public HomeItemRVController(List<ProductItem> itemList, ApiWebProduct apiService, Context context, Activity activity){
        this.apiService = apiService;
        this.itemList = itemList;
        this.context = context;
        this.activity = activity;

        editorVisibility = new EditorVisibility();
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
        holder.binding.setItemEditable(editorVisibility);
        holder.binding.setModelID(position);
        holder.binding.setItemClickListener(this);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public void itemClicked(int index) {
        Intent toDetailActivity = new Intent(activity, DetailActivity.class);
        toDetailActivity.putExtra("itemID", itemList.get(index).id);
        activity.startActivityForResult(toDetailActivity,24);
    }

    @Override
    public void adminDeleteAction(int index) {
        Call<ProductResponse> call = apiService.deleteProduct(itemList.get(index).id);

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.body() != null){
                    if (response.isSuccessful()){
                        Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(context,"Failed to connect to Web API!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void adminEditAction(int index) {
        //TODO: Buat Activity Edit di sini dan jalankan CRUD Update!
        Intent intent = new Intent(activity, ProductEditActivity.class);
        intent.putExtra("id",(long) itemList.get(index).id);
        activity.startActivityForResult(intent, LAUNCH_ADD_ACTIVITY);
    }

    public List<ProductItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<ProductItem> itemList) {
        this.itemList = itemList;
    }

    public void setAdminMode(Boolean mode){
        if(mode == true){
            editorVisibility.setEditorVisibility(0);
        }else{
            editorVisibility.setEditorVisibility(View.GONE);
        }
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

    public class EditorVisibility extends BaseObservable{
        public int editorVisibility = View.GONE;

        @Bindable
        public int getEditorVisibility() {
            return editorVisibility;
        }
        public void setEditorVisibility(int editorVisibility) {
            this.editorVisibility = editorVisibility;
            notifyPropertyChanged(BR.itemEditable);
        }
    }
}
