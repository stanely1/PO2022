import java.util.*;

class Sort<T extends Comparable<T>> extends Thread {
    public T[] tab;

    public Sort(T[] _tab) {
        tab = _tab;
    }

    private T[] merge(Sort<T> x, Sort<T> y) throws InterruptedException {
        //uruchamiamy watki
        x.start();
        y.start();
        //czekamy az zakoncza dzialanie:
        x.join();
        y.join();

        int n = x.tab.length + y.tab.length;
        ArrayList<T> res = new ArrayList<T>();

        int indX = 0, indY = 0;
        for(int i = 0; i < n; i++)
        {
            if(indX == x.tab.length) res.add(y.tab[indY++]);
            else if(indY == y.tab.length) res.add(x.tab[indX++]);
            else if(x.tab[indX].compareTo(y.tab[indY]) <= 0) res.add(x.tab[indX++]);
            else res.add(y.tab[indY++]);
        }
        return res.toArray(x.tab);
    }
    public void run() {
        if(tab.length <= 1) return;
        int m = tab.length/2;
        try {
            tab = merge(new Sort<T>(Arrays.copyOfRange(tab,0,m)), new Sort<T>(Arrays.copyOfRange(tab,m,tab.length)));
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static <T extends Comparable<T>> void MergeSort(T[] t) throws InterruptedException {
        Sort<T> s = new Sort<T>(t);
        s.start();
        s.join();
        
        for(int i = 0; i < t.length; i++)
            t[i] = s.tab[i];
    }
}

public class zad4 {
    public static void main(String[] args) throws InterruptedException {
        Integer[] tab = {7,4,2,1,3,6,5,9,10,8,3,3,2};
        System.out.println(Arrays.toString(tab));

        Sort.MergeSort(tab);
        System.out.println(Arrays.toString(tab));

        String[] stab = {"janek","maciek","basia","kasia","zbyszek","ania","michal"};
        Sort.MergeSort(stab);
        System.out.println(Arrays.toString(stab));
    }   
}