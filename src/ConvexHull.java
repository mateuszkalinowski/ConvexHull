import geometry.Point;
import geometry.Surface;

import java.util.Random;

/**
 * Created by Mateusz on 21.11.2016.
 * Project ConvexHull
 */
public class ConvexHull {
    public static void main(String[] args) {
        Surface mainSurface = new Surface();
        Random rnd = new Random();
        for(int i = 0; i < 1000; i ++) {
            int x = rnd.nextInt(1000);
            int y = rnd.nextInt(1000);
            mainSurface.add(new Point(x,y));
        }
        System.out.println(mainSurface);
    }
}
