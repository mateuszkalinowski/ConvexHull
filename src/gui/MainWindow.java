package gui;

import core.ConvexHull;
import geometry.*;
import geometry.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
        mainMenu.add(editMenu);
        mainMenu.add(helpMenu);

        JMenuItem exitAction = new JMenuItem("Zakończ");
        exitAction.addActionListener(e -> {
            System.exit(0);
        });
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
                        mainSurface.add(new Point(Integer.parseInt(x),Integer.parseInt(y)));
                        pointsListModel.addElement(line);
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
        add(mainBorderLayout);
    }

    private JList pointsList;
}
