package gui;

import core.ConvexHull;
import geometry.*;
import geometry.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static core.ConvexHull.convexHull;
import static core.ConvexHull.mainSurface;

/**
 * Created by Mateusz on 23.11.2016.
 * Project ConvexHull
 */
public class MainWindow extends JFrame {
    public MainWindow(){
        setTitle("ConvexHull Generator");
        setSize(800,450);
        setLocationRelativeTo(null);

        JPanel mainBorderLayout = new JPanel(new BorderLayout());
        JPanel rightBorderLayout = new JPanel(new BorderLayout());

        JPanel rightGridLayout = new JPanel(new GridLayout(2,1));
        rightBorderLayout.add(rightGridLayout,BorderLayout.CENTER);
        mainBorderLayout.add(rightBorderLayout, BorderLayout.EAST);

        JPanel rightUpBorderLayout = new JPanel(new BorderLayout());
        JPanel rightDownBorderLayout = new JPanel(new BorderLayout());

        rightGridLayout.add(rightUpBorderLayout);
        rightGridLayout.add(rightDownBorderLayout);
        JLabel inputPointsLabel = new JLabel("Punkty:",SwingConstants.CENTER);
        rightUpBorderLayout.add(inputPointsLabel,BorderLayout.NORTH);

        final DefaultListModel<String> pointsListModel = new DefaultListModel<>();
        pointsList = new JList<>();
        pointsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pointsList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        pointsList.setVisibleRowCount(-1);
        pointsList = new JList<>(pointsListModel);
        pointsList.setPrototypeCellValue("X:   Y:   ");
        JScrollPane pointListScrollPane = new JScrollPane(pointsList);

        JPanel rightDownGridLayout = new JPanel(new GridLayout(7,2));

        JTextField xValue  = new JTextField();
        JTextField yValue = new JTextField();

        rightDownGridLayout.add(new JLabel("X:",SwingConstants.CENTER));
        rightDownGridLayout.add(xValue);
        rightDownGridLayout.add(new JLabel("Y:",SwingConstants.CENTER));
        rightDownGridLayout.add(yValue);

        JButton addPointButton = new JButton("Dodaj");
        addPointButton.addActionListener(e ->{
            try {
                int x = Integer.parseInt(xValue.getText());
                int y = Integer.parseInt(yValue.getText());
                if(!mainSurface.contains(new Point(x,y))) {
                    pointsListModel.addElement("X: " + x + ", Y: " + y);
                    xValue.setText("");
                    yValue.setText("");
                    mainSurface.add(new Point(x, y));
                    repaint();
                }
            }
            catch (Exception ignored){
                JOptionPane.showMessageDialog(null, "Wprowadzone współrzędne są niepoprawne!", "Błąd" +
                        " danych",JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton removePointButton = new JButton(("Usuń"));
        removePointButton.addActionListener(e -> {
            int i = pointsList.getSelectedIndex();
            if(i>=0) {
                String toRemove = pointsListModel.get(i);
                String x = toRemove.split(",")[0];
                String y = toRemove.split(",")[1];
                x = x.substring(3,x.length());
                y = y.substring(4,y.length());
                pointsListModel.removeElementAt(i);
                mainSurface.remove(new Point(Integer.parseInt(x),Integer.parseInt(y)));
                repaint();
            }
        });
        rightDownGridLayout.add(addPointButton);
        rightDownGridLayout.add(removePointButton);

        JLabel randomPointsRangeLabel = new JLabel("Zakres :");
        JTextField randomPointsRange = new JTextField("100");
        JLabel pointsToRandomNumber = new JLabel("Ilość punktów:");
        JTextField randomPointsNumber = new JTextField("100");

        JButton randomizePointsButton = new JButton("Losuj");
        randomizePointsButton.addActionListener(e -> {
            try {
                Random rnd = new Random();
                for (int i = 0; i < Integer.parseInt(randomPointsNumber.getText()); i++) {
                    int x = rnd.nextInt(Integer.parseInt(randomPointsRange.getText())) - Integer.parseInt(randomPointsRange.getText()) / 2;
                    int y = rnd.nextInt(Integer.parseInt(randomPointsRange.getText())) - Integer.parseInt(randomPointsRange.getText()) / 2;
                    mainSurface.add(new geometry.Point(x, y));
                    pointsListModel.addElement("X: " + x + ", Y: " + y);
                }
                repaint();
            } catch (Exception ignored){
                JOptionPane.showMessageDialog(null, "Wprowadzone parametry wyszukiwania są niepoprawne!", "Błąd" +
                        " danych",JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton deleteAllPointsButton = new JButton("Usuń ");
        deleteAllPointsButton.addActionListener(e -> {
            mainSurface.removeAll();
            pointsListModel.removeAllElements();
            repaint();
        });

        rightDownGridLayout.add(new JLabel("Losowanie:"));
        rightDownGridLayout.add(new JLabel());
        rightDownGridLayout.add(randomPointsRangeLabel);
        rightDownGridLayout.add(randomPointsRange);
        rightDownGridLayout.add(pointsToRandomNumber);
        rightDownGridLayout.add(randomPointsNumber);
        rightDownGridLayout.add(randomizePointsButton);
        rightDownGridLayout.add(deleteAllPointsButton);

        rightDownBorderLayout.add(rightDownGridLayout);
        rightUpBorderLayout.add(pointListScrollPane,BorderLayout.CENTER);



        JButton testButton = new JButton("Generuj Otoczkę");
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pointsListModel.getSize()>=3) {
                    convexHull = mainSurface.createConvexHull();
                }
                repaint();
            }
        });
        rightBorderLayout.add(testButton,BorderLayout.SOUTH);

        Chart chart = new Chart();
        mainBorderLayout.add(chart);
        add(mainBorderLayout);
    }

    private JList pointsList;
}
