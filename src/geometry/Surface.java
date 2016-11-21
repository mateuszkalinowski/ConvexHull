package geometry;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mateusz on 21.11.2016.
 * Project ConvexHull
 */
public class Surface {
    public Surface() {
        points = new HashSet<>();
    }
    public void add(Point point) {
        points.add(point);
    }
    public void add(Point[] points){
        for(Point e: points)
            this.points.add(e);
    }
    public void remove(Point point) {
        points.remove(point);
    }

    @Override
    public String toString() {
        String result = "";
        result+="Surface:\n";

        for(Point e: points) {
            result+=e.toString();
            result+="\n";
        }
        return result;
    }
    public Polygon createConvexHull(){
        
        return new Polygon();
    }

    private Set<Point> points;
}
