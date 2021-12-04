package com.example.uas_pbp_rmc;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uas_pbp_rmc.controller.HomeItemRVController;
import com.example.uas_pbp_rmc.databinding.FragmentHomeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    List<ProductItem> productItemList;
    FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        binding.setActivity(this);

        StoreItemDatagen generator = new StoreItemDatagen();
        productItemList = generator.retrieveItemList();
        HomeItemRVController rvController = new HomeItemRVController(productItemList, container.getContext(), getActivity());
        binding.setRvadapter(rvController);

        return binding.getRoot();
    }
}