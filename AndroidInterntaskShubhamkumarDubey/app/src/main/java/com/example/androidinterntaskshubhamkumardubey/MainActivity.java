package com.example.androidinterntaskshubhamkumardubey;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String api_1 = "https://vpic.nhtsa.dot.gov/api/vehicles/getallmakes?format=json";
    String api_2 = "https://vpic.nhtsa.dot.gov/api/vehicles/GetModelsForMakeId/";
    ArrayList allModelsList;
    ArrayList allModelsNameList;
    String selection;
    String selection2;
    RecyclerView recyclerview;
    Button add_Button,refresh,logoutbtn;
    MyDatabaseHelper myDB;
    ArrayList<String> make_id , car_model;
    CoustomAdaptor coustomAdaptor;
    int myNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerview = findViewById(R.id.recyclerView);
        add_Button = findViewById(R.id.addButton);
        refresh = findViewById(R.id.refreshButton);
        logoutbtn = findViewById(R.id.logout_btn);



        allModelsList = new ArrayList();
        allModelsNameList = new ArrayList();
        getData1();


        ArrayAdapter<ArrayList> adapter = new ArrayAdapter<>(this, R.layout.dropdown_items, allModelsList);
        ArrayAdapter<ArrayList> adapter2 = new ArrayAdapter<>(this, R.layout.dropdown_items, allModelsNameList);

        AutoCompleteTextView autoCompleteTextView1 = findViewById(R.id.filled_expossed1);
        autoCompleteTextView1.setAdapter(adapter);

        autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                selection = (String) adapterView.getItemAtPosition(i);
                String new_api = api_2 + selection + "?format=json";

                getData2(new_api);

                AutoCompleteTextView autoCompleteTextView2 = findViewById(R.id.filled_expossed2);
                adapter2.clear();
                autoCompleteTextView2.setAdapter(adapter2);

                autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        selection2 = (String) adapterView.getItemAtPosition(i);
                    }
                });


            }
        });




        add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selection != null && selection2 !=null) {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
                    myDB.addData(Integer.valueOf(selection), selection2);
                    RefreshDb();
                }
                else {
                    Toast.makeText(MainActivity.this,"Select Model amd Make",Toast.LENGTH_SHORT).show();
                }
            }
        });



        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RefreshDb();
            }
        });


    }

    void RefreshDb(){
        myDB = new MyDatabaseHelper(MainActivity.this);
        make_id = new ArrayList<>();
        car_model = new ArrayList<>();

        coustomAdaptor = new CoustomAdaptor(MainActivity.this, make_id,car_model);
        recyclerview.setAdapter(coustomAdaptor);
        recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        StoreDataInArray();


        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });


    }

    void StoreDataInArray()  {

        Cursor cursor = myDB.readALLData();
        if (cursor.getCount()==0)
        {
            Toast.makeText(this,"NO DATA",Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext())
            {
                make_id.add(cursor.getString(0));
                car_model.add(cursor.getString(1));
            }
        }
    }

    private void getData1() {

        RequestQueue queue = Volley.newRequestQueue(this);


// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, api_1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject myObject = new JSONObject(response);
                            JSONArray jsonArray = myObject.getJSONArray("Results");


                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);


                                allModelsList.add(object.getString("Make_ID"));
                            }

                        } catch (JSONException e) {
                            Log.e("api", "onResponse:" + e.getLocalizedMessage());
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("api", "onErrorResponse:" + error.getLocalizedMessage());
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    private void getData2(String newApi) {
        RequestQueue queue = Volley.newRequestQueue(this);


// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, newApi,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject myObject = new JSONObject(response);
                            JSONArray jsonArray = myObject.getJSONArray("Results");


                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);


                                allModelsNameList.add(object.getString("Model_Name"));
                            }


                        } catch (JSONException e) {
                            Log.e("api", "onResponse:" + e.getLocalizedMessage());
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("api", "onErrorResponse:" + error.getLocalizedMessage());
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


}