package com.example.uas_pbp_rmc;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uas_pbp_rmc.controller.CartItemRVController;
import com.example.uas_pbp_rmc.databinding.FragmentCartBinding;
import com.google.android.gms.actions.ItemListIntents;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    FirebaseAuth mAuth;
    String username;

    FragmentCartBinding binding;

    List<Integer> itemList;

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
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            username = currentUser.getDisplayName();
        }else{
            // TODO : Start Login Activity
        }

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart,container,false);
        binding.setActivity(this);

        CartItemRVController rvController = new CartItemRVController(itemList, container.getContext(), getActivity());
        binding.setRvadapter(rvController);

        return binding.getRoot();
    }

    private void getUserCartData(String username){

    }
}