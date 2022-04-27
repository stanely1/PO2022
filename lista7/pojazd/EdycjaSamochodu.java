package pojazd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EdycjaSamochodu extends EdycjaPojazdu {
    private Samochod obiektEdytowany;

    JTextField rodzajPaliwaEdit;
    JTextField typNadwoziaEdit;
    JTextField mocSilnikaEdit;

    public EdycjaSamochodu(Samochod s, String _filename) {
        super(s, _filename);
        obiektEdytowany = s;
        rodzajPaliwaEdit = new JTextField(s.rodzajPaliwa);
        typNadwoziaEdit = new JTextField(s.typNadwozia);
        mocSilnikaEdit = new JTextField(s.mocSilnika.toString());
    }

    public void show() {
        JFrame frame = new JFrame("Edycja samochodu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(6,2));

        super.addTextFields(container);
        container.add(new JLabel("Rodzaj paliwa"));
        container.add(rodzajPaliwaEdit);
        container.add(new JLabel("Typ nadwozia"));
        container.add(typNadwoziaEdit);
        container.add(new JLabel("Moc silnika"));
        container.add(mocSilnikaEdit);

        JButton saveButton = new JButton("Zapisz");
        saveButton.addActionListener(this);
        container.add(saveButton);

        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        obiektEdytowany.rodzajPaliwa = rodzajPaliwaEdit.getText();
        obiektEdytowany.typNadwozia = typNadwoziaEdit.getText();
        obiektEdytowany.mocSilnika = Integer.parseInt(mocSilnikaEdit.getText());
        super.actionPerformed(e);
    }
}