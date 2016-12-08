package com.h2woah;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.h2woah.model.WaterQualityReport;
import com.h2woah.model.WaterSafety;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CreateQualityReport extends AppCompatActivity {
    private TextView or;
    private EditText latitude;
    private EditText longitude;
    private Spinner quality;
    private EditText virusPPM;
    private EditText contaminantPPM;
    public static ArrayList<WaterQualityReport> qReports = new ArrayList<WaterQualityReport>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quality_report);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(myToolbar);
        or = (TextView) findViewById(R.id.or_label);
        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.longitude);
        virusPPM = (EditText) findViewById(R.id.virusPPM);
        contaminantPPM =(EditText) findViewById(R.id.contaminantPPM);
        quality = (Spinner) findViewById(R.id.quality);

        quality.setAdapter(new ArrayAdapter<WaterSafety>(this, android.R.layout.simple_spinner_item,
                WaterSafety.values()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_action: {
                LoginActivity.logout();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_back: {
                NavUtils.navigateUpFromSameTask(this);
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    public void cancel(View view) {
        Toast toast = Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void createReport(View view) {
        final double ATLLAT = 33.7490;
        final double ATLLONG = -84.3880;
        boolean coordErr = false;
        String lat = latitude.getText().toString();
        double latNum = ATLLAT;
        String lon = longitude.getText().toString();
        double lonNum = ATLLONG;
        WaterSafety safety = (WaterSafety) quality.getSelectedItem();

        double vp = Double.parseDouble(virusPPM.getText().toString());
        double cp = Double.parseDouble(contaminantPPM.getText().toString());

        DateFormat df = new SimpleDateFormat("MM/dd/yy");
        Date dateObj = new Date();
        String date = df.format(dateObj);
        DateFormat tf = new SimpleDateFormat("HH:mm:ss");
        String time = tf.format(dateObj);

        String name = LoginActivity.currentUser.getEmail();

        if (!lat.equals("") && !lon.equals("")) {
            try {
                latNum = Double.parseDouble(lat);
                lonNum = Double.parseDouble(lon);
            } catch (NumberFormatException e) {     // User inputs incorrect format for latitude or longitude
                coordErr = true;
            }
        }


        if (coordErr) {
            Toast toast = Toast.makeText(this, "Latitude  or Longitude are not coordinates", Toast.LENGTH_LONG);
            toast.show();
        } else {

            WaterQualityReport report = new WaterQualityReport(date, time, name, latNum, lonNum, safety,vp, cp );
            qReports.add(report);
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);

            Toast toast = Toast.makeText(this, "Created", Toast.LENGTH_LONG);
            toast.show();
            intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }
    }
}
