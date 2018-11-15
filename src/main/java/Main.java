import dbscan.DBScan;
import models.Circle;
import models.Point;
import models.Polygon;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args){
        Instant start = Instant.now();
        List<Point> points = new ArrayList<>();

        points.add(new Point(-2,-1));
        points.add(new Point(-1,-2));
        points.add(new Point(1,1));
        points.add(new Point(2,2));
        points.add(new Point(3,3));


        points.add(new Point(7,10));
        points.add(new Point(8,8));
        points.add(new Point(10,9));



        points.add(new Point(101,101));
        points.add(new Point(102,102));
        points.add(new Point(103,103));
        points.add(new Point(104,104));

        DBScan clusterizer = new DBScan(new Circle(2), 3);
        List<Polygon> clusters = clusterizer.clusterize(points);

        System.out.println();
        System.out.println("CLuster count: "+ clusters.size());


        Instant end = Instant.now();

        System.out.println(Duration.between(start,end).toMillis());

    }


}
