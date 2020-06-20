package com.example.hospitalsearch;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Geolocation {
    public static void getAddress(final String locationAddress, final Context context , final Handler handler)
    {
        Thread thread=new Thread()
        {
            @Override
            public void run() {
                Geocoder geocoder=new Geocoder(context, Locale.getDefault());
                String result=null;
                try {
                    List addresslist=geocoder.getFromLocationName(locationAddress,1);
                    if(addresslist!=null&&addresslist.size()>0)
                    {
                        Address address=(Address)  addresslist.get(0);
                        StringBuilder stringBuilder=new StringBuilder();
                        stringBuilder.append(address.getLatitude()).append("\n");
                        stringBuilder.append(address.getLongitude()).append("\n");
                        result=stringBuilder.toString();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    Message messege=Message.obtain();
                    messege.setTarget(handler);
                    if(result!=null)
                    {
                        messege.what=1;
                        Bundle bundle=new Bundle();
                        result="Adress   :    "+locationAddress+"\n\n\nLatitude and longitude \n"+result;
                        bundle.putString("adress",result);
                        messege.setData(bundle);
                    }
                    messege.sendToTarget();
                }
            }
        };
        thread.start();
    }
}
