package com.example.noah.volleyjson;

/**
 * Created by Noah on 2/10/2017.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;


import java.sql.Date;

public class details extends Activity {

    private ProgressDialog pDialog;

    private TextView t;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.act2);
        t = (TextView) findViewById(R.id.tv);

        //getting data from previous page
        String name = getIntent().getStringExtra("name");
        String company = getIntent().getStringExtra("company");
        String favorite = getIntent().getStringExtra("favorite");
        String small = getIntent().getStringExtra("small");
        String large = getIntent().getStringExtra("large");
        String email = getIntent().getStringExtra("email");
        String site = getIntent().getStringExtra("website");
        String birth = getIntent().getStringExtra("birth");
        String work = getIntent().getStringExtra("work");
        String home = getIntent().getStringExtra("home");
        String mobile = getIntent().getStringExtra("mobile");
        String street = getIntent().getStringExtra("street");
        String city = getIntent().getStringExtra("city");
        String state = getIntent().getStringExtra("state");
        String country = getIntent().getStringExtra("country");
        String zip = getIntent().getStringExtra("zip");
        String lat = getIntent().getStringExtra("lat");
        String longi = getIntent().getStringExtra("long");

        //code for parsing 10 digit number into birthday
        int bday = 0;
        try {
            bday = Integer.parseInt(birth);
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        int timestamp = bday;
        Date birthDate = new Date(timestamp * 1000);

        //formating test view visually
        t.setTextColor(Color.GRAY);
        t.setBackgroundColor(Color.BLACK);

        //populating textview with data
        t.setText(Html.fromHtml("<b> Name: </b>"));
        t.append(name+" \n");
        t.append(Html.fromHtml("<b> Company: </b>"+company));
        t.append("\n");
        t.append(Html.fromHtml("<b> Favorite: </b>"+" "+favorite));
        t.append("\n");
        t.append(Html.fromHtml("<b> SmallURL: </b> "+" "+small));
        t.append("\n");
        t.append(Html.fromHtml("<b> Email: </b> "+" "+email));
        t.append("\n");
        t.append(Html.fromHtml("<b> Website: </b> "+" "+site));
        t.append("\n");
        t.append(Html.fromHtml("<b> Birthday: </b> "+" "+birthDate));
        t.append("\n\n");
        t.append(Html.fromHtml("<b> Phone: </b> "));
        t.append("\n   ");
        t.append(Html.fromHtml("<b> Work: </b> "+work));
        t.append("\n   ");
        t.append(Html.fromHtml("<b> Home: </b> "+home));
        t.append("\n   ");
        t.append(Html.fromHtml("<b> Mobile: </b> "+mobile));
        t.append("\n\n");
        t.append(Html.fromHtml("<b> Address: </b> "));
        t.append("\n   ");
        t.append(street+"\n   "+city+", "+state+" "+country+"\n   ");
        t.append(Html.fromHtml("<b> Zip Code: </b> "));
        t.append(zip+"\n   ");
        t.append(Html.fromHtml("<b> Latitude: </b> "));
        t.append(" "+lat+"\n   ");
        t.append(Html.fromHtml("<b> Latitude: </b> "));
        t.append(" "+longi);

        //getting Largeimage
        new DownloadImageTask((ImageView) findViewById(R.id.imageView))
                .execute(large);
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


