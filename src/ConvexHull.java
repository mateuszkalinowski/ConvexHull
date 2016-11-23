import geometry.*;

import java.util.Random;

/**
 * Created by Mateusz on 21.11.2016.
 * Project ConvexHull
 */
public class ConvexHull {
    public static void main(String[] args) {
        Surface mainSurface = new Surface();
        Random rnd = new Random();
       /* for(int i = 0; i < 5; i ++) {
            int x = rnd.nextInt(1000);
            int y = rnd.nextInt(1000);
            mainSurface.add(new Point(x,y));
        }*/
       /* mainSurface.add(new Point(12,4));
        mainSurface.add(new Point(8,5));
        mainSurface.add(new Point(17,9));
        mainSurface.add(new Point(6,2));
        mainSurface.add(new Point(6,6));
        mainSurface.add(new Point(5,8));
        mainSurface.add(new Point(10,8));
        mainSurface.add(new Point(18,4));
        mainSurface.add(new Point(12,11));
        mainSurface.add(new Point(16,7));
        mainSurface.add(new Point(10,1));*/
        mainSurface.add(new Point(1,1));
        mainSurface.add(new Point(2,1));
        mainSurface.add(new Point(2,2));
        mainSurface.add(new Point(1,2));
        Polygon convexHull = mainSurface.createConvexHull();
       // System.out.println(mainSurface);

        System.out.println(convexHull);
    }
}
