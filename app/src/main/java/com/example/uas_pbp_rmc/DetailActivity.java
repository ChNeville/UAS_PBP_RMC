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
import com.example.uas_pbp_rmc.webapi.CartDataStore;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements DetailClickListener {

    Intent intent;
    CartDataStore cartDataStore;

    ActivityDetailBinding binding;
    List<ProductItem> itemList;
    int itemID;

    FloatingActionButton mdetail_add_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mdetail_add_cart = findViewById(R.id.detail_add_cart);
        intent = getIntent();

        cartDataStore = new CartDataStore(DetailActivity.this);

        //StoreItemDatagen generator = new StoreItemDatagen();
        //itemList = generator.retrieveItemList();

        itemID = intent.getIntExtra("itemID",0);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        binding.setDetailClickListener(this);
        binding.setModel(itemList.get(itemID));

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
        List<Integer> itemCart = cartDataStore.getCartData();
        itemCart.add(itemID);
        cartDataStore.setCartData(itemCart);
        PushNotification("Notification From HP SECOND", "Produk Berhasil Masuk Keranjang!");

        setResult(Activity.RESULT_OK);
        finish();
    }
}