package com.example.hospitalsearch.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hospitalsearch.PatientActivity;
import com.example.hospitalsearch.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button patientButton=findViewById(R.id.patientButton);
        Button hospitalButton=findViewById(R.id.hospitalButton);
        hospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),LoginActivity.class);
                startActivity(intent);
            }
        });


        patientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), PatientActivity.class);
                startActivity(intent);
            }
        });
    }


}