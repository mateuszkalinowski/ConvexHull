package geometry;

import java.util.HashSet;
import java.util.Set;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

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
        Polygon convexHull = new Polygon();
        if(points.isEmpty())
            return null;
        Point first = points.iterator().next();
        for(Point e: points) {
            if(e.getX()>first.getX())
                first = e;
        }
        convexHull.addPoint(first);
        Point previous = first;

        Double previousAngle = 0.0;

        Double smallestAngle = null;


        int counter = 0;
        do {
            counter++;
            Integer x = null;
            Integer y = null;
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
                            System.out.println("Używam: 0q");
                        }
                        if (x1 < x2 && y1 > y2) {
                            angle = 1 + (x2 - x1) / distance;
                            System.out.println("Używam: 1q");
                        }
                        if (x1 < x2 && y1 < y2) {
                            angle = 2 + (y2 - y1) / distance;
                            System.out.println("Używam: 2q");
                        }
                        if (x1 > x2 && y1 < y2) {
                            angle = 3 + (x1 - x2) / distance;
                            System.out.println("Używam: 3q");
                        }
                        if(x1==x2) {
                            if(y1>y2) {
                                angle = 1.0;
                                System.out.println("Używam: 1kąt");
                            }
                            if(y1<y2) {
                                System.out.println("Używam: 3kąt");
                                angle = 3.0;
                            }
                        }
                        if(y1==y2) {
                            if(x1>x2) {
                                System.out.println("Używam: 4kąt");
                                angle = 0.0;
                            }
                            if(x1 < x2) {
                                System.out.println("Używam: 2kąt");
                                angle = 2.0;
                            }
                        }
                        if(x==null && y==null) {
                            smallestAngle = angle;
                            x = e.getX();
                            y = e.getY();
                        }
                        else {
                            if(angle<smallestAngle && angle>previousAngle) {
                                smallestAngle = angle;
                                x = e.getX();
                                y = e.getY();
                            }
                        }
                    }
                }
            previousAngle = smallestAngle;
            convexHull.addPoint(new Point(x,y));
            previous = new Point(x,y);

        }while(counter<3);

        return convexHull;
    }

    private Set<Point> points;
}
