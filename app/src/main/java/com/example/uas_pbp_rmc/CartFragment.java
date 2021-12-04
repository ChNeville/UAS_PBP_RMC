package com.example.uas_pbp_rmc;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uas_pbp_rmc.controller.CartItemRVController;
import com.example.uas_pbp_rmc.databinding.FragmentCartBinding;
import com.example.uas_pbp_rmc.webapi.CartDataStore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    FragmentCartBinding binding;
    CartDataStore cartDataStore;

    public CartFragment(CartDataStore cartDataStore) {
        this.cartDataStore = cartDataStore;
    }

    public static CartFragment newInstance(CartDataStore cartDataStore) {
        CartFragment fragment = new CartFragment(cartDataStore);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart,container,false);
        binding.setActivity(this);

        CartItemRVController rvController = new CartItemRVController(cartDataStore, container.getContext(), getActivity());
        binding.setRvadapter(rvController);

        return binding.getRoot();
    }
}