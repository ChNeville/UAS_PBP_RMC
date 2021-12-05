package com.example.uas_pbp_rmc.state;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

public class AdminState {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public AdminState(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("adminState",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setAdminState(Boolean state){
        editor.clear();
        editor.putBoolean("admin",state);
        editor.commit();
    }

    public Boolean getAdminState(){
       return sharedPreferences.getBoolean("data", false);
    }
}
