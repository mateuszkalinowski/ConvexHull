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
        g2.fillRect(0,0,getWidth(),getHeight());
        g2.setColor(Color.BLACK);
        g2.drawLine(margin,getHeight()/2,getWidth()-margin,getHeight()/2);
        g2.drawLine(getWidth()/2,margin,getWidth()/2,getHeight()-margin);
        int maxNorth = Math.abs(ConvexHull.mainSurface.getMaxNorth().getY());
        int maxSouth = Math.abs(ConvexHull.mainSurface.getMaxSouth().getY());
        int maxEast = Math.abs(ConvexHull.mainSurface.getMaxEast().getX());
        int maxWest = Math.abs(ConvexHull.mainSurface.getMaxWest().getX());

        int width = (maxEast > maxWest ? maxEast : maxWest) + 1;
      //  if(width%2!=0)
      //      width+=1;
        int height = (maxNorth > maxSouth ? maxNorth : maxSouth) + 1;
      //  if(height%2!=0)
       //     width+=1;
        for(int i = -width; i <= width;i++) {
            double x= (getWidth()-2*margin)/2.0 + (getWidth()-2*margin)/2.0*(i/(width*1.0)) + margin;
            g2.drawLine((int)x,getHeight()/2-5,(int)x,getHeight()/2+5);
        }

        for(int i = -height; i <= height;i++) {
            double y = (getHeight()-2*margin)/2.0 - (getHeight()-2*margin)/2.0*(i/(height*1.0)) + margin;
            g2.drawLine(getWidth()/2-5,(int)y,getWidth()/2+5,(int)y);
        }



        g2.setStroke(new BasicStroke(2));
        for( Point e: ConvexHull.mainSurface.returnPointsAsArray()) {
            double x = (getWidth()-2*margin)/2.0 + (getWidth()-2*margin)/2.0*(e.getX()/(width*1.0)) + margin;
            double y = (getHeight()-2*margin)/2.0 - (getHeight()-2*margin)/2.0*(e.getY()/(height*1.0)) + margin;
         //   g2.fillRect((int) (x - pozpix/2.0),(int) (y-pionpix/2.0),(int) pozpix,(int) pionpix );\
            g2.drawLine((int)x,(int)y-10,(int)x,(int)y+10);
            g2.drawLine((int)x-10,(int)y,(int)x+10,(int)y);
        }
        g2.setColor(Color.RED);
        ArrayList<Point> polygonToDisplay = ConvexHull.convexHull.returnPointsAsArray();
        g2.setStroke(new BasicStroke(2));
        for(int i = 1; i < polygonToDisplay.size();i++){
            double x1 = (getWidth()-2*margin)/2.0 + (getWidth()-2*margin)/2.0*(polygonToDisplay.get(i).getX()/(width*1.0)) + margin;
            double y1 = (getHeight()-2*margin)/2.0 - (getHeight()-2*margin)/2.0*(polygonToDisplay.get(i).getY()/(height*1.0)) + margin;

            double x2 = (getWidth()-2*margin)/2.0 + (getWidth()-2*margin)/2.0*(polygonToDisplay.get(i-1).getX()/(width*1.0)) + margin;
            double y2 = (getHeight()-2*margin)/2.0 - (getHeight()-2*margin)/2.0*(polygonToDisplay.get(i-1).getY()/(height*1.0)) + margin;
            g2.drawLine((int)x1,(int)y1,(int)x2,(int)y2);
        }
        double x1 = (getWidth()-2*margin)/2.0 + (getWidth()-2*margin)/2.0*(polygonToDisplay.get(0).getX()/(width*1.0)) + margin;
        double y1 = (getHeight()-2*margin)/2.0 - (getHeight()-2*margin)/2.0*(polygonToDisplay.get(0).getY()/(height*1.0)) + margin;


        double x2 = (getWidth()-2*margin)/2.0 + (getWidth()-2*margin)/2.0*(polygonToDisplay.get(polygonToDisplay.size()-1).getX()/(width*1.0)) + margin;
        double y2 = (getHeight()-2*margin)/2.0 - (getHeight()-2*margin)/2.0*(polygonToDisplay.get(polygonToDisplay.size()-1).getY()/(height*1.0)) + margin;
        g2.drawLine((int)x1,(int)y1,(int)x2,(int)y2);



       // width = 20;
       // height = 20;

        /*double pozpix =  getWidth()/(width*2.0);
        double pionpix = getHeight()/(height*2.0);
        for( Point e: ConvexHull.mainSurface.returnPointsAsArray()) {
            double x = getWidth()/2.0 + getWidth()/2.0*(e.getX()/(width*1.0));
            double y = getHeight()/2.0 - getHeight()/2.0*(e.getY()/(height*1.0));
            g2.fillRect((int) (x -pozpix/2.0),(int) (y-pionpix/2.0),(int) pozpix,(int) pionpix );
        }*/
  /*      g2.setColor(Color.RED);
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
*/

    }
    private int margin;

}
