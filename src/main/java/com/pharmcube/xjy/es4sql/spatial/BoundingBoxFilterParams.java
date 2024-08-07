package com.pharmcube.xjy.es4sql.spatial;

public class BoundingBoxFilterParams {
    private Point topLeft;
    private Point bottomRight;

    public BoundingBoxFilterParams(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }
}
