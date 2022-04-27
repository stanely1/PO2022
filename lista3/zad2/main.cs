using System; 

class Program {
    public static void Main() {
        MyDictionary<string,int> d = new MyDictionary<string,int>();
        d.add("x",1);
        Console.WriteLine(d.find("x"));
        d.add("y",2); d.add("z",3);
        Console.WriteLine(d.find("y"));
        Console.WriteLine(d.find("z"));
        try {
            Console.WriteLine(d.find("d"));
        } catch(Exception e) {
            Console.WriteLine(e);
        }

        d.delete("x");
        try {
            Console.WriteLine(d.find("x"));
        } catch(Exception e) {
            Console.WriteLine(e);
        }

        d.add("y",6);
        Console.WriteLine(d.find("y"));
    }
};