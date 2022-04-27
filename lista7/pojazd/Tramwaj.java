package pojazd;

import java.io.*;

public class Tramwaj extends Pojazd {
    Integer pojemnosc;
    Integer miejscaSiedzace;
    Integer dlugosc;
    
    public Tramwaj() {
        super();
        pojemnosc = miejscaSiedzace = dlugosc = 0;
    }
    public Tramwaj(String _marka, int _rok, int _pojemnosc, int _miejscaSiedzace, int _dlugosc) {
        super(_marka,_rok);
        pojemnosc = _pojemnosc;
        miejscaSiedzace = _miejscaSiedzace;
        dlugosc = _dlugosc;
    }

    public String toString() {
        return super.toString() + "\n\tPojemność: " + pojemnosc.toString() 
                                + "\n\tLiczba miejsc siedzących: " + miejscaSiedzace.toString()
                                + "\n\tDlugość: " + dlugosc.toString();
    }

    public void Edycja(String filename) {
        EdycjaTramwaju edit = new EdycjaTramwaju(this,filename);
        edit.show();
    }

    public static Tramwaj readObject(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis;

        try {
            fis = new FileInputStream(filename);
        } catch(FileNotFoundException e) {
            return new Tramwaj();
        }

        ObjectInputStream ois = new ObjectInputStream(fis);
        Tramwaj p = (Tramwaj)ois.readObject();
        ois.close();
        return p;
    }
}
