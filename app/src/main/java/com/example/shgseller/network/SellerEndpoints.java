package com.example.shgseller.network;

import com.example.shgseller.models.Seller;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SellerEndpoints {
    @GET("/seller")
    Call<List<Seller> > getAllSellers();


}
