package com.example.uas_pbp_rmc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.uas_pbp_rmc.controller.ProfilViewClickListener;
import com.example.uas_pbp_rmc.databinding.FragmentProfilBinding;
import com.example.uas_pbp_rmc.model.Profil;
import com.example.uas_pbp_rmc.state.AdminState;
import com.example.uas_pbp_rmc.webapi.ApiServer;
import com.example.uas_pbp_rmc.webapi.ApiWebProduct;
import com.example.uas_pbp_rmc.webapi.ApiWebProfil;
import com.example.uas_pbp_rmc.webapi.ProductListResponse;
import com.example.uas_pbp_rmc.webapi.ProfilListResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilFragment extends Fragment implements ProfilViewClickListener {
    AdminState adminState;
    FirebaseAuth mAuth;
    ApiWebProfil apiService;
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
        apiService = ApiServer.getClient().create(ApiWebProfil.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        profil = new Profil();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null && adminState.getAdminState() != true){
            username = currentUser.getEmail();
            loadUserData();
        }else if(adminState.getAdminState() == true){
            profil.setUsername("admin@mail.com");
            profil.setNama("ADMINISTRATOR");
        }else{
            Activity activity = getActivity();
            Intent intent = new Intent(activity,LoginActivity.class);
            activity.startActivityForResult(intent, 0);
        }

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profil,container,false);
        binding.setProfileClickListener(this);
        binding.setProfile(profil);
        return binding.getRoot();
    }

    private void loadUserData(){
        Context context = getContext();

        Call<ProfilListResponse> call = apiService.getProfilByUsername(username);

        call.enqueue(new Callback<ProfilListResponse>() {
            @Override
            public void onResponse(Call<ProfilListResponse> call, Response<ProfilListResponse> response) {
                if (response.body() != null){
                    if (response.isSuccessful()){
                        Profil nprof = response.body().getProfilList().get(0);
                        profil.setUsername(nprof.getUsername());
                        profil.setNama(nprof.getNama());
                        profil.setAge(nprof.getAge());
                        profil.setAddress(nprof.getAddress());
                        profil.setMembership(nprof.getMembership());
                        Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ProfilListResponse> call, Throwable t) {
                Toast.makeText(context,"Failed to connect to Web API!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onProfileEditClicked() {
        if(adminState.getAdminState() != true) {
            //TODO: Launch Activity Edit User di Sini (Edit dan Delete), User admin tak bisa di edit!
            Intent intent = new Intent(getActivity(), EditProfilFragment.class);
            startActivity(intent);
        }else{
            Context context = getContext();
            Toast.makeText(context, "You cannot change admin info!", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        changeFragment(new ProfilFragment());
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