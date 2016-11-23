package geometry;

import java.util.ArrayList;

/**
 * Created by Mateusz on 21.11.2016.
 * Project core.ConvexHull
 */
public class Polygon {
    public Polygon(){
        points = new ArrayList<>();
    }
    public Polygon(ArrayList<Point> points) {
        this.points = points;
    }
    public void addPoint(Point e) {
        points.add(e);
    }

    @Override
    public String toString() {
        String polygon = "Polygon:\n";
        for(Point e : points) {
            polygon+=e+System.lineSeparator();
        }
        return polygon;
    }
    public ArrayList<Point> returnPointsAsArray(){
        return points;
    }

    private ArrayList<Point> points;
}
