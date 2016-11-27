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
        if(!points.contains(e)) {
                points.add(e);
        }
    }

    @Override
    public String toString() {
        String polygon = "Polygon:\n";
        for(Point e : points) {
            polygon+=e+System.lineSeparator();
        }
        return polygon;
    }
    public double getArea(){
        double area = 0;
        if(points.size()<=2)
            return 0;
        Point first = points.get(0);
        for(int i = 1; i < points.size()-1;i++) {
            double a = Math.sqrt((first.getX() - points.get(i).getX()) * (first.getX() - points.get(i).getX()) + (first.getY() - points.get(i).getY()) * (first.getY() - points.get(i).getY()));
            double b = Math.sqrt((first.getX() - points.get(i+1).getX()) * (first.getX() - points.get(i+1).getX()) + (first.getY() - points.get(i+1).getY()) * (first.getY() - points.get(i+1).getY()));
            double c = Math.sqrt((points.get(i+1).getX() - points.get(i).getX()) * (points.get(i+1).getX() - points.get(i).getX()) + (points.get(i+1).getY() - points.get(i).getY()) * (points.get(i+1).getY() - points.get(i).getY()));
            double p = (a+b+c)/2.0;
            area += Math.sqrt(p*(p-a)*(p-b)*(p-c));
        }
        return area;
    }
   /* public boolean isInside(double x,double y) {
        if(points.size()>2) {
            if(points.contains(new Point((int)x,(int) y))) {
                return true;
            }

            int minY = points.get(0).getY();
            for(Point toCheck : points) {
                if(toCheck.getY()<minY)
                    minY = toCheck.getY();
            }

            int howManyTimesPasses = 0;
            
            for(int i = 0; i < points.size();i++) {
                if(x==points.get(i).getX() && y<points.get(i).getY())
                    howManyTimesPasses++;
            }

            for(int i = 0; i < points.size()-1;i++) {
                Point first = points.get(i);
                Point second = points.get(i+1);
                if(first.getY()==second.getY()) {
                    int onLeft = first.getX()<second.getX() ? first.getX() : second.getX();
                    int onRight = first.getX()>second.getX() ? first.getX() : second.getX();
                    if(x>onLeft&&x<onRight&&y<=first.getY()) {
                        howManyTimesPasses++;
                    }
                }*/
                /*if(first.getX()==second.getX()) {
                    int onTop = first.getY()>second.getY() ? first.getY() : second.getY();
                    int onBottom = first.getY()<second.getY() ? first.getY() : second.getY();
                    if(x==first.getX()) {
                        if(y<onTop && y>onBottom) {
                            howManyTimesPasses++;
                        }
                    }
                }*/
              /*  else {
                    Point upper = first.getY() > second.getY() ? first : second;
                    Point lower = first.getY() < second.getY() ? first : second;
                    if(lower.getX()<upper.getX()) {
                        if(x>lower.getX() && x<upper.getX()) {
                            if(y<=lower.getY()) {
                                howManyTimesPasses++;
                            }
                            else if(y<=upper.getY()) {
                                double a1 = (y - lower.getY()) / (x - lower.getX());
                                double a2 = (upper.getY() - y) / (upper.getX() - x);
                                if (a1 < a2)
                                    howManyTimesPasses++;
                                if (a1 == a2) {
                                        howManyTimesPasses++;
                                }
                            }
                        }
                    }
                   if(upper.getX()<lower.getX()) {
                        if(x>upper.getX() && x<lower.getX()) {
                            if(y<=lower.getY()) {
                                howManyTimesPasses++;
                            }
                            else if(y<=upper.getY()) {
                                if(x == upper.getX()) {
                                    howManyTimesPasses++;
                                }
                                else {
                                    double a1 = (y - lower.getY()) / (x - lower.getX());
                                    double a2 = (upper.getY() - y) / (upper.getX() - x);
                                    if (a1 > a2)
                                        howManyTimesPasses++;
                                    if (a1 == a2) {
                                        howManyTimesPasses++;
                                    }
                                }
                            }
                        }

                    }

                }
            }
            Point first = points.get(points.size()-1);
            Point second = points.get(0);
            if(first.getY()==second.getY()) {
                int onLeft = first.getX()<second.getX() ? first.getX() : second.getX();
                int onRight = first.getX()>second.getX() ? first.getX() : second.getX();
                if(x>onLeft&&x<onRight&&y<=first.getY()) {
                    howManyTimesPasses++;
                }
            }
                /*if(first.getX()==second.getX()) {
                    int onTop = first.getY()>second.getY() ? first.getY() : second.getY();
                    int onBottom = first.getY()<second.getY() ? first.getY() : second.getY();
                    if(x==first.getX()) {
                        if(y<onTop && y>onBottom) {
                            howManyTimesPasses++;
                        }
                    }
                }*/
        /*    else {
                Point upper = first.getY() > second.getY() ? first : second;
                Point lower = first.getY() < second.getY() ? first : second;
                if(lower.getX()<upper.getX()) {
                    if(x>lower.getX() && x<upper.getX()) {
                        if(y<=lower.getY()) {
                            howManyTimesPasses++;
                        }
                        else if(y<=upper.getY()) {
                            double a1 = (y - lower.getY()) / (x - lower.getX());
                            double a2 = (upper.getY() - y) / (upper.getX() - x);
                            if (a1 < a2)
                                howManyTimesPasses++;
                            if (a1 == a2) {
                                howManyTimesPasses++;
                            }
                        }
                    }
                }
                if(upper.getX()<lower.getX()) {
                    if(x>upper.getX() && x<lower.getX()) {
                        if(y<=lower.getY()) {
                            howManyTimesPasses++;
                        }
                        else if(y<=upper.getY()) {
                            if(x == upper.getX()) {
                                howManyTimesPasses++;
                            }
                            else {
                                double a1 = (y - lower.getY()) / (x - lower.getX());
                                double a2 = (upper.getY() - y) / (upper.getX() - x);
                                if (a1 > a2)
                                    howManyTimesPasses++;
                                if (a1 == a2) {
                                    howManyTimesPasses++;
                                }
                            }
                        }
                    }

                }

            }


            if(howManyTimesPasses%2==1)
                return true;
            else
                return false;

        }
        return false;
    }*/
    public ArrayList<Point> returnPointsAsArray(){
        return points;
    }

    private ArrayList<Point> points;
}
