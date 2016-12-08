package com.h2woah;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.h2woah.model.WaterQualityReport;

import java.util.ArrayList;

public class ViewGraphActivity extends AppCompatActivity {
    private ScatterChart chart;
    private ArrayList<WaterQualityReport> reports;

    /**
     * Sets the reports to be a part of the graph
     * @param r the reports to be viewed
     */
    public void setReports(ArrayList<WaterQualityReport> r) {
        reports = r;
    }

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_graph);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(myToolbar);
        reports = CreateQualityReport.qReports;
        loadGraph("2016");


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



    /**
     * Sets the type
     * @param t String indicating the type
     */
    public void setType(String t) { type = t; }



//    private NumberAxis xAxis;
//
//    private NumberAxis yAxis;

    private final int months = 12;


//
//    /**
//     * initalize the graphs
//     */
//
//    private void initialize() {
//        xAxis.setLabel("Month");
//        xAxis.setTickUnit(1);
//        xAxis.setLowerBound(1);
//        xAxis.setUpperBound(months);
//    }

    /**
     * The graphs to load
     * @param year the year to start with
     */
    public void loadGraph(String year) {
        chart = (ScatterChart) findViewById(R.id.chart);
//
//        ArrayList<Entry> entries = new ArrayList<>();
//        entries.add(new Entry(, 0));
//        entries.add(new Entry(8f, 1));
//        entries.add(new Entry(6f, 2));
//        entries.add(new Entry(12f, 3));
//        entries.add(new Entry(18f, 4));
//        entries.add(new Entry(9f, 5));
//
//        ScatterDataSet dataset = new ScatterDataSet(entries, "asdfj");
//
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        labels.add("August");
        labels.add("September");
        labels.add("October");
        labels.add("November");
        labels.add("December");






        //yAxis.setLabel(type.get() + " (ppm)");
        //chart.
       // chart.setTitle("Monthly Change in Water Quality for " + year);
        if (reports != null) {
            //Series<Integer, Double> series = new Series<>();
            ArrayList<Entry> series = new ArrayList<Entry>();
            float[] monthlyAverages = new float[months];
            int[] numMonthlyReports = new int[months];
            for (int i = 0; i < months; i++) {
                monthlyAverages[i] = 0.0f;
                numMonthlyReports[i] = 0;
            }
            for (WaterQualityReport report : reports) {
                Double yVal;
                if ("Virus".equals(type)) {
                    yVal = report.getVppm();
                } else {
                    yVal = report.getCppm();
                }
                Integer xVal = Integer.parseInt(report.getDate().substring(0, 2));
                monthlyAverages[xVal-1] += yVal;
                numMonthlyReports[xVal-1] += 1;

            }
            for (int i = 0; i < months; i++) {
                int numReports = numMonthlyReports[i];
                if (numReports != 0) {
                    monthlyAverages[i] /= numReports;
                    series.add(new Entry(monthlyAverages[i], i));
                }
            }
            ScatterDataSet dataset = new ScatterDataSet(series, "contaminants");
            ScatterData data = new ScatterData(labels, dataset);
            chart.setData(data);
            chart.setDescription("Description");
            dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
            dataset.setScatterShapeSize(10);
            dataset.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
            chart.animateY(5000);
        }
    }
}
