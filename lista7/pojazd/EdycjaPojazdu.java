package pojazd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EdycjaPojazdu implements ActionListener {
    private Pojazd obiektEdytowany;
    
    JTextField markaEdit;
    JTextField rokProdukcjiEdit;
    String filename;

    public EdycjaPojazdu(Pojazd p, String _filename) {
        obiektEdytowany = p;
        markaEdit = new JTextField(p.marka);
        rokProdukcjiEdit = new JTextField(p.rokProdukcji.toString());

        filename = _filename;
    }

    void addTextFields(Container container) {
        container.add(new JLabel("Marka"));
        container.add(markaEdit);
        container.add(new JLabel("Rok produkcji"));
        container.add(rokProdukcjiEdit);
    }
    public void show() {
        JFrame frame = new JFrame("Edycja pojazdu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(3,2));

        addTextFields(container);

        JButton saveButton = new JButton("Zapisz");
        saveButton.addActionListener(this);
        container.add(saveButton);

        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        obiektEdytowany.marka = markaEdit.getText();
        obiektEdytowany.rokProdukcji = Integer.parseInt(rokProdukcjiEdit.getText());

        try {
            Pojazd.writeObject(obiektEdytowany,filename);
        } catch(Exception exc) {
            exc.printStackTrace();
        }
    }
}
