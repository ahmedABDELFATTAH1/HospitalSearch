package com.example.hospitalsearch;





import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class Urls {

    public static final String ROOT = "http://f75115ef5c42.ngrok.io";



    public static String gethospitals(String xloc,String yloc,String type) {
        return ROOT + "/find_hospitals_by_location?xloc="+ xloc+"& yloc="+yloc+"&type= "+type;
    }

}