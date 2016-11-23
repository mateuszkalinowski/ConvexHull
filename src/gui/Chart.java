package gui;

import core.ConvexHull;
import geometry.*;
import geometry.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Mateusz on 23.11.2016.
 * Project ConvexHull
 */
public class Chart extends JPanel {
    public Chart(){

    }
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0,0,getWidth(),getHeight());
        g2.setColor(Color.BLACK);
        g2.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
        g2.drawLine(getWidth()/2,0,getWidth()/2,getHeight());
        int maxNorth = 50;
        int maxSouth = 50;
        int maxEast = 50;
        int maxWest = 50;



        int width = 20;
        int height = 20;

        double pozpix =  getWidth()/(width*2.0);
        double pionpix = getHeight()/(height*2.0);
        for( Point e: ConvexHull.mainSurface.returnPointsAsArray()) {
            double x = getWidth()/2.0 + getWidth()/2.0*(e.getX()/(width*1.0));
            double y = getHeight()/2.0 - getHeight()/2.0*(e.getY()/(height*1.0));
            g2.fillRect((int) (x -pozpix/2.0),(int) (y-pionpix/2.0),(int) pozpix,(int) pionpix );
        }
        ArrayList<Point> polygonToDisplay = ConvexHull.convexHull.returnPointsAsArray();
        g2.setStroke(new BasicStroke(5));
        for(int i = 1; i < polygonToDisplay.size();i++){
            double x1 = getWidth()/2.0 + getWidth()/2.0*(polygonToDisplay.get(i).getX()/(width*1.0));
            double y1 = getHeight()/2.0 - getHeight()/2.0*(polygonToDisplay.get(i).getY()/(height*1.0));

            double x2 = getWidth()/2.0 + getWidth()/2.0*(polygonToDisplay.get(i-1).getX()/(width*1.0));
            double y2 = getHeight()/2.0 - getHeight()/2.0*(polygonToDisplay.get(i-1).getY()/(height*1.0));
            g2.drawLine((int)x1,(int)y1,(int)x2,(int)y2);
        }
        double x1 = getWidth()/2.0 + getWidth()/2.0*(polygonToDisplay.get(0).getX()/(width*1.0));
        double y1 = getHeight()/2.0 - getHeight()/2.0*(polygonToDisplay.get(0).getY()/(height*1.0));

        double x2 = getWidth()/2.0 + getWidth()/2.0*(polygonToDisplay.get(polygonToDisplay.size()-1).getX()/(width*1.0));
        double y2 = getHeight()/2.0 - getHeight()/2.0*(polygonToDisplay.get(polygonToDisplay.size()-1).getY()/(height*1.0));
        g2.drawLine((int)x1,(int)y1,(int)x2,(int)y2);


    }

}
