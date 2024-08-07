package com.pharmcube.xjy.es4sql.spatial;

public class RangeDistanceFilterParams extends  DistanceFilterParams {
    private String distanceTo;

    public RangeDistanceFilterParams(String distanceFrom,String distanceTo, Point from) {
        super(distanceFrom, from);
        this.distanceTo = distanceTo;
    }

    public String getDistanceTo() {
        return distanceTo;
    }

    public String getDistanceFrom() {
        return this.getDistance();
    }
}
