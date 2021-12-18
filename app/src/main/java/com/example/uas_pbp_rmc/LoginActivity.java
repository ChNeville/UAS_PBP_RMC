package com.example.uas_pbp_rmc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.uas_pbp_rmc.state.AdminState;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    AdminState adminState;
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
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void setRegistration(){
        mAuth.createUserWithEmailAndPassword(
                emailInput.getText().toString(),
                passInput.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Signup Sukses, Cek Email Utk Konfirmasi",Toast.LENGTH_SHORT).show();
                            determineLogin(false);
                        } else {
                            Toast.makeText(LoginActivity.this,"Signup Gagal",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}