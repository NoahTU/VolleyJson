package com.example.noah.volleyjson;
/**
 * Created by Noah on 2/10/2017.
 */


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import java.util.ArrayList;
import java.util.List;

import static java.util.logging.Logger.global;

public class MainActivity extends Activity {

    // json object response url
    private String urlJsonObj = "https://s3.amazonaws.com/technical-challenge/Contacts_v2.json";

    // json array response url
    private String urlJsonArry = "https://s3.amazonaws.com/technical-challenge/Contacts_v2.json";

    private static String TAG = MainActivity.class.getSimpleName();

    // Progress dialog
    private ProgressDialog pDialog;

    public ListView lv;

    //List for transporting data to next page
    public List<String> arrayl = new ArrayList<String>();

    public List<String> emaill = new ArrayList<String>();

    public List<String> homel = new ArrayList<String>();

    public List<String> mobilel = new ArrayList<String>();

    public List<String> companyl = new ArrayList<String>();

    public List<String> favoritel = new ArrayList<String>();

    public List<String> small = new ArrayList<String>();

    public List<String> large = new ArrayList<String>();

    public List<String> websitel = new ArrayList<String>();

    public List<String> birth = new ArrayList<String>();

    public List<String> workl = new ArrayList<String>();

    public List<String> streetl = new ArrayList<String>();

    public List<String> cityl = new ArrayList<String>();

    public List<String> statel = new ArrayList<String>();

    public List<String> countryl = new ArrayList<String>();

    public List<String> zipl = new ArrayList<String>();

    public List<String> lat = new ArrayList<String>();

    public List<String> longi = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        //the parsing begins
        makeJsonArrayRequest();
    }


  //Method to make json array request
    public void makeJsonArrayRequest() {

        showpDialog();

        //open JSON request
        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {

                            lv = (ListView) findViewById(R.id.lst);

                            // loop through for each json object
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response.get(i);

                                //get needed JSON fields
                                String name = person.optString("name");
                                String company = person.optString("company");
                                String favorite = person.optString("favorite");
                                String smallImageURL = person.optString("smallImageURL");
                                String largeImageURL = person.optString("largeImageURL");
                                String email = person.optString("email");
                                String website = person.optString("website");
                                String birthdate = person.optString("birthdate");
                                JSONObject phone = person
                                        .getJSONObject("phone");
                                String work = phone.optString("work");
                                String home = phone.optString("home");
                                String mobile = phone.optString("mobile");
                                JSONObject address = person
                                        .getJSONObject("address");
                                String street = address.optString("street");
                                String city = address.optString("city");
                                String state = address.optString("state");
                                String country = address.optString("country");
                                String zip = address.optString("zip");
                                String latitude = address.optString("latitude");
                                String longitude = address.optString("longitude");

                                //add data to storage list
                                arrayl.add(name);
                                companyl.add(company);
                                favoritel.add(favorite);
                                small.add(smallImageURL);
                                large.add(largeImageURL);
                                emaill.add(email);
                                websitel.add(website);
                                birth.add(birthdate);
                                workl.add(work);
                                homel.add(home);
                                mobilel.add(mobile);
                                streetl.add(street);
                                cityl.add(city);
                                statel.add(state);
                                countryl.add(country);
                                zipl.add(zip);
                                lat.add(latitude);
                                longi.add(longitude);

                            }

                            //apply names to main page ViewList
                           ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrayl);

                           lv.setAdapter(adapter);

                            //wait for user click
                            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                //bring data to next page(details)
                                @Override
                                public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
                                    Intent intent= new Intent(MainActivity.this, details.class);
                                    intent.putExtra("name", arrayl.get(p3));
                                    intent.putExtra("company", companyl.get(p3));
                                    intent.putExtra("favorite", favoritel.get(p3));
                                    intent.putExtra("small", small.get(p3));
                                    intent.putExtra("large", large.get(p3));
                                    intent.putExtra("email", emaill.get(p3));
                                    intent.putExtra("website", websitel.get(p3));
                                    intent.putExtra("birth", birth.get(p3));
                                    intent.putExtra("work", workl.get(p3));
                                    intent.putExtra("home", homel.get(p3));
                                    intent.putExtra("mobile", mobilel.get(p3));
                                    intent.putExtra("street", streetl.get(p3));
                                    intent.putExtra("city", cityl.get(p3));
                                    intent.putExtra("state", statel.get(p3));
                                    intent.putExtra("country", countryl.get(p3));
                                    intent.putExtra("zip", zipl.get(p3));
                                    intent.putExtra("lat", lat.get(p3));
                                    intent.putExtra("long", longi.get(p3));
                                    startActivity(intent);
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                        hidepDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);

    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}