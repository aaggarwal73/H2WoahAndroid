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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.h2woah.model.UserLevel;
import com.h2woah.model.WaterSourceReport;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class CreateSourceReport extends AppCompatActivity {
    private TextView or;
    private EditText latitude;
    private EditText longitude;
    private Spinner condition;
    private Spinner source;
    public static ArrayList<WaterSourceReport> sReports = new ArrayList<WaterSourceReport>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_source_report);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.actionbar);
        or = (TextView) findViewById(R.id.or_label);
        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.longitude);
        setSupportActionBar(myToolbar);
        condition = (Spinner) findViewById(R.id.condition);
        source = (Spinner) findViewById(R.id.source);
        condition.setAdapter(new ArrayAdapter<WaterSourceReport.WaterCondition>(this, android.R.layout.simple_spinner_item,
                WaterSourceReport.WaterCondition.values()));
        source.setAdapter(new ArrayAdapter<WaterSourceReport.WaterType>(this, android.R.layout.simple_spinner_item,
                WaterSourceReport.WaterType.values()));


}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout_action:
            {
                LoginActivity.logout();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_back:
            {
                NavUtils.navigateUpFromSameTask(this);
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    public void createReport(View view) {
        final double ATLLAT = 33.7490;
        final double ATLLONG = -84.3880;
        boolean coordErr = false;
        String lat = latitude.getText().toString();
        double latNum = ATLLAT;
        String lon = longitude.getText().toString();
        double lonNum = ATLLONG;
        WaterSourceReport.WaterType type = (WaterSourceReport.WaterType) source.getSelectedItem();
        WaterSourceReport.WaterCondition condit = (WaterSourceReport.WaterCondition) condition.getSelectedItem();
        DateFormat df = new SimpleDateFormat("MM/dd/yy");
        Date dateObj =  new Date();
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

            WaterSourceReport report = new WaterSourceReport(date, time, name, latNum, lonNum, type, condit);
            sReports.add(report);
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
            //write to file
//            try {
//                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
//                        this.openFileOutput("SourceReport.txt", this.MODE_PRIVATE));
//                outputStreamWriter.write(report.toDatabase());
//                outputStreamWriter.close();
//                Intent intent = new Intent(this, MapsActivity.class);
//                startActivity(intent);
//            } catch (IOException e) {
//
//            }
        }
    }

    /**
     * Cancels and confirms not creating the report
     */

    public void cancel(View view) {
        Toast toast = Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }


}
