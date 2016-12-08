package com.h2woah;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.h2woah.model.User;
import com.h2woah.model.WaterSourceReport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_back:
                LoginActivity.logout();
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                // User chose the "back" item, show the app settings UI...
                return true;
            case R.id.logout_action:
                LoginActivity.logout();
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                // User chose the "back" item, show the app settings UI...
                return true;
            case R.id.edit_profile:
                intent = new Intent(this, EditProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.new_water_report:
                intent = new Intent(this, CreateSourceReport.class);
                startActivity(intent);
                return true;
            case R.id.new_quality_report:
                intent = new Intent(this, CreateQualityReport.class);
                startActivity(intent);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng ATL = new LatLng(33.7756, -84.3963);
        //get source reports
       String[] sourceReports = new String[WaterSourceReport.num];
        for(WaterSourceReport report: CreateSourceReport.sReports) {
            LatLng loc = new LatLng(report.getLat(), report.getLon());
            mMap.addMarker(new MarkerOptions()
                    .position(loc)
                    .title(report.getTypeOfReport())
                    .snippet(report.toString()));
        }
//       try {  InputStream inputStream = this.openFileInput("SourceReport.txt");
//            if ( inputStream != null ) {
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//                List<String> lines = new ArrayList<String>();
//                String line;
//                while ((line = bufferedReader.readLine()) != null) {
//                    lines.add(line);
//                }
//
//                inputStream.close();
//                sourceReports = lines.toArray(new String[lines.size()]);
//            }
//       }
//        catch (FileNotFoundException e) {
//            Log.e("login activity", "File not found: " + e.toString());
//        } catch (IOException e) {
//            Log.e("login activity", "Can not read file: " + e.toString());
//        }
//        for (String sReport: sourceReports) {
//            if (sReport != null) {
//                String[] pieces = sReport.split(",");
//                if (pieces.length == 7) {
//                    System.out.println(pieces[0] + pieces[1] + pieces[2] + pieces[3] + pieces[4] + pieces[5] + pieces[6]);
//                    WaterSourceReport report = new WaterSourceReport(Integer.parseInt(pieces[0]), pieces[1], pieces[2], pieces[3], Double.parseDouble(pieces[4]),
//                            Double.parseDouble(pieces[5]), WaterSourceReport.WaterType.stringToType(pieces[6]), WaterSourceReport.WaterCondition.stringToType(pieces[7]));
//                    LatLng loc = new LatLng(Double.parseDouble(pieces[4]), Double.parseDouble(pieces[5]));
//                    mMap.addMarker(new MarkerOptions().position(loc).title(report.toString()));
//                }
//
//            }
//
//        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(ATL));
    }
}
