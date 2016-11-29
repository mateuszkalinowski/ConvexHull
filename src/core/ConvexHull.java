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
        EventQueue.invokeLater(() -> {
            mainFrame = new MainWindow();
            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mainFrame.setVisible(true);
        });
    }
    public static Polygon convexHull;
    public static Surface mainSurface;
    public static MainWindow mainFrame;

    public static int maxRange = 20000;
}
