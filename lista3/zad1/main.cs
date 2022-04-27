using System;

class Program {
    public static void Main() {
        Lista<int> l = new Lista<int>();
        l.push_back(19); l.push_back(88);
        Console.WriteLine("{0} {1}", l.pop_front(), l.pop_back());
        Console.WriteLine(l.is_empty());

        l.push_front(2); l.push_front(1); // 1 -> 2
        l.push_back(3); l.push_back(4); // 1 -> 2 -> 3 -> 4
        while(!l.is_empty()) Console.Write("{0} ",l.pop_front());
        Console.Write("\n");

        l.push_front(2); l.push_front(1); // 1 -> 2
        l.push_back(3); l.push_back(4); // 1 -> 2 -> 3 -> 4
        while(!l.is_empty()) Console.Write("{0} ",l.pop_back());

        try {
            Console.WriteLine(l.pop_front());
        } catch(Exception e) {
            Console.WriteLine(e);
        }
        
        try {
            Console.WriteLine(l.pop_back());
        } catch(Exception e) {
            Console.WriteLine(e);
        }
    }
};