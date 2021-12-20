package com.example.uas_pbp_rmc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.uas_pbp_rmc.model.Profil;
import com.example.uas_pbp_rmc.state.AdminState;
import com.example.uas_pbp_rmc.webapi.ApiServer;
import com.example.uas_pbp_rmc.webapi.ApiWebProfil;
import com.example.uas_pbp_rmc.webapi.ProfilListResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    AdminState adminState;
    ApiWebProfil apiService;
    private FirebaseAuth mAuth;

    String UIContext = "LOGIN";

    EditText emailInput;
    EditText userInput;
    EditText passInput;
    Button loginAction;
    Switch signSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            finish();
        }

        apiService = ApiServer.getClient().create(ApiWebProfil.class);

        adminState = new AdminState(this);
        mAuth = FirebaseAuth.getInstance();

        emailInput = findViewById(R.id.loginMail);
        userInput = findViewById(R.id.loginUsername);
        passInput = findViewById(R.id.loginPassword);
        loginAction = findViewById(R.id.loginAction);
        signSwitch = findViewById(R.id.loginSwitch);

        signSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                determineLogin(isChecked);
            }
        });

        loginAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(UIContext){
                    case "LOGIN":{
                        setLogin();
                        break;
                    }
                    case "SIGNUP":{
                        setRegistration();
                        break;
                    }
                    default:break;
                }
            }
        });

        userInput.setVisibility(View.GONE);
    }

    private void determineLogin(Boolean state){
        if(state == true){
            UIContext = "SIGNUP";
            loginAction.setText("sign up");
            userInput.setVisibility(View.VISIBLE);
        } else {
            UIContext = "LOGIN";
            loginAction.setText("login");
            userInput.setVisibility(View.GONE);
        }
    }

    private void setLogin(){
        String emailname = emailInput.getText().toString();
        String password = passInput.getText().toString();
        if(emailname.equals("admin@mail.com") && password.equals("admin")){ // TODO : Kalau demonstrasi inget email dan password utk admin
            adminState.setAdminState(true);
            Toast.makeText(LoginActivity.this, "Admin Mode Dinyalakan", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            mAuth.signInWithEmailAndPassword(
                    emailname,
                    password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login Sukses", Toast.LENGTH_SHORT).show();

                                Intent returnIntent = new Intent();
                                setResult(Activity.RESULT_OK, returnIntent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void setRegistration(){
        Context ctx = this;

        String emailname = emailInput.getText().toString();
        String username = userInput.getText().toString();
        String password = passInput.getText().toString();
        mAuth.createUserWithEmailAndPassword(
                emailname, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            registerUserData(ctx, emailname, username);
                            mAuth.signOut();
                            determineLogin(false);
                        } else {
                            Toast.makeText(LoginActivity.this,"Signup Gagal",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void registerUserData(Context ctx, String emailname, String username){
        Profil profil = new Profil(emailname, username);
        Call<ProfilListResponse> call = apiService.createProfil(profil);
        call.enqueue(new Callback<ProfilListResponse>() {
            @Override
            public void onResponse(Call<ProfilListResponse> call, Response<ProfilListResponse> response) {
                if(response.body() != null){
                    if (response.isSuccessful()){
                        Toast.makeText(ctx,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ctx,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ProfilListResponse> call, Throwable t) {
                Toast.makeText(ctx,"Failed to connect to Web API!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}