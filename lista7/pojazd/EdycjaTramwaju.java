package pojazd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EdycjaTramwaju extends EdycjaPojazdu {
    private Tramwaj obiektEdytowany;

    JTextField pojemnoscEdit;
    JTextField miejscaSiedzaceEdit;
    JTextField dlugoscEdit;

    public EdycjaTramwaju(Tramwaj t, String _filename) {
        super(t,_filename);
        obiektEdytowany = t;
        pojemnoscEdit = new JTextField(t.pojemnosc.toString());
        miejscaSiedzaceEdit = new JTextField(t.miejscaSiedzace.toString());
        dlugoscEdit = new JTextField(t.dlugosc.toString());
    }

    public void show() {
        JFrame frame = new JFrame("Edycja Tramwaju");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(6,2));

        super.addTextFields(container);
        container.add(new JLabel("Pojemność"));
        container.add(pojemnoscEdit);
        container.add(new JLabel("Liczba miejsc siedzących"));
        container.add(miejscaSiedzaceEdit);
        container.add(new JLabel("Długość"));
        container.add(dlugoscEdit);

        JButton saveButton = new JButton("Zapisz");
        saveButton.addActionListener(this);
        container.add(saveButton);

        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        obiektEdytowany.pojemnosc = Integer.parseInt(pojemnoscEdit.getText());
        obiektEdytowany.miejscaSiedzace = Integer.parseInt(miejscaSiedzaceEdit.getText());
        obiektEdytowany.dlugosc = Integer.parseInt(dlugoscEdit.getText());
        super.actionPerformed(e);
    }
}