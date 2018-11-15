package models;

import java.util.List;
import java.util.stream.Collectors;

public class Circle extends AbstractGeometryFigure {

    private double radius;

    public Circle(double radius){
        this.radius = radius;
    }

    @Override
    public List<Point> getPointsInside(List<Point> points, Point center) {
        double squaredRadius = Math.pow(this.radius, 2);

        return points
                .stream()
                .filter(p -> isPointInside(center.getX(), center.getY(), p.getX(), p.getY(), squaredRadius))
                .collect(Collectors.toList());
    }

    private boolean isPointInside(double a, double b, double x, double y, double squaredRadius) {
        return Math.pow((x - a), 2) + Math.pow((y - b), 2) <= squaredRadius;
    }

}
