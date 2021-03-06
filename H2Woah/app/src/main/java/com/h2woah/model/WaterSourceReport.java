package com.h2woah.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Aman on 12/8/16.
 */

public class WaterSourceReport {

    public enum WaterType {
        BOTTLED, WELL, STREAM, LAKE, SPRING, OTHER;

        /**
         * Transforms the enum into a list
         * @return  the final list
         */
        public static Collection<WaterType> toList() {
            List<WaterType> list = new ArrayList<WaterType>();

            Collections.addAll(list, values());
            return list;
        }
        public static String toString(WaterType level) {
            switch (level) {
                case BOTTLED:
                    return "BOTTLED";
                case WELL:
                    return "WELL";
                case STREAM:
                    return "STREAM";
                case LAKE:
                    return "LAKE";
                case SPRING:
                    return "SPRING";
                case OTHER:
                    return "OTHER";
            }

            return null;
        }
        public static WaterType stringToType(String level) {
            if ((level instanceof String)) {
                switch (level) {
                    case "BOTTLED":
                        return BOTTLED;
                    case "WELL":
                        return WELL;
                    case "STREAM":
                        return STREAM;
                    case "LAKE":
                        return LAKE;
                    case "SPRING":
                        return SPRING;
                    case "OTHER":
                        return OTHER;
                }
            }
            return null;
        }
    }

    public enum WaterCondition {
        WASTE, TREATABLECLEAR, TREATABLEMUDDY, POTABLE;

        /**
         * Transforms the enum into a list
         * @return  the final list
         */
        public static Collection<WaterCondition> toList() {
            List<WaterCondition> list = new ArrayList<WaterCondition>();

            Collections.addAll(list, values());
            return list;
        }
        public static WaterCondition stringToType(String level) {
            if ((level instanceof String)) {
                switch (level) {
                    case "WASTE":
                        return WASTE;
                    case "TREATABLECLEAR":
                        return TREATABLECLEAR;
                    case "TREATABLEMUDDY":
                        return TREATABLEMUDDY;
                    case "POTABLE":
                        return POTABLE;

                }
            }
            return null;
        }
    }

    public static int num = 1;
    private int reportNum;
    public int getNum() { return reportNum; }

    private WaterType type;
    private WaterCondition condition;

    private WaterType getType() { return type; }

    private WaterCondition getCondition() { return condition; }

    private String date;
    private String time;

    public int getReportNum() {
        return reportNum;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    private String name;
    private double lat;
    private double lon;
    public WaterSourceReport(String date, String time, String name,
                             double lat, double lon, WaterType type, WaterCondition condition) {
        this.date = date;
        this.time = time;
        this.name = name;
        this.lat = lat;
        this.lon = lon;

        this.type = type;
        this.condition = condition;
        this.reportNum = num;
        num++;
    }

    public WaterSourceReport(int num, String date, String time, String name,
                             double lat, double lon, WaterType type, WaterCondition condition) {
        this.date = date;
        this.time = time;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.type = type;
        this.condition = condition;
        this.reportNum = num;
       // WaterSourceReport.num++;
    }

    public static WaterCondition stringToCondition(String s) {
        switch(s) {
            case "WASTE":
                return WaterCondition.WASTE;
            case "TREATABLECLEAR":
                return WaterCondition.TREATABLECLEAR;
            case "TREATABLEMUDDY":
                return WaterCondition.TREATABLEMUDDY;
            case "POTABLE":
                return WaterCondition.POTABLE;
            default:
                return WaterCondition.WASTE;
        }
    }

    public static WaterType stringToWaterType(String s) {
        // BOTTLED, WELL, STREAM, LAKE, SPRING, OTHER;
        switch (s) {
            case "BOTTLED":
                return WaterType.BOTTLED;
            case "WELL":
                return WaterType.WELL;
            case "STREAM":
                return WaterType.STREAM;
            case "LAKE":
                return WaterType.LAKE;
            case "SPRING":
                return WaterType.SPRING;
            default:
                return WaterType.OTHER;
        }
    }

    public String getTypeOfReport() {
        return "Source Report";
    }

    public String toDatabase(WaterSourceReport report) {
        return  reportNum + "," + date + "," + time + "," + name + "," + lat + "," + lon + "," + type + "," + condition +"\n";
//        sReports.add(report);
    }
    @Override
    public String toString() {
        return "Num: " + reportNum + "\n" + "Date: " + date + "\n"
                + "Time: " + time + "\n" + "Name: " + name
                + "\n" + "Lat: " + lat + "\n" + "Long: " + lon
                + "\n" + "Type: " + type + "\n" + "Condition: " + condition;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!other.getClass().equals(this.getClass())) {
            return false;
        }
        WaterSourceReport that = (WaterSourceReport) other;
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
