package com.example.uas_pbp_rmc;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uas_pbp_rmc.databinding.FragmentProfilBinding;

public class EditProfilFragment extends AppCompatActivity{//fragment

    EditText etMembership, etName, etUsername, etAge, etAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit_profil);

        viewInitializations();
    }

    void viewInitializations() {
        etMembership = findViewById(R.id.edtmembership);
        etUsername = findViewById(R.id.edtusername);
        etName = findViewById(R.id.edtnama);
        etAge = findViewById(R.id.edtage);
        etAddress = findViewById(R.id.edtaddress);

        //actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    boolean validateInput() {
        if (etMembership.getText().toString().equals("")) {
            etMembership.setError("Please Enter Your Membership");
            return false;
        }
        if (etUsername.getText().toString().equals("")) {
            etUsername.setError("Please Enter Your Username");
            return false;
        }
        if (etName.getText().toString().equals("")) {
            etName.setError("Please Enter Your Name");
            return false;
        }
        if (etAge.getText().toString().equals("")) {
            etAge.setError("Please Enter Your Age");
            return false;
        }
        if (etAddress.getText().toString().equals("")) {
            etAddress.setError("Please Enter Your Address");
            return false;
        }
        return true;
    }

    public void performEditProfile (View v) {
        if (validateInput()) {

            //Data Send
            String membership = etMembership.getText().toString();
            String username = etUsername.getText().toString();
            String name = etName.getText().toString();
            String age = etAge.getText().toString();
            String address = etAddress.getText().toString();

            Toast.makeText(this,"Profile Update Successfully",Toast.LENGTH_SHORT).show();


        }
    }

}