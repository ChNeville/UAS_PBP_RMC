package com.example.uas_pbp_rmc;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uas_pbp_rmc.controller.HomeItemRVController;
import com.example.uas_pbp_rmc.databinding.FragmentHomeBinding;
import com.example.uas_pbp_rmc.model.ProductItem;
import com.example.uas_pbp_rmc.webapi.ProductResponse;
import com.example.uas_pbp_rmc.webapi.retrofitFirebase;
import com.example.uas_pbp_rmc.webapi.retrofitFirebaseInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    retrofitFirebaseInterface apiService;
    List<ProductItem> productItemList;

    FragmentHomeBinding binding;
    HomeItemRVController rvController;

    public HomeFragment() {}

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiService = retrofitFirebase.getClient().create(retrofitFirebaseInterface.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        binding.setActivity(this);

        rvController = new HomeItemRVController(productItemList, container.getContext(), getActivity());
        binding.setRvadapter(rvController);

        fetchProduct();

        return binding.getRoot();
    }

    private void fetchProduct(){
        Call<ProductResponse> call = apiService.getAllProduct();

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()){
                    productItemList = response.body().getProductList();
                    rvController.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {}
        });
    }
}