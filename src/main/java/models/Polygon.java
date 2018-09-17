package models;

import java.util.ArrayList;
import java.util.List;

public class Polygon {

    private List<Point> points;

    public Polygon(){
        this.points = new ArrayList<>();
    }

    public void addPoint(Point point){
        if (point != null){
            points.add(point);
        }
    }

    public List<Point> getPoints() {
        return points;
    }


    public Point getCenter() {
        int pointsCount = this.getPoints().size();

        if (pointsCount < 2) {
            return null;
        }

        float x = this.getPoints().get(0).getX(), y= this.getPoints().get(0).getY();


        for (int i =1 ; i < pointsCount; i++){
            x = (x + this.getPoints().get(i).getX())/2;
            y = (y + this.getPoints().get(i).getY())/2;
        }

        return new Point(x,y);
    }

}
