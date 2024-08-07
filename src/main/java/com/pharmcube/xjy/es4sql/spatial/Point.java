package com.pharmcube.xjy.es4sql.spatial;

public class Point {
    private double lon;
    private double lat;

    public Point(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }
}
