package dbscan;

import models.Point;
import models.Polygon;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class DBScan {

    private double epslon;
    private int minPoints;

    public DBScan(double epslon, int minPoints) {
        this.epslon = epslon;
        this.minPoints = minPoints;
    }

    public Map<Integer, Polygon> clusterize(List<Point> points) {
        Map<Integer, Polygon> clusters = new HashMap<>();

        if (points.size() == 0)
            return clusters;

        Queue<Point> queue = new LinkedList<>();

        int cluster = 0;

        while (!areAllAnalized(points)) {

            //Null polygon means that new cluster should be started
            if (clusters.get(cluster) == null)
                clusters.put(cluster, new Polygon());

            Point center = queue.isEmpty() ? getNext(points) : queue.remove();
            center.setUsed();

            List<Point> nearPoints = getPoinsInCircle(points, center, this.epslon);

            if (nearPoints.size() >= this.minPoints) {
                System.out.println("Adding point " + center.getX() + " " + center.isUsed() + " in cluster " + cluster);
                clusters.get(cluster).addPoint(center);

                List<Point> candidates = nearPoints
                        .parallelStream()
                        .filter(p -> !queue.contains(p))
                        .filter(p -> !p.isUsed())
                        .collect(Collectors.toList());

                queue.addAll(candidates);

            }

            //Just finished checking all neighborhood points and just finished creating a cluster, so a new cluster must be created
            if (queue.isEmpty() && clusters.get(cluster).getPoints().size() > 0)
                cluster++;

        }

        return clusters;
    }

    private List<Point> getPoinsInCircle(List<Point> points, Point center, double radius) {
        double squaredRadius = Math.pow(radius, 2);

        return points
                .stream()
                .filter(p -> isInsideCircle(center.getX(), center.getY(), p.getX(), p.getY(), squaredRadius))
                .collect(Collectors.toList());
    }

    private boolean isInsideCircle(double a, double b, double x, double y, double squaredRadius) {
        return Math.pow((x - a), 2) + Math.pow((y - b), 2) <= squaredRadius;
    }

    private boolean areAllAnalized(List<Point> points) {
        return points.stream().filter(p -> !p.isUsed()).count() == 0;
    }

    private Point getNext(List<Point> points) {
        List<Point> elegible = points.stream().filter(p -> !p.isUsed()).collect(Collectors.toList());
        return elegible.size() == 0 ? null : elegible.get(0);
    }

}
