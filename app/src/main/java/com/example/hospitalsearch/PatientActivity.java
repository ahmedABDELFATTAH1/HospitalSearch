package com.example.hospitalsearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PatientActivity extends AppCompatActivity {
    Button btnlocation = null;
    FusedLocationProviderClient fusedLocationProviderClient;
    ArrayList<HospitalModel> hospitalData=null;
    ArrayAdapter<HospitalAdapter> adapter;
    ListView list=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        btnlocation=findViewById(R.id.get_location);
        hospitalData=new ArrayList<>();
        JSONObject root = null;
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        adapter= new HospitalAdapter(this,hospitalData);
        list = findViewById(R.id.hospital_adapter_ui);
        list.setAdapter(adapter);

        btnlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(PatientActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            Location location=task.getResult();
                            if(location !=null)
                            {
                                try {
                                    Geocoder geocoder=new Geocoder(PatientActivity.this, Locale.getDefault());
                                    List<Address> addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

                                    String xloc=String.valueOf(addresses.get(0).getLatitude());
                                    String yloc=String.valueOf(addresses.get(0).getLongitude());
                                    String type="medium";
                                        Urls urlController = new Urls();
                                        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
                                        String url = Urls.gethospitals(xloc,yloc,type);
                                        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                                                url,
                                                new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {
                                                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                                                        try {
                                                            JSONObject root=new JSONObject(response);
                                                            addHospitals(root);

                                                        } catch (JSONException e) {

                                                        }

                                                    }
                                                },
                                                new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {
                                                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                                                        try {
                                                            JSONObject root = getmemicdata();
                                                            addHospitals(root);
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }

                                                    }
                                                }) ;

                                        // Add the request to the RequestQueue.
                                        queue.add(stringRequest);


                                } catch (IOException e) {

                                }


                            }


                        }
                    });
                } else {
                    ActivityCompat.requestPermissions(PatientActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            44);
                }


            }
        });


    }

    private JSONObject getmemicdata() throws JSONException {

            return new JSONObject("{\"hospitals\":[{\"id\":21,\"username\":\"nakia68@example.org\",\"name\":\"Corwin-Jaskolski\",\"address_location\":\"400 Hartmann Brook Suite 418\\nNorth Londonland, NM 14073\",\"x_location\":8.3,\"y_location\":7.9,\"free_slots_high\":4,\"free_slots_medium\":75,\"free_slots_low\":48,\"price_high\":7619,\"price_medium\":1414,\"price_low\":438,\"created_at\":\"2020-06-19T14:34:39.000000Z\",\"updated_at\":\"2020-06-19T14:34:39.000000Z\",\"phones\":[],\"distance\":4.110960958218892},{\"id\":3,\"username\":\"luettgen.jannie@example.org\",\"name\":\"Carroll Inc\",\"address_location\":\"4089 Kautzer Row Suite 745\\nWolfbury, MA 25940\",\"x_location\":24.6,\"y_location\":1.5,\"free_slots_high\":8,\"free_slots_medium\":23,\"free_slots_low\":24,\"price_high\":5668,\"price_medium\":819,\"price_low\":751,\"created_at\":\"2020-06-19T13:25:47.000000Z\",\"updated_at\":\"2020-06-19T13:25:47.000000Z\",\"phones\":[],\"distance\":16.58945448168806},{\"id\":19,\"username\":\"trinity71@example.net\",\"name\":\"Bruen PLC\",\"address_location\":\"5687 Murphy Plaza\\nShanahanton, CA 29538\",\"x_location\":12.6,\"y_location\":41.4,\"free_slots_high\":193,\"free_slots_medium\":66,\"free_slots_low\":21,\"price_high\":9742,\"price_medium\":1270,\"price_low\":461,\"created_at\":\"2020-06-19T14:34:39.000000Z\",\"updated_at\":\"2020-06-19T14:34:39.000000Z\",\"phones\":[],\"distance\":30.442076144704714},{\"id\":2,\"username\":\"kohler.hardy@example.com\",\"name\":\"Gibson, Nicolas and Ryan\",\"address_location\":\"9461 Rath Villages\\nGustshire, MD 69850\",\"x_location\":47.2,\"y_location\":21.1,\"free_slots_high\":120,\"free_slots_medium\":94,\"free_slots_low\":3,\"price_high\":6205,\"price_medium\":1474,\"price_low\":677,\"created_at\":\"2020-06-19T13:25:47.000000Z\",\"updated_at\":\"2020-06-19T13:25:47.000000Z\",\"phones\":[],\"distance\":37.5825757499403},{\"id\":22,\"username\":\"brett99@example.com\",\"name\":\"Koepp, Goldner and Pollich\",\"address_location\":\"174 Carroll Row\\nNorth Caterina, MI 32749\",\"x_location\":5.1,\"y_location\":52.8,\"free_slots_high\":14,\"free_slots_medium\":52,\"free_slots_low\":47,\"price_high\":9225,\"price_medium\":1496,\"price_low\":490,\"created_at\":\"2020-06-19T14:34:40.000000Z\",\"updated_at\":\"2020-06-19T14:34:40.000000Z\",\"phones\":[],\"distance\":42.21433405846881},{\"id\":15,\"username\":\"conroy.rex@example.com\",\"name\":\"Fadel PLC\",\"address_location\":\"87825 Feil Square Apt. 151\\nStokesport, ND 39077\",\"x_location\":53.7,\"y_location\":23.3,\"free_slots_high\":115,\"free_slots_medium\":6,\"free_slots_low\":13,\"price_high\":5344,\"price_medium\":1878,\"price_low\":629,\"created_at\":\"2020-06-19T14:34:38.000000Z\",\"updated_at\":\"2020-06-19T14:34:38.000000Z\",\"phones\":[],\"distance\":44.436246466145185},{\"id\":12,\"username\":\"hodkiewicz.eleanora@example.net\",\"name\":\"D'Amore-Gutkowski\",\"address_location\":\"6102 Devonte Wall Suite 861\\nPort Ellen, MD 97429\",\"x_location\":52.2,\"y_location\":32.1,\"free_slots_high\":154,\"free_slots_medium\":47,\"free_slots_low\":34,\"price_high\":2698,\"price_medium\":1886,\"price_low\":433,\"created_at\":\"2020-06-19T13:25:48.000000Z\",\"updated_at\":\"2020-06-19T13:25:48.000000Z\",\"phones\":[],\"distance\":46.28876753598005},{\"id\":1,\"username\":\"walter.lesly@example.net\",\"name\":\"Brown-Mayert\",\"address_location\":\"1087 Hettie Garden Apt. 162\\nSouth Helenberg, OH 30915-4464\",\"x_location\":61.4,\"y_location\":8.3,\"free_slots_high\":123,\"free_slots_medium\":11,\"free_slots_low\":46,\"price_high\":2450,\"price_medium\":613,\"price_low\":296,\"created_at\":\"2020-06-19T13:25:47.000000Z\",\"updated_at\":\"2020-06-19T13:25:47.000000Z\",\"phones\":[],\"distance\":50.472269614115824},{\"id\":24,\"username\":\"wemard@example.org\",\"name\":\"Bednar Ltd\",\"address_location\":\"4807 Lew Isle Apt. 941\\nNew Carmella, NM 13958\",\"x_location\":55.4,\"y_location\":45,\"free_slots_high\":200,\"free_slots_medium\":35,\"free_slots_low\":25,\"price_high\":2758,\"price_medium\":1065,\"price_low\":942,\"created_at\":\"2020-06-19T14:34:40.000000Z\",\"updated_at\":\"2020-06-19T14:34:40.000000Z\",\"phones\":[],\"distance\":55.92280393542512},{\"id\":11,\"username\":\"randal23@example.net\",\"name\":\"Carroll-Kertzmann\",\"address_location\":\"323 Lueilwitz Way\\nLake Creolachester, GA 43191\",\"x_location\":49.9,\"y_location\":58.6,\"free_slots_high\":183,\"free_slots_medium\":71,\"free_slots_low\":38,\"price_high\":4905,\"price_medium\":1391,\"price_low\":193,\"created_at\":\"2020-06-19T13:25:48.000000Z\",\"updated_at\":\"2020-06-19T13:25:48.000000Z\",\"phones\":[],\"distance\":61.47332754943399},{\"id\":16,\"username\":\"alberta28@example.net\",\"name\":\"Bogan, Spencer and Graham\",\"address_location\":\"156 Mandy Hill\\nLake Sharon, RI 89882\",\"x_location\":7.3,\"y_location\":74.4,\"free_slots_high\":135,\"free_slots_medium\":72,\"free_slots_low\":27,\"price_high\":1366,\"price_medium\":840,\"price_low\":635,\"created_at\":\"2020-06-19T14:34:39.000000Z\",\"updated_at\":\"2020-06-19T14:34:39.000000Z\",\"phones\":[],\"distance\":63.507873527618614},{\"id\":17,\"username\":\"gwendolyn58@example.com\",\"name\":\"Ledner, Crooks and Wiegand\",\"address_location\":\"348 Adeline Mission Suite 533\\nPort Samanta, MT 02357-3149\",\"x_location\":74.6,\"y_location\":6.5,\"free_slots_high\":197,\"free_slots_medium\":36,\"free_slots_low\":9,\"price_high\":4053,\"price_medium\":787,\"price_low\":139,\"created_at\":\"2020-06-19T14:34:39.000000Z\",\"updated_at\":\"2020-06-19T14:34:39.000000Z\",\"phones\":[],\"distance\":63.75899936479555},{\"id\":5,\"username\":\"gusikowski.retta@example.com\",\"name\":\"Toy-Ryan\",\"address_location\":\"1330 Randi Roads\\nEast Dee, MS 46720-2705\",\"x_location\":61.9,\"y_location\":49.5,\"free_slots_high\":26,\"free_slots_medium\":68,\"free_slots_low\":43,\"price_high\":6518,\"price_medium\":1287,\"price_low\":763,\"created_at\":\"2020-06-19T13:25:47.000000Z\",\"updated_at\":\"2020-06-19T13:25:47.000000Z\",\"phones\":[],\"distance\":63.82052961234339},{\"id\":14,\"username\":\"roxane.deckow@example.net\",\"name\":\"Donnelly-Gislason\",\"address_location\":\"122 Emmerich Plains\\nPort Brennon, OH 60100\",\"x_location\":71.8,\"y_location\":33.3,\"free_slots_high\":176,\"free_slots_medium\":85,\"free_slots_low\":23,\"price_high\":9912,\"price_medium\":1724,\"price_low\":922,\"created_at\":\"2020-06-19T14:34:38.000000Z\",\"updated_at\":\"2020-06-19T14:34:38.000000Z\",\"phones\":[],\"distance\":64.76055898461655},{\"id\":23,\"username\":\"hadley.langosh@example.com\",\"name\":\"O'Connell and Sons\",\"address_location\":\"27873 Gorczany Path Suite 595\\nArmstrongtown, VA 34677-9210\",\"x_location\":45.2,\"y_location\":67,\"free_slots_high\":24,\"free_slots_medium\":4,\"free_slots_low\":19,\"price_high\":8557,\"price_medium\":1054,\"price_low\":428,\"created_at\":\"2020-06-19T14:34:40.000000Z\",\"updated_at\":\"2020-06-19T14:34:40.000000Z\",\"phones\":[],\"distance\":65.61737574758686},{\"id\":13,\"username\":\"kenna.rau@example.com\",\"name\":\"Howell, Conroy and Quigley\",\"address_location\":\"169 Waters Spurs\\nCieloview, MI 26934-3689\",\"x_location\":3.8,\"y_location\":78.4,\"free_slots_high\":77,\"free_slots_medium\":56,\"free_slots_low\":34,\"price_high\":9198,\"price_medium\":1304,\"price_low\":798,\"created_at\":\"2020-06-19T14:34:38.000000Z\",\"updated_at\":\"2020-06-19T14:34:38.000000Z\",\"phones\":[],\"distance\":67.78347881305592},{\"id\":9,\"username\":\"cleo.ernser@example.net\",\"name\":\"Hill, Stamm and Schuster\",\"address_location\":\"51897 Lera Road\\nNorth Thaliatown, MT 94461\",\"x_location\":79,\"y_location\":20.8,\"free_slots_high\":19,\"free_slots_medium\":51,\"free_slots_low\":46,\"price_high\":5832,\"price_medium\":1557,\"price_low\":650,\"created_at\":\"2020-06-19T13:25:48.000000Z\",\"updated_at\":\"2020-06-19T13:25:48.000000Z\",\"phones\":[],\"distance\":68.70254725990878},{\"id\":8,\"username\":\"maryse23@example.org\",\"name\":\"Kunze-Stanton\",\"address_location\":\"72216 Kutch Courts\\nLucyfurt, VT 53549-9000\",\"x_location\":67.5,\"y_location\":56.4,\"free_slots_high\":26,\"free_slots_medium\":34,\"free_slots_low\":11,\"price_high\":5619,\"price_medium\":690,\"price_low\":768,\"created_at\":\"2020-06-19T13:25:48.000000Z\",\"updated_at\":\"2020-06-19T13:25:48.000000Z\",\"phones\":[],\"distance\":72.48041114673674},{\"id\":7,\"username\":\"thora70@example.org\",\"name\":\"Welch, Dickens and Zulauf\",\"address_location\":\"6421 O'Reilly Neck Apt. 660\\nGeovannystad, ID 92063-2356\",\"x_location\":83.1,\"y_location\":3.1,\"free_slots_high\":185,\"free_slots_medium\":0,\"free_slots_low\":8,\"price_high\":3168,\"price_medium\":546,\"price_low\":401,\"created_at\":\"2020-06-19T13:25:47.000000Z\",\"updated_at\":\"2020-06-19T13:25:47.000000Z\",\"phones\":[],\"distance\":72.5315103937592},{\"id\":10,\"username\":\"willa.feil@example.com\",\"name\":\"Simonis-Runolfsson\",\"address_location\":\"33425 Zion Underpass Suite 088\\nGreenburgh, ID 39725-9264\",\"x_location\":89,\"y_location\":13.1,\"free_slots_high\":22,\"free_slots_medium\":15,\"free_slots_low\":45,\"price_high\":5367,\"price_medium\":678,\"price_low\":327,\"created_at\":\"2020-06-19T13:25:48.000000Z\",\"updated_at\":\"2020-06-19T13:25:48.000000Z\",\"phones\":[],\"distance\":78.0282641098724},{\"id\":20,\"username\":\"gruecker@example.com\",\"name\":\"Mante-Doyle\",\"address_location\":\"2448 Gillian Roads Suite 779\\nLake Lolaview, IA 66230\",\"x_location\":35.8,\"y_location\":86.5,\"free_slots_high\":94,\"free_slots_medium\":51,\"free_slots_low\":35,\"price_high\":3145,\"price_medium\":1978,\"price_low\":844,\"created_at\":\"2020-06-19T14:34:39.000000Z\",\"updated_at\":\"2020-06-19T14:34:39.000000Z\",\"phones\":[],\"distance\":79.46879890875412},{\"id\":4,\"username\":\"acremin@example.com\",\"name\":\"Grady-Keebler\",\"address_location\":\"91382 Lloyd Mountain\\nPort Susanaville, IL 15792-3952\",\"x_location\":13.6,\"y_location\":92.8,\"free_slots_high\":47,\"free_slots_medium\":94,\"free_slots_low\":17,\"price_high\":6605,\"price_medium\":965,\"price_low\":313,\"created_at\":\"2020-06-19T13:25:47.000000Z\",\"updated_at\":\"2020-06-19T13:25:47.000000Z\",\"phones\":[],\"distance\":81.8413098624405},{\"id\":6,\"username\":\"ifadel@example.org\",\"name\":\"Padberg-Herzog\",\"address_location\":\"545 Weimann Trace Suite 548\\nNew Keatonshire, NV 53264\",\"x_location\":63.5,\"y_location\":76.7,\"free_slots_high\":181,\"free_slots_medium\":11,\"free_slots_low\":16,\"price_high\":8528,\"price_medium\":726,\"price_low\":721,\"created_at\":\"2020-06-19T13:25:47.000000Z\",\"updated_at\":\"2020-06-19T13:25:47.000000Z\",\"phones\":[],\"distance\":84.09958382774555},{\"id\":18,\"username\":\"schuster.thurman@example.org\",\"name\":\"Keeling LLC\",\"address_location\":\"369 Jabari Hill Suite 260\\nWest Kacey, GA 91937-7403\",\"x_location\":60.8,\"y_location\":94,\"free_slots_high\":142,\"free_slots_medium\":71,\"free_slots_low\":32,\"price_high\":2832,\"price_medium\":615,\"price_low\":862,\"created_at\":\"2020-06-19T14:34:39.000000Z\",\"updated_at\":\"2020-06-19T14:34:39.000000Z\",\"phones\":[],\"distance\":96.79380145443199}]}");


    }

    public void addHospitals(JSONObject root) throws JSONException {
    JSONArray hospitals= root.getJSONArray("hospitals");
    hospitalData.clear();
    for(int i =0 ; i<hospitals.length() ; i++)
    {
        JSONObject hospital_element=hospitals.getJSONObject(i);
        String name = hospital_element.getString("name");
        String address_location = hospital_element.getString("address_location");
        String phones = String.valueOf(0);//hospital_element.getJSONArray("phones").getString(0);
        String free_slots_high = hospital_element.getString("free_slots_high");
        String free_slots_medium = hospital_element.getString("free_slots_medium");
        String free_slots_low = hospital_element.getString("free_slots_low");
        String price_high = hospital_element.getString("price_high");
        String price_medium = hospital_element.getString("price_medium");
        String price_low = hospital_element.getString("price_low");
        hospitalData.add(new HospitalModel(name,address_location,phones,free_slots_high,price_high,free_slots_medium,price_medium,free_slots_low,price_low));
    }
        adapter.notifyDataSetChanged();
}

}