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
import com.h2woah.model.WaterSourceReport;

public class CreateQualityReport extends AppCompatActivity {
    private TextView or;
    private EditText latitude;
    private EditText longitude;
    private Spinner quality;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quality_report);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(myToolbar);
        or = (TextView) findViewById(R.id.or_label);
        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.longitude);
        setSupportActionBar(myToolbar);
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

    public void cancel(View view) {
        Toast toast = Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    public void createReport(View view) {
        Toast toast = Toast.makeText(this, "Created", Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
