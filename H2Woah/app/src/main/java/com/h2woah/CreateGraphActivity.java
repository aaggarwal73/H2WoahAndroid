package com.h2woah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.h2woah.model.WaterQualityReport;

import java.util.ArrayList;
//import java.util.Optional;

public class CreateGraphActivity extends AppCompatActivity {
    private static String year;
    private ToggleButton virus;
    private ToggleButton contaminant;
    private Spinner yearBox;
    private EditText latField;
    private EditText longField;
    public static String getYear() {
        return year;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_graph);
        yearBox = (Spinner) findViewById(R.id.year);
        latField = (EditText) findViewById(R.id.latitude);
        longField = (EditText) findViewById(R.id.longitude);
        virus = (ToggleButton) findViewById(R.id.toggleVirus);
        contaminant = (ToggleButton) findViewById(R.id.toggleContaminant);
        virus.setSelected(true);
        contaminant.setSelected(false);

        virus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    virus.setSelected(true);
                    contaminant.setSelected(false);
                } else {
                    virus.setSelected(false);
                    contaminant.setSelected(true);
                    // The toggle is disabled
                }
            }
        });
        contaminant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    virus.setSelected(false);
                    contaminant.setSelected(true);
                } else {
                    virus.setSelected(true);
                    contaminant.setSelected(false);
                    // The toggle is disabled
                }
            }
        });
        //button create graph
    }
    public void cancel(View view) {
        Toast toast = Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void createGraph(View view) {
        final double ATLLAT = 33.7490;
        final double ATLLONG = -84.3880;
        boolean coordErr = false;
        String lat = latField.getText().toString();
        double latNum = ATLLAT;
        String lon = longField.getText().toString();
        double lonNum = ATLLONG;
        String yearStr = (String) yearBox.getSelectedItem();
        year = yearStr;
        if (yearStr.length() == 4) {
            year = yearStr.substring(2);
        }
        boolean isVirus = false;
        boolean isContaminant = false;
        if (virus.isSelected()) {
            isVirus = true;
        }
        if (contaminant.isSelected()) {
            isContaminant = true;
        }

        if (!lat.equals("") && !lon.equals("")) {
            try {
                latNum = Double.parseDouble(lat);
                lonNum = Double.parseDouble(lon);
            } catch (NumberFormatException e) {     // User inputs incorrect format for latitude or longitude
                coordErr = true;
            }
        }


        if (!coordErr) {


            ArrayList<WaterQualityReport> reports = new ArrayList<>();
            ArrayList<WaterQualityReport> qreports = CreateQualityReport.qReports;
            for (WaterQualityReport report : qreports) {
                if (report.getLat() == latNum && report.getLon() == lonNum
                        && report.getDate().substring(6).equals(year)) {
                    reports.add(report);
                }
            }
            Intent intent = new Intent(this, ViewGraphActivity.class);
            startActivity(intent);
//            mainApplication.initGraphViewScreen(reports,
//                    isVirus ? "Virus" : "Contaminant", yearStr);

        }

    }



}
