package com.example.uas_pbp_rmc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uas_pbp_rmc.model.ProductItem;
import com.example.uas_pbp_rmc.webapi.ApiServer;
import com.example.uas_pbp_rmc.webapi.ProductListResponse;
import com.example.uas_pbp_rmc.webapi.ProductResponse;
import com.example.uas_pbp_rmc.webapi.ApiWebProduct;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class ProductEditActivity extends AppCompatActivity {

    private ApiWebProduct apiService;

    private EditText urlImageEDT;
    private EditText namaEDT;
    private EditText specEDT;
    private EditText pricingEDT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_edit);

        // TODO : Data passing dari Home ke ProductEdit

        apiService = ApiServer.getClient().create(ApiWebProduct.class);

        urlImageEDT = findViewById(R.id.product_edit_url);
        namaEDT = findViewById(R.id.product_edit_name);
        specEDT = findViewById(R.id.product_edit_spec);
        pricingEDT = findViewById(R.id.product_edit_pricing);

        Button btnEdit = findViewById(R.id.product_edit_action);
        TextView tvTitle = findViewById(R.id.tv_title);
        long id = getIntent().getLongExtra("id", -1);

        if (id < 0) {
            tvTitle.setText("Tambah Product");

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    createProduct();
                }
            });
            btnEdit.setText("Tambah");
        } else {
            tvTitle.setText("Edit Product");
            getProductById(id);

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateProduct(id);
                }
            });
        }
    }

    private void getProductById(long id) {
        Call<ProductResponse> call = apiService.getProductById(id);
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call,
                                   Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    ProductItem productList = response.body().getProduct();

                    urlImageEDT.setText(productList.getImageURL());
                    namaEDT.setText(productList.getProductName());
                    specEDT.setText(productList.getProductInfo());
                    pricingEDT.setText(String.valueOf(productList.getPrice()));
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ProductEditActivity.this,
                                jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(ProductEditActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(ProductEditActivity.this,
                        "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createProduct() {
        ProductItem product = new ProductItem(
                0,
                namaEDT.getText().toString(),
                pricingEDT.getText().toString().isEmpty() ? null
                        : Float.parseFloat(pricingEDT.getText().toString()),
                specEDT.getText().toString(),
                urlImageEDT.getText().toString());

        Call<ProductResponse> call = apiService.createProduct(product);
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call,
                                   Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ProductEditActivity.this,
                            response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ProductEditActivity.this,
                                jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(ProductEditActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(ProductEditActivity.this,
                        "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateProduct(long id) {
        ProductItem product = new ProductItem(
                0,
                namaEDT.getText().toString(),
                pricingEDT.getText().toString().isEmpty() ? null
                        : Float.parseFloat(pricingEDT.getText().toString()),
                specEDT.getText().toString(),
                urlImageEDT.getText().toString());

        Call<ProductResponse> call = apiService.updateProduct(id, product);
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call,
                                   Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ProductEditActivity.this,
                            response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ProductEditActivity.this,
                                jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(ProductEditActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(ProductEditActivity.this,
                        "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}