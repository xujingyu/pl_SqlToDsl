package com.pharmcube.xjy.es4sql.spatial;

import java.util.List;

public class PolygonFilterParams {
    private List<Point> polygon;

    public PolygonFilterParams( List<Point> polygon) {
        this.polygon = polygon;
    }

    public List<Point> getPolygon() {
        return polygon;
    }
}
