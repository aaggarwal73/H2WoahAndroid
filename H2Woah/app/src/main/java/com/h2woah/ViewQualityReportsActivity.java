package com.h2woah;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.h2woah.model.WaterQualityReport;
import com.h2woah.model.WaterSourceReport;

import java.util.Iterator;

public class ViewQualityReportsActivity extends AppCompatActivity {
    private ListView listView;
    private static String[] items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items = new String[CreateQualityReport.qReports.size()];
        setContentView(R.layout.activity_view_q_reports);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(myToolbar);
        listView = (ListView) findViewById(R.id.listview);
        Iterator iter = CreateQualityReport.qReports.iterator();
        int i = 0;
        while (iter.hasNext()) {

            WaterQualityReport report = (WaterQualityReport) iter.next();
            if ("Quality Report".equals(report.getTypeOfReport())) {
                items[i] = report.toString();
            }
            i++;
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.list_item, items);
        listView.setAdapter(adapter);

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
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
