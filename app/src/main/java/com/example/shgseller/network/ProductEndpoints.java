package com.example.shgseller.network;

import com.example.shgseller.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductEndpoints {
    @GET("/products")
    Call<List<Product>> getAllProducts();
}
