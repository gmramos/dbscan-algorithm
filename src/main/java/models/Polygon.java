package models;

import java.util.List;

public class Polygon {

    private List<Point> points;
    private Point center;

    public Polygon(List<Point> points){
        this.points = points;
    }

    public void addPoint(Point point){

    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }
}
