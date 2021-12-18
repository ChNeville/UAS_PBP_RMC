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
import com.example.uas_pbp_rmc.model.Profil;
import com.example.uas_pbp_rmc.state.AdminState;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilFragment extends Fragment implements ProfilViewClickListener {
    AdminState adminState;
    FirebaseAuth mAuth;
    String username;

    FragmentProfilBinding binding;

    Profil profil;

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
        adminState = new AdminState(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null && adminState.getAdminState() != true){
            username = currentUser.getDisplayName();
        }else if(adminState.getAdminState() == true){
            profil = new Profil("admin@mail.com","ADMINISTRATOR");
        }else{
            Activity activity = getActivity();
            Intent intent = new Intent(activity,LoginActivity.class);
            activity.startActivity(intent);
        }

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profil,container,false);
        binding.setProfileClickListener(this);
        binding.setProfile(profil);
        return binding.getRoot();
    }

    @Override
    public void onProfileEditClicked() {
        if(adminState.getAdminState() != true) {
            //TODO: Launch Activity Edit User di Sini (Edit dan Delete), User admin tak bisa di edit!
        }
    }

    @Override
    public void onProfilLogoutClicked() {
        if(adminState.getAdminState() == true){
            adminState.setAdminState(false);
        }else{
            mAuth.signOut();
        }
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