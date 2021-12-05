package com.example.uas_pbp_rmc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.uas_pbp_rmc.controller.DetailClickListener;
import com.example.uas_pbp_rmc.databinding.ActivityDetailBinding;
import com.example.uas_pbp_rmc.model.ProductItem;

import com.example.uas_pbp_rmc.webapi.ProductResponse;
import com.example.uas_pbp_rmc.webapi.retrofitFirebaseInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity
        extends AppCompatActivity
        implements DetailClickListener{

    retrofitFirebaseInterface apiService;
    ProductItem productItem;

    Intent intent;

    ActivityDetailBinding binding;
    FloatingActionButton mdetail_add_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mdetail_add_cart = findViewById(R.id.detail_add_cart);
        intent = getIntent();

        long itemID = intent.getIntExtra("itemID",0);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        binding.setDetailClickListener(this);
        binding.setModel(productItem);

        setTitle(R.string.view_title_detail);
    }

    @Override
    public void PushNotification(String title, String body) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.img)
                .build();

        notificationManager.notify(0, notification);

    }

    @Override
    public void cartAddClicked() {

        PushNotification("Notification From HP SECOND", "Produk Berhasil Masuk Keranjang!");

        setResult(Activity.RESULT_OK);
        finish();
    }

    private void getProductByID(long id){
        Call<ProductResponse> call = apiService.getProductById(id);

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()){
                    productItem = response.body().getProductList().get(0);
                }
            }
            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {}
        });
    }

    private void addProductToCart(long id){
        Call<ProductResponse> call = apiService.getProductById(id);

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()){
                    productItem = response.body().getProductList().get(0);
                }
            }
            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {}
        });
    }
}