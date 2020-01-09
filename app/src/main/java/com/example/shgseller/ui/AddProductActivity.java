package com.example.shgseller.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.InetAddresses;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;
import com.example.shgseller.Constants;
import com.example.shgseller.HomeActivity;
import com.example.shgseller.MainActivity;
import com.example.shgseller.R;
import com.example.shgseller.models.Product;
import com.example.shgseller.network.ProductEndpoints;
import com.example.shgseller.network.RetrofitClient;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.HTTP;

public class AddProductActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int GALLERY_REQUEST_CODE = 2;
    @BindView(R.id.imageViewProductImage) ImageView imageViewProductImage;
    @BindView(R.id.textInputLayoutProductName) TextInputLayout textInputLayoutProductName;
    @BindView(R.id.textInputLayoutProductPrice) TextInputLayout textInputLayoutProductPrice;
    @BindView(R.id.textInputLayoutProductDescription) TextInputLayout textInputLayoutProductDescription;
    @BindView(R.id.textInputLayoutProductCategory) TextInputLayout textInputLayoutProductCategory;

    @BindView(R.id.textInputEditProductName) TextInputEditText textInputEditTextProductName;
    @BindView(R.id.textInputEditProductPrice) TextInputEditText textInputEditTextProductPrice;
    @BindView(R.id.textInputEditProductDescription) TextInputEditText textInputEditTextProductDescription;
//    @BindView(R.id.textInputEditProductCategory) TextInputEditText textInputEditTextProductCategory;
    @BindView(R.id.spinnerProductCategories) Spinner spinnerProductCategories;
    @BindView(R.id.textViewImageErrorMessage) TextView textViewImageErrorMessage;

    @BindView(R.id.buttonAddProduct) MaterialButton materialButtonAddProduct;
    @BindView(R.id.buttonAddImage) MaterialButton materialButtonAddImage;

    private Image image = null;
    private ProductEndpoints productEndpoints;
    private String DEFAULT_SELLER = "112233";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);

        materialButtonAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker imagePicker = ImagePicker.create(AddProductActivity.this)
                        .returnMode(ReturnMode.ALL)// Activity or Fragment
                        .single();

                imagePicker.start();

            }
        });


        materialButtonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });

    }

    public void addProduct(){
        String productName = textInputEditTextProductName.getText().toString();
        if(productName.isEmpty()){
            textInputLayoutProductName.setError("Please enter product name");
            return;
        }else{
            textInputLayoutProductName.setError(null);
        }

        String productPrice = textInputEditTextProductPrice.getText().toString();
        if(productPrice.isEmpty()){
            textInputLayoutProductPrice.setError("Please enter product price");
            return;
        }else {
            textInputLayoutProductPrice.setError(null);
        }
//        String productCategory = textInputEditTextProductCategory.getText().toString();
        String productCategory = spinnerProductCategories.getSelectedItem().toString();

        if(productCategory.isEmpty()){
            textInputLayoutProductCategory.setError("Please choose Product category");
            return;
        }else{
            textInputLayoutProductCategory.setError(null);
        }

        String productDescription = textInputEditTextProductDescription.getText().toString();
        if(productDescription.isEmpty()){
            textInputLayoutProductDescription.setError("Please enter product description");
            return;
        }else{
            textInputLayoutProductDescription.setError(null);
        }
        if(image == null){
            textViewImageErrorMessage.setVisibility(View.VISIBLE);
            return;
        }else{
            textViewImageErrorMessage.setVisibility(View.INVISIBLE);
        }

        productEndpoints = RetrofitClient.getRetrofitInstance().create(ProductEndpoints.class);

        File file = new File(image.getPath());
        Log.v("FILE",file.getAbsolutePath());
        RequestBody fileToUpload = RequestBody.create(MediaType.parse("*/*"),file);
        RequestBody productNameBody = RequestBody.create(MediaType.parse("multitype/form-data"),productName);
        RequestBody productCategoryBody = RequestBody.create(MediaType.parse("multitype/form-data"),productCategory);
        RequestBody productDescriptionBody = RequestBody.create(MediaType.parse("multitype/form-data"),productDescription);
        RequestBody productPriceBody = RequestBody.create(MediaType.parse("multitype/form-data"),productPrice);
        RequestBody seller = RequestBody.create(MediaType.parse("multitype/form-data"),DEFAULT_SELLER);


        Call<Product> call = productEndpoints.addProduct(fileToUpload,productNameBody,productPriceBody,productCategoryBody,productDescriptionBody,seller);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                int responseCode = response.code();
                if (responseCode == HttpURLConnection.HTTP_CREATED){
                    Toast.makeText(getApplicationContext(),"Added new product.",Toast.LENGTH_LONG).show();

                }
                Log.v("TAG","CODE : "+response.code());

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Unable to add product",Toast.LENGTH_SHORT).show();
                Log.v("TAG","ERROR MSG : "+t.getMessage());
            }
        });
    }
    
    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // Get a list of picked images
//            List<Image> images = ImagePicker.getImages(data)
            // or get a single image only
            image = ImagePicker.getFirstImageOrNull(data);
            Glide.with(imageViewProductImage)
                    .load(image.getPath())
                    .into(imageViewProductImage);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
