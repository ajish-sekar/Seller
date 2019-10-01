package com.example.shgseller.models;

import com.google.gson.annotations.SerializedName;

public class Seller {
    @SerializedName("seller_account")
    private String seller_account;
    @SerializedName("seller_name")
    private String seller_name;
    @SerializedName("seller_contact")
    private String seller_contact;
    @SerializedName("seller_address")
    private Address seller_address;

    public Seller(String seller_account, String seller_name, String seller_contact, Address seller_address) {
        this.seller_account = seller_account;
        this.seller_name = seller_name;
        this.seller_contact = seller_contact;
        this.seller_address = seller_address;
    }

    public String getSeller_account() {
        return seller_account;
    }

    public void setSeller_account(String seller_account) {
        this.seller_account = seller_account;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getSeller_contact() {
        return seller_contact;
    }

    public void setSeller_contact(String seller_contact) {
        this.seller_contact = seller_contact;
    }

    public Address getSeller_address() {
        return seller_address;
    }

    public void setSeller_address(Address seller_address) {
        this.seller_address = seller_address;
    }
}
