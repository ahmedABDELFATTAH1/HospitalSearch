package com.example.hospitalsearch;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import java.util.ArrayList;

public class HospitalAdapter extends ArrayAdapter {
    public HospitalAdapter(Context context , ArrayList<HospitalModel> hosptalList)
    {
        super(context,0,hosptalList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HospitalModel hospital_data = (HospitalModel) getItem(position);
        View ListItemView = convertView;
        if(ListItemView == null)
        {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.hospital_ticket,parent,false);

        }
        TextView hos_name = (TextView)ListItemView.findViewById(R.id.hos_name);
        hos_name.setText(hospital_data.getName());

        TextView hos_address = (TextView)ListItemView.findViewById(R.id.hos_address);
        hos_address.setText("Address : "+hospital_data.getAddress());

        TextView hos_phone=(TextView)ListItemView.findViewById(R.id.hos_phone);
        hos_phone.setText("phone : "+hospital_data.getPhone());

        TextView hos_free_high=(TextView)ListItemView.findViewById(R.id.hos_free_high);
        hos_free_high.setText("Free Slots : "+hospital_data.getFree_high());

        TextView hos_free_med=(TextView)ListItemView.findViewById(R.id.hos_free_med);
        hos_free_med.setText("Free Slots : "+hospital_data.getFree_med());

        TextView hos_free_low=(TextView)ListItemView.findViewById(R.id.hos_free_low);
        hos_free_low.setText("Free Slots : "+hospital_data.getFree_low());


        TextView hos_price_high=(TextView)ListItemView.findViewById(R.id.hos_price_high);
        hos_price_high.setText("Price : "+hospital_data.getPrice_high());

        TextView hos_price_med=(TextView)ListItemView.findViewById(R.id.hos_price_med);
        hos_price_med.setText("Price : "+hospital_data.getPrice_med());

        TextView hos_price_low=(TextView)ListItemView.findViewById(R.id.hos_price_low);
        hos_price_low.setText("Price : "+hospital_data.getPrice_low());

        return  ListItemView;
    }

}

