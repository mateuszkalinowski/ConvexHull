package geometry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

/**
 * Created by Mateusz on 21.11.2016.
 * Project core.ConvexHull
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
    public void removeAll(){
        points.clear();
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
    public boolean isEmpty(){
        return points.isEmpty();
    }
    public boolean contains(Point e){
        if(points.contains(e))
            return true;
        return false;
    }
    public Point getMaxNorth(){
        if(points==null)
            return null;
        Point toReturn = points.iterator().next();
        for(Point e : points) {
            if(e.getY()<toReturn.getY())
                toReturn = e;
        }
        return toReturn;
    }
    public Point getMaxSouth(){
        if(points==null)
            return null;
        Point toReturn = points.iterator().next();
        for(Point e : points) {
            if(e.getY()>toReturn.getY())
                toReturn=e;
        }
        return toReturn;
    }
    public Point getMaxEast(){
        if(points==null)
            return null;
        Point toReturn = points.iterator().next();
        for(Point e : points) {
            if(e.getX()>toReturn.getX())
                toReturn = e;
        }
        return toReturn;
    }
    public Point getMaxWest(){
        if(points==null)
            return null;
        Point toReturn = points.iterator().next();
        for(Point e : points) {
            if(e.getX()<toReturn.getX())
                toReturn = e;
        }
        return toReturn;
    }


    public ArrayList<Point> returnPointsAsArray() {
        ArrayList<Point> toReturn = new ArrayList<>();
        for(Point e : points)
            toReturn.add(e);
        return toReturn;
    }

    public Polygon createConvexHull(){
        Polygon convexHull = new Polygon();
        if(points.isEmpty())
            return null;
        Point first = points.iterator().next();
        for(Point e: points) {
            if(e.getX()>first.getX())
                first = e;
        }
        convexHull.addPoint(first);
        int firstx = first.getX();
        int firsty = first.getY();
        Point previous = first;

        Double previousAngle = 0.0;

        Double smallestAngle = null;

        Integer x = null;
        Integer y = null;
        int counter = 0;
        do {
            counter++;
                x = null;
                y = null;
                for (Point e : points) {
                    Double angle = null;
                    if(!e.equals(previous)) {
                        int x1 = e.getX();
                        int x2 = previous.getX();
                        int y1 = e.getY();
                        int y2 = previous.getY();
                        double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
                        if (x1 > x2 && y1 > y2) {
                            angle = (y1 - y2) / distance;
                        }
                        if (x1 < x2 && y1 > y2) {
                            angle = 1 + (x2 - x1) / distance;
                        }
                        if (x1 < x2 && y1 < y2) {
                            angle = 2 + (y2 - y1) / distance;
                        }
                        if (x1 > x2 && y1 < y2) {
                            angle = 3 + (x1 - x2) / distance;
                        }
                        if(x1==x2) {
                            if(y1>y2) {
                                angle = 1.0;
                            }
                            if(y1<y2) {
                                angle = 3.0;
                            }
                        }
                        if(y1==y2) {
                            if(x1>x2) {
                                angle = 0.0;
                            }
                            if(x1 < x2) {
                                angle = 2.0;
                            }
                        }
                        if(x==null && y==null && angle>previousAngle) {
                            smallestAngle = angle;
                            x = e.getX();
                            y = e.getY();
                        }
                        else if (x!=null && y!=null && previousAngle!=null) {
                            if(angle<smallestAngle && angle>=previousAngle) {
                                smallestAngle = angle;
                                x = e.getX();
                                y = e.getY();
                            }
                        }
                    }
                }
            if(x==null && y==null) {
                previousAngle=0.0;
            }
            else {
                if(x==firstx&&y==firsty)
                    break;
                previousAngle = smallestAngle;
                convexHull.addPoint(new Point(x, y));
                previous = new Point(x, y);
            }

        }while(true);

        return convexHull;
    }

    private Set<Point> points;
}
