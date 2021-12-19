package com.example.uas_pbp_rmc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.uas_pbp_rmc.controller.CartItemRVController;
import com.example.uas_pbp_rmc.databinding.FragmentCartBinding;
import com.example.uas_pbp_rmc.model.Profil;
import com.example.uas_pbp_rmc.state.AdminState;
import com.example.uas_pbp_rmc.webapi.ApiServer;
import com.example.uas_pbp_rmc.webapi.ApiWebProfil;
import com.example.uas_pbp_rmc.webapi.ProfilResponse;
import com.google.android.gms.actions.ItemListIntents;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {
    AdminState adminState;
    FirebaseAuth mAuth;
    ApiWebProfil apiService;
    String username;

    FragmentCartBinding binding;

    CartItemRVController rvController;

    public CartFragment() {}

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adminState = new AdminState(getContext());
        apiService = ApiServer.getClient().create(ApiWebProfil.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null && adminState.getAdminState() != true){
            username = currentUser.getEmail();
        }else if(adminState.getAdminState() == true){
        }else{
            Activity activity = getActivity();
            Intent intent = new Intent(activity,LoginActivity.class);
            activity.startActivity(intent);
        }

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart,container,false);
        binding.setActivity(this);

        rvController = new CartItemRVController(new ArrayList<Integer>() ,username, apiService, container.getContext(), getActivity());
        binding.setRvadapter(rvController);

        return binding.getRoot();
    }
}