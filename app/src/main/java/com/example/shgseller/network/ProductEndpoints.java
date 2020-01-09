package com.example.shgseller.network;

import com.example.shgseller.models.Product;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ProductEndpoints {
    @GET("/products")
    Call<List<Product>> getAllProducts();

    @Multipart
    @POST("/products/")
    Call<Product> addProduct(
            @Part("product_photo\"; filename=\"pp.png\" ") RequestBody file,
            @Part("product_title") RequestBody productTitle,
            @Part("product_price") RequestBody productPrice,
            @Part("product_category") RequestBody productCategory,
            @Part("product_description") RequestBody productDescription,
            @Part("seller") RequestBody seller
    );
}
