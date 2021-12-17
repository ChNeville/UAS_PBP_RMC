package com.example.uas_pbp_rmc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uas_pbp_rmc.controller.ProfilViewClickListener;
import com.example.uas_pbp_rmc.databinding.FragmentProfilBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilFragment extends Fragment implements ProfilViewClickListener {
    FirebaseAuth mAuth;
    String username;

    FragmentProfilBinding binding;

    public ProfilFragment() {
        // Required empty public constructor
    }

    public static ProfilFragment newInstance(String param1, String param2) {
        ProfilFragment fragment = new ProfilFragment();
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
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            username = currentUser.getDisplayName();
        }else{
            Activity activity = getActivity();
            Intent intent = new Intent(activity,LoginActivity.class);
            activity.startActivity(intent);
        }

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profil,container,false);
        binding.setProfileClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onProfilLogoutClicked() {
        mAuth.signOut();
        changeFragment(new HomeFragment());
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