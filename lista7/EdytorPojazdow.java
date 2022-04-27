import pojazd.*;

public class EdytorPojazdow {
    public static void main(String args[]) {
        if(args.length != 2) {
            System.err.println("Użycie: java EdytorPojazdow <nazwa pliku> <nazwa klasy obiektu>");
            return;
        }

        String filename = args[0];
        String classname = args[1];

        Pojazd p;
        try {
            if(classname.equals("Pojazd")) p = Pojazd.readObject(filename);
            else if(classname.equals("Samochod")) p = Samochod.readObject(filename);
            else if(classname.equals("Tramwaj")) p = Tramwaj.readObject(filename);
            else {
                System.err.println(String.format("Taka klasa nie istnieje: \"%s\"",args[1]));
                return;
            }
        } catch(Exception e) {
            System.err.println("Błąd wczytywania obiektu");
            e.printStackTrace();
            return;
        }
        
        p.Edycja(filename);
    }
}
