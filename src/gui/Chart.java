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
        margin = 15;
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.BLACK);
        g2.drawLine(margin, getHeight() / 2, getWidth() - margin, getHeight() / 2);
        g2.drawLine(getWidth() / 2, margin, getWidth() / 2, getHeight() - margin);
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(getWidth()-margin,getHeight()/2,getWidth()-margin-margin/2,getHeight()/2-margin/2);
        g2.drawLine(getWidth()-margin-margin/2,getHeight()/2+margin/2,getWidth()-margin,getHeight()/2);

        g2.drawLine(getWidth()/2,margin,getWidth()/2-margin/2,margin+margin/2);
        g2.drawLine(getWidth()/2,margin,getWidth()/2+margin/2,margin+margin/2);
        g2.setStroke(new BasicStroke(1));
        int width = 0,height = 0;
        if(!ConvexHull.mainSurface.isEmpty()) {
            int maxNorth = Math.abs(ConvexHull.mainSurface.getMaxNorth().getY());
            int maxSouth = Math.abs(ConvexHull.mainSurface.getMaxSouth().getY());
            int maxEast = Math.abs(ConvexHull.mainSurface.getMaxEast().getX());
            int maxWest = Math.abs(ConvexHull.mainSurface.getMaxWest().getX());

            width = (maxEast > maxWest ? maxEast : maxWest) + 2;
            height = (maxNorth > maxSouth ? maxNorth : maxSouth) + 2;
            if(width>height)height = width;
            if(height>width)width  = height;
            if(width<25) {
                for (int i = -width+1; i <= width-1; i++) {
                    double x = (getWidth() - 2 * margin) / 2.0 + (getWidth() - 2 * margin) / 2.0 * (i / (width * 1.0)) + margin;
                    g2.drawLine((int) x, getHeight() / 2 - 5, (int) x, getHeight() / 2 + 5);
                    if(i==1) {
                        g2.drawString("1",(int) x-3,getHeight()/2+20);
                    }
                }
            }
            else {
                for (int i = 0; i <= width; i+=width/5) {
                    double x = (getWidth() - 2 * margin) / 2.0 + (getWidth() - 2 * margin) / 2.0 * (i / (width * 1.0)) + margin;
                    g2.drawLine((int) x, getHeight() / 2 - 5, (int) x, getHeight() / 2 + 5);
                    if(i==width/5) {
                        g2.drawString(width/5+"",(int) x-3,getHeight()/2+20);
                    }
                }
                for (int i = 0; i >= -width; i-=width/5) {
                    double x = (getWidth() - 2 * margin) / 2.0 + (getWidth() - 2 * margin) / 2.0 * (i / (width * 1.0)) + margin;
                    g2.drawLine((int) x, getHeight() / 2 - 5, (int) x, getHeight() / 2 + 5);
                }
            }
            if(height<25) {
                for (int i = -height+1; i <= height-1; i++) {
                    double y = (getHeight() - 2 * margin) / 2.0 - (getHeight() - 2 * margin) / 2.0 * (i / (height * 1.0)) + margin;
                    g2.drawLine(getWidth() / 2 - 5, (int) y, getWidth() / 2 + 5, (int) y);
                    if(i==1) {
                        g2.drawString("1",getWidth()/2+10,(int) y+3);
                    }
                }
            }
            else {
                for (int i = 0; i <= height; i+=height/5) {
                    double y = (getHeight() - 2 * margin) / 2.0 - (getHeight() - 2 * margin) / 2.0 * (i / (height * 1.0)) + margin;
                    g2.drawLine(getWidth() / 2 - 5, (int) y, getWidth() / 2 + 5, (int) y);
                    if(i==height/5) {
                        g2.drawString(height/5+"",getWidth()/2+10,(int) y+3);
                    }
                }
                for (int i = 0; i >= -height; i-=height/5) {
                    double y = (getHeight() - 2 * margin) / 2.0 - (getHeight() - 2 * margin) / 2.0 * (i / (height * 1.0)) + margin;
                    g2.drawLine(getWidth() / 2 - 5, (int) y, getWidth() / 2 + 5, (int) y);
                }
            }


            g2.setStroke(new BasicStroke(2));
            for (Point e : ConvexHull.mainSurface.returnPointsAsArray()) {
                double x = (getWidth() - 2 * margin) / 2.0 + (getWidth() - 2 * margin) / 2.0 * (e.getX() / (width * 1.0)) + margin;
                double y = (getHeight() - 2 * margin) / 2.0 - (getHeight() - 2 * margin) / 2.0 * (e.getY() / (height * 1.0)) + margin;
                //   g2.fillRect((int) (x - pozpix/2.0),(int) (y-pionpix/2.0),(int) pozpix,(int) pionpix );\
                g2.drawLine((int) x, (int) y - 10, (int) x, (int) y + 10);
                g2.drawLine((int) x - 10, (int) y, (int) x + 10, (int) y);
            }
        }
        g2.setColor(Color.RED);
        if (ConvexHull.convexHull!= null) {
            ArrayList<Point> polygonToDisplay = ConvexHull.convexHull.returnPointsAsArray();
        g2.setStroke(new BasicStroke(2));
        for (int i = 1; i < polygonToDisplay.size(); i++) {
            double x1 = (getWidth() - 2 * margin) / 2.0 + (getWidth() - 2 * margin) / 2.0 * (polygonToDisplay.get(i).getX() / (width * 1.0)) + margin;
            double y1 = (getHeight() - 2 * margin) / 2.0 - (getHeight() - 2 * margin) / 2.0 * (polygonToDisplay.get(i).getY() / (height * 1.0)) + margin;

            double x2 = (getWidth() - 2 * margin) / 2.0 + (getWidth() - 2 * margin) / 2.0 * (polygonToDisplay.get(i - 1).getX() / (width * 1.0)) + margin;
            double y2 = (getHeight() - 2 * margin) / 2.0 - (getHeight() - 2 * margin) / 2.0 * (polygonToDisplay.get(i - 1).getY() / (height * 1.0)) + margin;
            g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        }
        double x1 = (getWidth() - 2 * margin) / 2.0 + (getWidth() - 2 * margin) / 2.0 * (polygonToDisplay.get(0).getX() / (width * 1.0)) + margin;
        double y1 = (getHeight() - 2 * margin) / 2.0 - (getHeight() - 2 * margin) / 2.0 * (polygonToDisplay.get(0).getY() / (height * 1.0)) + margin;


        double x2 = (getWidth() - 2 * margin) / 2.0 + (getWidth() - 2 * margin) / 2.0 * (polygonToDisplay.get(polygonToDisplay.size() - 1).getX() / (width * 1.0)) + margin;
        double y2 = (getHeight() - 2 * margin) / 2.0 - (getHeight() - 2 * margin) / 2.0 * (polygonToDisplay.get(polygonToDisplay.size() - 1).getY() / (height * 1.0)) + margin;
        g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
    }
    }
    private int margin;

}
