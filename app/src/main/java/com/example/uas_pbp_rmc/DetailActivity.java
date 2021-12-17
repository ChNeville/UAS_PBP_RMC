package com.example.uas_pbp_rmc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.uas_pbp_rmc.controller.DetailClickListener;
import com.example.uas_pbp_rmc.databinding.ActivityDetailBinding;
import com.example.uas_pbp_rmc.model.ProductItem;

import com.example.uas_pbp_rmc.webapi.ApiServer;
import com.example.uas_pbp_rmc.webapi.ProductResponse;
import com.example.uas_pbp_rmc.webapi.ApiWebProduct;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity
        extends AppCompatActivity
        implements DetailClickListener{

    ApiWebProduct apiService;
    ProductItem productItem;
    int itemID;

    Intent intent;

    ActivityDetailBinding binding;
    FloatingActionButton mdetail_add_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mdetail_add_cart = findViewById(R.id.detail_add_cart);
        intent = getIntent();

        itemID = intent.getIntExtra("itemID", -1);

        apiService = ApiServer.getClient().create(ApiWebProduct.class);

        productItem = new ProductItem(-1, "DUMMY", 0, "","");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        binding.setDetailClickListener(this);
        binding.setModel(productItem);

        setTitle(R.string.view_title_detail);

        getProductByID(itemID);
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
        addProductToCart(itemID);
        PushNotification("Notification From HP SECOND", "Produk Berhasil Masuk Keranjang!");

        setResult(Activity.RESULT_OK);
        finish();
    }

    private void getProductByID(int id){
        Context ctx = this;

        Call<ProductResponse> call = apiService.getAllProduct();

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(ctx,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    if(response.body().getProductList().size() > 0) {
                        ProductItem np = response.body().getProductList().get(0);
                        productItem.setId(np.id);
                        productItem.setImageURL(np.imageURL);
                        productItem.setProductName(np.getProductName());
                        productItem.setProductInfo(np.productInfo);
                        productItem.setPrice(np.getPrice());
                    }else{
                        Toast.makeText(ctx,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(ctx,"Failed to connect to Web API!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addProductToCart(int id){
        /*
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
        });*/
    }
}