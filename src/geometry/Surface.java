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
        Polygon convexHull = new Polygon();
        //SZUKANIE PUNKTU NAJBARDZIEJ NA PRAWO
        if(points.isEmpty())
            return null;
        Point first = points.iterator().next();
        for(Point e: points) {
            if(e.getX()>first.getX())
                first = e;
        }
        convexHull.addPoint(first);
        Point previous = first;
        Point candidate = null;
        double angle = 0;
        int counter = 1;
        boolean isBigger = false;
        do {
            counter++;
            System.out.println("Poprzedni Punkt" + previous);
            for (Point e : points) {
                if(candidate==null) {
                    System.out.println("Pierwszy kandydat" + e);
                    candidate=e;
                    int x1 = candidate.getX();
                    int x2 = previous.getX();
                    int y1 = candidate.getY();
                    int y2 = previous.getY();
                    double distance = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
                    System.out.println("Dystans" + distance);

                    if(!isBigger) {
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

                    }
                    else {
                        if (x1 > x2 && y1 > y2) {
                            angle = 2+ (y1 - y2) / distance;
                        }
                        if (x1 < x2 && y1 > y2) {
                            angle = 3 + (x2 - x1) / distance;
                        }
                        if (x1 < x2 && y1 < y2) {
                            angle = (y2 - y1) / distance;
                        }
                        if (x1 > x2 && y1 < y2) {
                            angle = 1 + (x1 - x2) / distance;
                        }

                    }
                }
                else if(e.getX()!=previous.getX() && e.getY()!=previous.getY()) {
                    Double newangle=null;
                    int x1 = e.getX();
                    int x2 = previous.getX();
                    int y1 = e.getY();
                    int y2 = previous.getY();
                    double distance = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
                    System.out.println(previous.getY());
                    System.out.println(y1);
                    if(!isBigger) {
                        if (x1 > x2 && y1 > y2) {
                            newangle = (y1 - y2) / distance;
                        }
                        if (x1 < x2 && y1 > y2) {
                            newangle = 1 + (x2 - x1) / distance;
                        }
                        if (x1 < x2 && y1 < y2) {
                            newangle = 2 + (y2 - y1) / distance;
                        }
                        if (x1 > x2 && y1 < y2) {
                            newangle = 3 + (x1 - x2) / distance;
                        }

                        if (newangle < angle) {
                            angle = newangle;
                            candidate = e;
                        }
                    }
                    else {
                        if (x1 > x2 && y1 > y2) {
                            newangle = 2+ (y1 - y2) / distance;
                        }
                        if (x1 < x2 && y1 > y2) {
                            newangle = 3 + (x2 - x1) / distance;
                        }
                        if (x1 < x2 && y1 < y2) {
                            newangle = (y2 - y1) / distance;
                        }
                        if (x1 > x2 && y1 < y2) {
                            newangle = 1 + (x1 - x2) / distance;
                        }

                        if (newangle < angle) {
                            angle = newangle;
                            candidate = e;
                        }
                    }
                }

            }
            if(candidate.getY()<previous.getY())
                isBigger=true;
            else
                isBigger=false;
            convexHull.addPoint(candidate);
            previous = candidate;
            candidate=null;
        }while(counter<6);

        return convexHull;
    }

    private Set<Point> points;
}
