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

import com.example.uas_pbp_rmc.controller.ProfilViewClickListener;
import com.example.uas_pbp_rmc.databinding.FragmentProfilBinding;
import com.example.uas_pbp_rmc.model.Profil;
import com.example.uas_pbp_rmc.state.AdminState;
import com.example.uas_pbp_rmc.webapi.ApiServer;
import com.example.uas_pbp_rmc.webapi.ApiWebProduct;
import com.example.uas_pbp_rmc.webapi.ApiWebProfil;
import com.example.uas_pbp_rmc.webapi.ProductListResponse;
import com.example.uas_pbp_rmc.webapi.ProfilResponse;
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

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null && adminState.getAdminState() != true){
            username = currentUser.getEmail();
            loadUserData();
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

    private void loadUserData(){
        Context context = getContext();

        Call<ProfilResponse> call = apiService.getProfilByUsername(username);

        call.enqueue(new Callback<ProfilResponse>() {
            @Override
            public void onResponse(Call<ProfilResponse> call, Response<ProfilResponse> response) {
                if (response.isSuccessful()){
                    profil = response.body().getProfil();
                    Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ProfilResponse> call, Throwable t) {
                Toast.makeText(context,"Failed to connect to Web API!",Toast.LENGTH_SHORT).show();
            }
        });
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