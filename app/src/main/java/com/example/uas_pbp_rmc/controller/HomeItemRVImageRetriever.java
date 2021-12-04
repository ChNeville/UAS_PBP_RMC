package com.example.uas_pbp_rmc.controller;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.uas_pbp_rmc.R;

public class HomeItemRVImageRetriever {
    @BindingAdapter({"imagegen:url"})
    public static void loadImage(ImageView view, String URL){
        class HomeItemRVImageRetrieverTask implements LoadImageTask.Listener{
            ImageView view;
            public HomeItemRVImageRetrieverTask(ImageView view, String URL){
                this.view = view;
                new LoadImageTask(this).execute(URL);
            }

            @Override
            public void onImageLoaded(Bitmap bitmap) {
                view.setImageBitmap(bitmap);
            }

            @Override
            public void onError() {
                view.setImageResource(R.drawable.ic_baseline_error_outline_24);
            }
        }

        HomeItemRVImageRetrieverTask run = new HomeItemRVImageRetrieverTask(view, URL);
    }
}
