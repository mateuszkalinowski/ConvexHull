package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mateusz on 25.11.2016.
 * Project ConvexHull
 */
public class InfoWindow extends JDialog {
    public InfoWindow() {
        setSize(250, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("O programie");
        JButton exit = new JButton("Powrót");
        exit.addActionListener(e -> setVisible(false));
        JLabel name = new JLabel("<html><center><h1>ConvexHull Generator</h1></center></html>");
        JLabel description = new JLabel("<html><center<Program służy do generowania otoczki wypukłej dookoła zadanych punktów.<br>" +
                "Punkty można dodawać pojedynczo, losować bądź dodawać z pliku. W przypadku losowania należy podać zakres<br>" +
                "(jest on równy z obu stron, tzn, po podaniu '100' punkty będą generowane z kwadratu,<br> o punktach granicznych (50,50)" +
                ", (-50,-50).<br>Plik z punktami do wczytania powinien zawierać linie w postaci:<br>X: wartość, Y: wartość<br><br></center>" +
                "Autor: Mateusz Kalinowski, www.github.com/mateuszkalinowski</html>");
        JPanel mainBorderLayout = new JPanel(new BorderLayout());
        mainBorderLayout.add(exit, BorderLayout.SOUTH);
        mainBorderLayout.add(description, BorderLayout.CENTER);
        mainBorderLayout.add(name, BorderLayout.NORTH);
        add(mainBorderLayout);
        pack();
    }
}
