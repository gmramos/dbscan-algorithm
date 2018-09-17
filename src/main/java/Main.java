import dbscan.DBScan;
import models.Point;

import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args){
        Instant start = Instant.now();
        List<Point> points = new ArrayList<Point>();

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

        DBScan clusterizer = new DBScan(7, 2);
        Map<Integer, List<Point>> clusters = clusterizer.clusterize(points);

        System.out.println();
        System.out.println("CLuster count: "+ clusters.size());

        clusters.forEach( (k,v) -> {
            System.out.println();
            System.out.println(v.size());
            printCenter(v);
        });

        Instant end = Instant.now();

        System.out.println(Duration.between(start,end).toMillis());

    }

    public static void printCenter(List<Point> sinfo){
        float x = sinfo.get(0).getLatitude(), y=sinfo.get(0).getLongitude();

        for (int i =1 ; i< sinfo.size(); i++){
            x = (x + sinfo.get(i).getLatitude())/2;
            y = (y + sinfo.get(i).getLongitude())/2;
        }

        System.out.println(x + " " + y );
    }

}
