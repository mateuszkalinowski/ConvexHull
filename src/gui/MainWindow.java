package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mateusz on 23.11.2016.
 * Project ConvexHull
 */
public class MainWindow extends JFrame {
    public MainWindow(){
        setTitle("ConvexHull Generator");
        setSize(800,450);

        JPanel mainBorderLayout = new JPanel(new BorderLayout());
        JPanel rightBorderLayout = new JPanel(new BorderLayout());
        mainBorderLayout.add(rightBorderLayout, BorderLayout.EAST);

        JButton testButton = new JButton("Test");
        rightBorderLayout.add(testButton);

        Chart chart = new Chart();
        mainBorderLayout.add(chart);
        add(mainBorderLayout);
    }
}
