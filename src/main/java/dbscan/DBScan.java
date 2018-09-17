package dbscan;

import models.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class DBScan {

    private double epslon;
    private int minPoints;

    public DBScan(double epslon, int  minPoints){
        this.epslon = Math.pow(epslon,2);
        this.minPoints = minPoints;
    }

    public Map<Integer, List<Point>> clusterize(List<Point> points){
        Map<Integer, List<Point>> clusters = new HashMap<>();

        if (points.size() == 0)
             return clusters;

        Queue<Point> queue = new LinkedList<>();

        int cluster = 0;

        while (!areAllAnalized(points)){

            if (clusters.get(cluster) == null)
                clusters.put(cluster,new ArrayList<>());

            Point center = queue.isEmpty()? getNext(points):queue.remove();
            center.setUsed();

            List<Point> nearPoints = getPoinsInCircle(points, center);

            if (nearPoints.size() >= this.minPoints){
                System.out.println("Adding point " + center.getLatitude() +  " "  + center.isUsed() + " in cluster " + cluster);
                clusters.get(cluster).add(center);

                List<Point> candidates = nearPoints
                        .parallelStream()
                        .filter(p -> !queue.contains(p))
                        .filter(p -> !p.isUsed())
                        .collect(Collectors.toList());

                queue.addAll(candidates);

            }

            if (queue.isEmpty() && clusters.get(cluster).size() > 0)
                cluster++;

        }

        return clusters;
    }

    private List<Point> getPoinsInCircle(List<Point> points, Point center){
        double squaredRadius =  Math.pow(this.epslon, 2);

        return points
                .stream()
                .filter( p -> (isInsideCircle(center.getLatitude(), center.getLongitude(),p.getLatitude(),p.getLongitude(),squaredRadius)))
                .collect(Collectors.toList());
    }

    private boolean isInsideCircle(double a, double b, double x, double y, double squaredRadius){
        return   Math.pow((x-a),2) + Math.pow((y-b),2) <= squaredRadius;
    }

    private boolean areAllAnalized(List<Point> points){
        return points.stream().filter(p -> p.isUsed() == false).count() == 0;
    }

    private Point getNext(List<Point> points){
        List<Point> elegible = points.stream().filter(p -> p.isUsed() == false).collect(Collectors.toList());
        return elegible.size() == 0? null: elegible.get(0);
    }

}
