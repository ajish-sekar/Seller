package com.example.shgseller.models;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("product_id")
    private String product_id;
    @SerializedName("product_title")
    private String product_title;
    @SerializedName("product_price")
    private float product_price;
    @SerializedName("product_description")
    private String product_description;
    @SerializedName("product_category")
    private String product_category;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public float getProduct_price() {
        return product_price;
    }

    public void setProduct_price(float product_price) {
        this.product_price = product_price;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }
}
