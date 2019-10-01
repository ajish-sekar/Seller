package com.example.shgseller.models;

import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("line_one")
    private String line_one;
    @SerializedName("line_two")
    private String line_two;
    @SerializedName("landmark")
    private String landmark;
    @SerializedName("district")
    private String district;
    @SerializedName("city")
    private String city;
    @SerializedName("pincode")
    private String pincode;

    public Address(String line_one, String line_two, String landmark, String district, String city, String pincode) {
        this.line_one = line_one;
        this.line_two = line_two;
        this.landmark = landmark;
        this.district = district;
        this.city = city;
        this.pincode = pincode;
    }

    public String getLine_one() {
        return line_one;
    }

    public void setLine_one(String line_one) {
        this.line_one = line_one;
    }

    public String getLine_two() {
        return line_two;
    }

    public void setLine_two(String line_two) {
        this.line_two = line_two;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
