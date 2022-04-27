package pojazd;

import java.io.*;

public class Samochod extends Pojazd {
    String rodzajPaliwa;
    String typNadwozia;
    Integer mocSilnika;

    public Samochod() {
        super();
        rodzajPaliwa = typNadwozia = "";
        mocSilnika = 0;
    }
    public Samochod(String _marka, int _rok, String _paliwo, String _typ, int _moc) {
        super(_marka, _rok);
        rodzajPaliwa = _paliwo;
        typNadwozia = _typ;
        mocSilnika = _moc;
    }

    public String toString() {
        return super.toString() + "\n\tRodzaj paliwa: " + rodzajPaliwa 
                                + "\n\tTyp nadwozia: " + typNadwozia 
                                + "\n\tMoc silnika: " + mocSilnika.toString();
    }

    public void Edycja(String filename) {
        EdycjaSamochodu edit = new EdycjaSamochodu(this,filename);
        edit.show();
    }

    public static Samochod readObject(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis;

        try {
            fis = new FileInputStream(filename);
        } catch(FileNotFoundException e) {
            return new Samochod();
        }

        ObjectInputStream ois = new ObjectInputStream(fis);
        Samochod p = (Samochod)ois.readObject();
        ois.close();
        return p;
    }
}
