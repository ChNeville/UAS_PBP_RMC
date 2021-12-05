package com.example.uas_pbp_rmc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.uas_pbp_rmc.model.ProductItem;

public class ProductEditActivity extends AppCompatActivity {

    Intent intent;

    String activityContext = "EDIT";

    ProductItem dataSet;

    EditText urlImageEDT;
    EditText namaEDT;
    EditText specEDT;
    EditText pricingEDT;
    Button editActionBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_edit);
        intent = getIntent();

        // TODO : Data passing dari Home ke ProductEdit
        activityContext = intent.getStringExtra("context");
        int passedDBI = intent.getIntExtra("index", 0);

        if(passedDBI != -1){

        }

        urlImageEDT = findViewById(R.id.product_edit_url);
        namaEDT = findViewById(R.id.product_edit_name);
        specEDT = findViewById(R.id.product_edit_spec);
        pricingEDT = findViewById(R.id.product_edit_pricing);
        editActionBT = findViewById(R.id.product_edit_action);

        switch(activityContext){
            case "EDIT":{
                // Insert data yang sudah di load
                urlImageEDT.setText(dataSet.getImageURL());
                namaEDT.setText(dataSet.getProductName());
                specEDT.setText(dataSet.getProductInfo());
                pricingEDT.setText(String.valueOf(dataSet.getPrice()));
                break;
            }
            case "ADD":{
                editActionBT.setText("ADD");
                break;
            }
            default:break;
        }
    }
}