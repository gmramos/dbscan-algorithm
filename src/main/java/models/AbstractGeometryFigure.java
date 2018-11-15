package models;

import java.util.List;

public abstract class AbstractGeometryFigure {
    public abstract List<Point> getPointsInside(List<Point> points, Point center);
}
