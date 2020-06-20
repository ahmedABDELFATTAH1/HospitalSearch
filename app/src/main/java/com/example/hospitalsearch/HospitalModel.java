package com.example.hospitalsearch;

public class HospitalModel {
    String name=null;
    String address=null;
    String phone=null;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getFree_high() {
        return free_high;
    }

    public String getPrice_high() {
        return price_high;
    }

    public String getFree_med() {
        return free_med;
    }

    public String getPrice_med() {
        return price_med;
    }

    public String getFree_low() {
        return free_low;
    }

    public String getPrice_low() {
        return price_low;
    }

    String free_high=null;
    String price_high=null;

    public HospitalModel(String name, String address, String phone, String free_high, String price_high, String free_med, String price_med, String free_low, String price_low) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.free_high = free_high;
        this.price_high = price_high;
        this.free_med = free_med;
        this.price_med = price_med;
        this.free_low = free_low;
        this.price_low = price_low;
    }

    String free_med=null;
    String price_med=null;
    String free_low=null;
    String price_low=null;

}
