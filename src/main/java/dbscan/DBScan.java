package dbscan;

import models.AbstractGeometryFigure;
import models.Point;
import models.Polygon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class DBScan {

    private AbstractGeometryFigure geometryFigure;
    private int minPoints;

    public DBScan(AbstractGeometryFigure geometryFigure,int minPoints) {
        this.minPoints = minPoints;
        this.geometryFigure = geometryFigure;
    }

    public List<Polygon> clusterize(List<Point> points) {
        List<Polygon> clusters = new ArrayList<>();

        if (points.isEmpty() || points.size() < 2)
            return clusters;

        for(Point location : points){

            if (location.isUsed())
                continue;

            Polygon p = buildCluster(points, location);

            if (!p.getPoints().isEmpty())
                clusters.add(p);

        }

        return clusters;
    }

    private Polygon buildCluster(List<Point> points, Point location){
        Polygon  polygon = new Polygon();
        Queue<Point> queue = new LinkedList<>();

        queue.add(location);

        while (!queue.isEmpty()){

            Point center = queue.remove();
            center.setUsed();

            List<Point> nearPoints = this.geometryFigure.getPointsInside(points, center);

            if (nearPoints.size() >= this.minPoints) {
                polygon.addPoint(center);

                List<Point> candidates = nearPoints
                        .parallelStream()
                        .filter(p -> !queue.contains(p))
                        .filter(p -> !p.isUsed())
                        .collect(Collectors.toList());

                queue.addAll(candidates);

            }
        }

        return polygon;
    }

}
