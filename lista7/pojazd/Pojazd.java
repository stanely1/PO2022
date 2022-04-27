package pojazd;

import java.io.*;

public class Pojazd implements Serializable {
    String marka;
    Integer rokProdukcji;

    public Pojazd() {
        marka = "";
        rokProdukcji = 0;
    }
    public Pojazd(String _marka, int _rok) {
        marka = _marka;
        rokProdukcji = _rok;
    }
    public String toString() {
        return this.getClass().getName().substring(7)
               + ":\n\tMarka: " + marka 
               + "\n\tRok produkcji: " + rokProdukcji.toString();
    }

    public void Edycja(String filename) {
        EdycjaPojazdu edit = new EdycjaPojazdu(this, filename);
        edit.show();
    }

    public static final void writeObject(Pojazd p, String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(p);
        oos.flush();
        oos.close();
    } 
    public static Pojazd readObject(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis;

        try {
            fis = new FileInputStream(filename);
        } catch(FileNotFoundException e) {
            return new Pojazd();
        }

        ObjectInputStream ois = new ObjectInputStream(fis);
        Pojazd p = (Pojazd)ois.readObject();
        ois.close();
        return p;
    }
}