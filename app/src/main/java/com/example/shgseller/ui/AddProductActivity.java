package com.example.shgseller.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.example.shgseller.Constants;
import com.example.shgseller.MainActivity;
import com.example.shgseller.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.textInputEditProductCategory) TextInputEditText textInputEditTextProductCategory;


    @BindView(R.id.buttonAddProduct) MaterialButton materialButtonAddProduct;

    @BindView(R.id.buttonAddImage) MaterialButton materialButtonAddImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);

        materialButtonAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker imagePicker = ImagePicker.create(AddProductActivity.this) // Activity or Fragment
                        .limit(1);

                imagePicker.start();

            }
        });




        materialButtonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(getApplicationContext(),"Will add product",Toast.LENGTH_SHORT).show();
            }
        });

    }
    
    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // Get a list of picked images
//            List<Image> images = ImagePicker.getImages(data)
            // or get a single image only
            Image image = ImagePicker.getFirstImageOrNull(data);
            Glide.with(imageViewProductImage)
                    .load(image.getPath())
                    .into(imageViewProductImage);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
