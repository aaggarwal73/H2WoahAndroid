package com.h2woah.model;

/**
 * Created by Aman on 12/8/16.
 */

public class WaterQualityReport {
    private static int num = 1;

    private static int reportNum;
    public int getNum() { return reportNum; }

    private WaterSafety safety;

    private WaterSafety getSafety() { return safety; }

    private double virusPPM;
    private double contaminantPPM;
    private String date;
    private String time;
    private String reporterName;
    private double lat;
    private double lon;

    public WaterQualityReport(String date, String time, String name, double lat,
                              double lon, WaterSafety safety, double virusPPM, double contaminantPPM) {
        this.date = date;
        this.time = time;
        this.reporterName = reporterName;
        this.lat = lat;
        this.lon = lon;
        this.safety = safety;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
        this.reportNum = num;
    }

    public void createWaterQualityReport(String date, String time, String reporterName, double lat,
                                         double lon, WaterSafety safety, double virusPPM, double contaminantPPM) {
        this.date = date;
        this.time = time;
        this.reporterName = reporterName;
        this.lat = lat;
        this.lon = lon;
        this.safety = safety;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
        this.reportNum = num;
        num++;
    }

    public double getVppm() {
        return virusPPM;
    }

    public double getCppm() {
        return contaminantPPM;
    }


    public String getTypeOfReport() {
        return "Quality Report";
    }

    @Override
    public String toString() {
        return "Num: " + num + "\n" + "Date: " + date + "\n"
                + "Time: " + time + "\n" + "Name: " + reporterName
                + "\n" + "Lat: " + lat + "\n" + "Long: " + lon
                + "\n" + "Type: " + safety + "\n" + "Virus PPM: " + virusPPM
                + "\n" + "Contaminant PPM: " + contaminantPPM;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!other.getClass().equals(this.getClass())) {
            return false;
        }
        WaterQualityReport that = (WaterQualityReport) other;
        if (that.getNum() != this.getNum()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return num;
    }
}
