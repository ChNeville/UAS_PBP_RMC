package com.example.uas_pbp_rmc;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.example.uas_pbp_rmc.databinding.ActivityQrpageBinding;

public class QRPageActivity extends AppCompatActivity {
    private ActivityQrpageBinding binding;
    private final ActivityResultLauncher<Intent> cameraResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                try {
                                    //Res



                                } catch (Exception e) {
                                    //Exe


                                }
                            }
                        }
                    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQrpageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnScan.setOnClickListener(v -> {
            cameraResult.launch(new Intent(this, QRScannerActivity.class));
        });
    }
}