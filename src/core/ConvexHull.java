package core;

import geometry.*;
import geometry.Point;
import geometry.Polygon;
import gui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Mateusz on 21.11.2016.
 * Project core.ConvexHull
 */
public class ConvexHull {
    public static void main(String[] args) {
        mainSurface = new Surface();
        Random rnd = new Random();
       /* for(int i = 0; i < 5; i ++) {
            int x = rnd.nextInt(1000);
            int y = rnd.nextInt(1000);
            mainSurface.add(new Point(x,y));
        }*/
        mainSurface.add(new Point(12,4));
        mainSurface.add(new Point(8,5));
        mainSurface.add(new Point(17,9));
        mainSurface.add(new Point(6,2));
        mainSurface.add(new Point(6,6));
        mainSurface.add(new Point(5,8));
        mainSurface.add(new Point(10,8));
        mainSurface.add(new Point(18,4));
        mainSurface.add(new Point(12,11));
        mainSurface.add(new Point(16,7));
        mainSurface.add(new Point(10,1));
        convexHull = mainSurface.createConvexHull();
       // System.out.println(mainSurface);
       // System.out.println(convexHull);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainFrame = new MainWindow();
                mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                mainFrame.setVisible(true);
            }
        });
    }
    public static Polygon convexHull;
    public static Surface mainSurface;
    public static MainWindow mainFrame;
}
