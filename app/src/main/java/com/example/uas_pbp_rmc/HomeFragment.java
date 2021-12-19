package com.example.uas_pbp_rmc;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.uas_pbp_rmc.controller.HomeItemRVController;
import com.example.uas_pbp_rmc.databinding.FragmentHomeBinding;
import com.example.uas_pbp_rmc.model.ProductItem;
import com.example.uas_pbp_rmc.state.AdminState;
import com.example.uas_pbp_rmc.webapi.ProductListResponse;
import com.example.uas_pbp_rmc.webapi.ApiServer;
import com.example.uas_pbp_rmc.webapi.ApiWebProduct;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    AdminState adminState;

    ApiWebProduct apiService;

    FragmentHomeBinding binding;
    HomeItemRVController rvController;

    Button adminAddFAB;

    Context context;

    public HomeFragment() {}

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiService = ApiServer.getClient().create(ApiWebProduct.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        binding.setActivity(this);

        context = getContext();

        adminAddFAB = getView().findViewById(R.id.home_admin_add_product);
        adminAddFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Buka activity addedit buat produk di sini!
            }
        });
        adminAddFAB.setVisibility(View.GONE);

        rvController = new HomeItemRVController(new ArrayList<ProductItem>(), apiService, container.getContext(), getActivity());
        binding.setRvadapter(rvController);

        fetchProduct();

        adminState = new AdminState(getContext());
        if(adminState.getAdminState() == true){
            rvController.setAdminMode(true);
            adminAddFAB.setVisibility(View.VISIBLE);
        }

        return binding.getRoot();
    }

    private void fetchProduct(){
        Call<ProductListResponse> call = apiService.getAllProduct();

        call.enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(Call<ProductListResponse> call, Response<ProductListResponse> response) {
                if(response.body()!= null){
                    if (response.isSuccessful()){
                        rvController.setItemList(response.body().getProductList());
                        rvController.notifyDataSetChanged();

                        Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ProductListResponse> call, Throwable t) {
                Toast.makeText(context,"Failed to connect to Web API!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean changeFragment(Fragment fragment){
        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, fragment)
                    .commit();
            return true;
        }return false;
    }
}