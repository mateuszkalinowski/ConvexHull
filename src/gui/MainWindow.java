package gui;

import core.ConvexHull;
import geometry.*;
import geometry.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static core.ConvexHull.convexHull;
import static core.ConvexHull.mainSurface;

/**
 * Created by Mateusz on 23.11.2016.
 * Project ConvexHull
 */
public class MainWindow extends JFrame {
    public MainWindow(){
        setTitle("ConvexHull Generator");
        setSize(900,650);
        setLocationRelativeTo(null);

        JMenuBar mainMenu = new JMenuBar();
        mainMenu.setSize(50,50);
        setJMenuBar(mainMenu);

        JTabbedPane rightPanel  = new JTabbedPane();

        JMenu fileMenu = new JMenu("Plik");
        JMenu editMenu = new JMenu("Edycja");
        JMenu helpMenu = new JMenu("Pomoc");
        mainMenu.add(fileMenu);
        //mainMenu.add(editMenu);
        mainMenu.add(helpMenu);

        URL imgURL = this.getClass().getResource("networking.png");
        ImageIcon icon = new ImageIcon(imgURL);
        setIconImage(icon.getImage());
        com.apple.eawt.Application.getApplication().setDockIconImage(icon.getImage());
        JMenuItem exitAction = new JMenuItem("Zakończ");
        exitAction.addActionListener(e -> {
            System.exit(0);
        });

        JLabel powierzchniaOtoczkiLabel = new JLabel("0");

        exitAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        JPanel mainBorderLayout = new JPanel(new BorderLayout());
        JPanel rightBorderLayout = new JPanel(new BorderLayout());

        JPanel rightGridLayout = new JPanel(new GridLayout(2,1));
        rightBorderLayout.add(rightGridLayout,BorderLayout.CENTER);

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

        final DefaultListModel<String> polygonPointsListModel = new DefaultListModel<>();
        polygonPointsList = new JList<>();
        polygonPointsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        polygonPointsList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        polygonPointsList.setVisibleRowCount(-1);
        polygonPointsList = new JList<>(polygonPointsListModel);
        polygonPointsList.setPrototypeCellValue("X:   Y:   ");
        JScrollPane polygonPointListScrollPane = new JScrollPane(polygonPointsList);

        JPanel rightDownGridLayout = new JPanel(new GridLayout(7,2));

        JTextField xValue  = new JTextField();
        JTextField yValue = new JTextField();

        xValue.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        int x = Integer.parseInt(xValue.getText());
                        int y = Integer.parseInt(yValue.getText());
                        if(Math.abs(x)<=ConvexHull.maxRange/2 && Math.abs(y)<=ConvexHull.maxRange/2) {
                            if (!mainSurface.contains(new Point(x, y))) {
                                pointsListModel.addElement("X: " + x + ", Y: " + y);
                                xValue.setText("");
                                yValue.setText("");
                                mainSurface.add(new Point(x, y));
                                repaint();
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Przekroczono zakres! Maksymalny zakres x i y: " + ConvexHull.maxRange/2, "Błąd" +
                                    " danych",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    catch (NumberFormatException ignored){
                        JOptionPane.showMessageDialog(null, "Wprowadzone współrzędne są niepoprawne!", "Błąd" +
                                " danych",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        yValue.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        int x = Integer.parseInt(xValue.getText());
                        int y = Integer.parseInt(yValue.getText());
                        if(Math.abs(x)<=ConvexHull.maxRange/2 && Math.abs(y)<=ConvexHull.maxRange/2) {
                            if (!mainSurface.contains(new Point(x, y))) {
                                pointsListModel.addElement("X: " + x + ", Y: " + y);
                                xValue.setText("");
                                yValue.setText("");
                                mainSurface.add(new Point(x, y));
                                repaint();
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Przekroczono zakres! Maksymalny zakres x i y: " + ConvexHull.maxRange/2, "Błąd" +
                                    " danych",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    catch (NumberFormatException ignored){
                        JOptionPane.showMessageDialog(null, "Wprowadzone współrzędne są niepoprawne!", "Błąd" +
                                " danych",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        rightDownGridLayout.add(new JLabel("X:",SwingConstants.CENTER));
        rightDownGridLayout.add(xValue);
        rightDownGridLayout.add(new JLabel("Y:",SwingConstants.CENTER));
        rightDownGridLayout.add(yValue);

        JButton addPointButton = new JButton("Dodaj");
        addPointButton.addActionListener(e ->{
            try {
                int x = Integer.parseInt(xValue.getText());
                int y = Integer.parseInt(yValue.getText());
                if(Math.abs(x)<=ConvexHull.maxRange/2 && Math.abs(y)<=ConvexHull.maxRange/2) {
                    if (!mainSurface.contains(new Point(x, y))) {
                        pointsListModel.addElement("X: " + x + ", Y: " + y);
                        xValue.setText("");
                        yValue.setText("");
                        mainSurface.add(new Point(x, y));
                        repaint();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Przekroczono zakres! Maksymalny zakres x i y: " + ConvexHull.maxRange/2, "Błąd" +
                            " danych",JOptionPane.ERROR_MESSAGE);
                }
            }
            catch (NumberFormatException ignored){
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
                if(convexHull!=null) {
                    for(Point point : convexHull.returnPointsAsArray()) {
                        if(point.getX() == Integer.parseInt(x) && point.getY() == Integer.parseInt(y)) {
                            convexHull = null;
                            polygonPointsListModel.clear();
                            powierzchniaOtoczkiLabel.setText("");
                            powierzchniaOtoczkiLabel.setToolTipText("");
                        }
                    }
                }
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
                if(Integer.parseInt(randomPointsNumber.getText())<=10000) {
                    if (Integer.parseInt(randomPointsRange.getText()) <= ConvexHull.maxRange) {
                        Random rnd = new Random();
                        for (int i = 0; i < Integer.parseInt(randomPointsNumber.getText()); i++) {
                            int x = rnd.nextInt(Integer.parseInt(randomPointsRange.getText())) - Integer.parseInt(randomPointsRange.getText()) / 2;
                            int y = rnd.nextInt(Integer.parseInt(randomPointsRange.getText())) - Integer.parseInt(randomPointsRange.getText()) / 2;
                            mainSurface.add(new geometry.Point(x, y));
                            pointsListModel.addElement("X: " + x + ", Y: " + y);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Maksymalny zakres losowania to: " + ConvexHull.maxRange, "Błąd" +
                                " danych", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Naraz można wylosować do 10000 punktów.","Błąd" +
                            " danych", JOptionPane.ERROR_MESSAGE);
                }
                repaint();
            } catch (Exception ignored){
                JOptionPane.showMessageDialog(null, "Wprowadzone parametry wyszukiwania są niepoprawne!", "Błąd" +
                        " danych",JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton deleteAllPointsButton = new JButton("<html><center>Usuń <br>wszystkie</center></html>");
        deleteAllPointsButton.addActionListener(e -> {
            mainSurface.removeAll();
            pointsListModel.removeAllElements();
            convexHull = null;
            polygonPointsListModel.removeAllElements();
            powierzchniaOtoczkiLabel.setToolTipText("");
            powierzchniaOtoczkiLabel.setText("");
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
                if(pointsListModel.getSize()>=2) {
                    convexHull = mainSurface.createConvexHull();
                    polygonPointsListModel.removeAllElements();
                    for(Point point: convexHull.returnPointsAsArray())
                    polygonPointsListModel.addElement("X: " + point.getX()+ ", Y: " + point.getY());

                    /*for(Point point : mainSurface.returnPointsAsArray()) {
                        if(!convexHull.isInside(point.getX(),point.getY()))
                            System.out.println("W środku nie ma: " + point.getX() + " " + point.getY());
                    }*/

                 /*   int maxY = mainSurface.getMaxNorth().getY();
                    int minY = mainSurface.getMaxSouth().getY();
                    int minX = mainSurface.getMaxWest().getX();
                    int maxX = mainSurface.getMaxEast().getX();
                    int trafionych = 0;
                    int zakresX = maxX - minX;
                    int zakresY = maxY - minY;
                    Random rnd = new Random();
                    for(int i = 0; i < 1000;i++) {
                        double x = ThreadLocalRandom.current().nextDouble(minX, minX+zakresX);
                        double y = ThreadLocalRandom.current().nextDouble(minY,minY+zakresY);
                        if(convexHull.isInside(x,y))
                            trafionych++;
                    }
                    double pole = (zakresX*zakresY) * (trafionych*1.0/1000);*/
                    double pole = convexHull.getArea();
                    pole = Math.round(pole*1000d)/1000d;
                    String poleString = "" + pole;
                    powierzchniaOtoczkiLabel.setToolTipText("");
                    if(poleString.length()>6) {
                        powierzchniaOtoczkiLabel.setToolTipText(poleString);
                        poleString = poleString.substring(0, 6) + "...";
                    }
                    powierzchniaOtoczkiLabel.setText("" + poleString);


                }
                repaint();
            }
        });

        JMenuItem exportPointAction = new JMenuItem("Eksportuj Punkty");
        exportPointAction.addActionListener(e ->{
            JFileChooser chooseFile = new JFileChooser();
            int save = chooseFile.showSaveDialog(null);
            if (save == JFileChooser.APPROVE_OPTION) {
                String filename = chooseFile.getSelectedFile().getPath();
                PrintWriter writer;
                try {
                    writer = new PrintWriter(filename, "UTF-8");
                    for (int i = 0; i < pointsListModel.size(); i++)
                        writer.println(pointsListModel.getElementAt(i));

                    writer.close();
                } catch (FileNotFoundException | UnsupportedEncodingException exception) {
                    JOptionPane.showMessageDialog(null, "Nie można wyeksportować danych do tego pliku.", "Błąd" +
                            " danych",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JMenuItem importPointAction = new JMenuItem("Importuj Punkty");
        importPointAction.addActionListener( e ->{
            JFileChooser chooseFile = new JFileChooser();
            int save = chooseFile.showOpenDialog(null);
            if (save == JFileChooser.APPROVE_OPTION) {
                String filename = chooseFile.getSelectedFile().getPath();
                String line;
                try {
                    Scanner in = new Scanner(new File(filename));
                    while (in.hasNextLine()) {
                        line = in.nextLine();
                        String x = line.split(",")[0];
                        String y = line.split(",")[1];
                        x = x.substring(3,x.length());
                        y = y.substring(4,y.length());
                        if(Math.abs(Integer.parseInt(x))<=ConvexHull.maxRange/2 && Math.abs(Integer.parseInt(y))<=ConvexHull.maxRange/2) {
                            mainSurface.add(new Point(Integer.parseInt(x), Integer.parseInt(y)));
                            pointsListModel.addElement(line);
                        }
                    }
                    in.close();
                    repaint();

                } catch (FileNotFoundException exception) {
                    JOptionPane.showMessageDialog(null, "Nie można czytać danych z tego pliku.", "Błąd" +
                            " danych",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        JMenuItem infoProgramAction = new JMenuItem("O programie");
        infoProgramAction.addActionListener(e -> {
            InfoWindow infoWindow = new InfoWindow();
            infoWindow.setVisible(true);
        });
        fileMenu.add(exportPointAction);
        fileMenu.add(importPointAction);
        fileMenu.addSeparator();
        fileMenu.add(exitAction);
        helpMenu.add(infoProgramAction);

        rightBorderLayout.add(testButton,BorderLayout.SOUTH);

        Chart chart = new Chart();
        mainBorderLayout.add(chart);
        mainBorderLayout.add(rightPanel, BorderLayout.EAST);
        rightPanel.add(rightBorderLayout,"Dane");

        JPanel resultsMainBorderLayout = new JPanel(new BorderLayout());

        JPanel resultsGridLayout = new JPanel(new GridLayout(2,1));

        rightPanel.add(resultsMainBorderLayout,"Wyniki");
        resultsMainBorderLayout.add(new JLabel("Punkty Otoczki:",SwingConstants.CENTER),BorderLayout.NORTH);

        resultsMainBorderLayout.add(resultsGridLayout,BorderLayout.CENTER);

        JPanel resultsDownGridLayout =  new JPanel(new GridLayout(5,2));

        resultsDownGridLayout.add(new JLabel("<html><center>Powierzchnia <br>Otoczki:</center></html>"));
        resultsDownGridLayout.add(powierzchniaOtoczkiLabel);

        JButton exportConvexHullButton = new JButton("<html><center>Eksportuj<br>Otoczkę</center></html>");
        exportConvexHullButton.addActionListener(e -> {
            JFileChooser chooseFile = new JFileChooser();
            int save = chooseFile.showSaveDialog(null);
            if (save == JFileChooser.APPROVE_OPTION) {
                String filename = chooseFile.getSelectedFile().getPath();
                PrintWriter writer;
                try {
                    writer = new PrintWriter(filename, "UTF-8");
                    for (int i = 0; i < polygonPointsListModel.size(); i++)
                        writer.println(polygonPointsListModel.getElementAt(i));
                    writer.close();
                } catch (FileNotFoundException | UnsupportedEncodingException exception) {
                    JOptionPane.showMessageDialog(null, "Nie można wyeksportować danych do tego pliku.", "Błąd" +
                            " danych",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        resultsDownGridLayout.add(exportConvexHullButton);
        resultsDownGridLayout.add(new JLabel());
        resultsDownGridLayout.add(new JLabel());
        resultsDownGridLayout.add(new JLabel());
        resultsDownGridLayout.add(new JLabel());
        resultsDownGridLayout.add(new JLabel());
        resultsDownGridLayout.add(new JLabel());
        resultsDownGridLayout.add(new JLabel());

        resultsGridLayout.add(polygonPointListScrollPane);
        resultsGridLayout.add(resultsDownGridLayout);


        add(mainBorderLayout);
    }

    private JList pointsList;
    private JList polygonPointsList;
}
