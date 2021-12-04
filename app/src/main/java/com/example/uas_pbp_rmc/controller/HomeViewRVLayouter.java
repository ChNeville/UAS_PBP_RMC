package com.example.uas_pbp_rmc.controller;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeViewRVLayouter {
    @BindingAdapter({"rvlayout:grid"})
    public static void setGridLayout(RecyclerView view, int columnSize){
        GridLayoutManager manager = new GridLayoutManager(view.getContext(),columnSize);
        view.setLayoutManager(manager);
    }
}
