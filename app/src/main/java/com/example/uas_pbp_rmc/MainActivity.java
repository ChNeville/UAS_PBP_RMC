package com.example.uas_pbp_rmc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {
    FragmentContainerView fragmentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeFragment(new HomeFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_view);
        bottomNavigationView.setOnItemSelectedListener(this);

        setTitle(R.string.view_title_home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                setTitle(R.string.view_title_home);
                break;
            case R.id.nav_cart:
                fragment = new CartFragment();
                setTitle(R.string.view_title_cart);
                break;
            case R.id.nav_profile:
                fragment = new ProfilFragment();
                setTitle(R.string.view_title_profil);
                break;
        }
        return changeFragment(fragment);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (24) : {
                if(resultCode == Activity.RESULT_OK) {
                    changeFragment(new CartFragment());
                    setTitle(R.string.view_title_cart);
                }
                break;
            }
        }
    }

    public boolean changeFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, fragment)
                    .commit();
            return true;
        }return false;
    }
}