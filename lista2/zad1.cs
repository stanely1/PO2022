using System;

class IntStream {
    protected int current_val;

    public IntStream() {
        current_val = 0;
    }

    virtual public int next() {
        if(eos()) return -1;
        return current_val++;
    }
    virtual public bool eos() {
        return current_val == Int32.MaxValue;
    }
    public void reset() {
        current_val = 0;
    }
};

class PrimeStream : IntStream {
    bool isPrime(int p) {
		if(p != 2 && p%2 == 0) return false;
		for(int i = 3; i*i <= p; i+=2)
			if(p%i == 0) return false;
		return true;
	}

	override public int next() {
        if(eos()) return -1;
		if(current_val == 2) current_val--;

		current_val += 2;
		while(current_val >= 0 && !isPrime(current_val)) current_val += 2;

		return eos() ? -1 : current_val;
	}
    override public bool eos() {
        return current_val < 0;
    }
};

class RandomStream : IntStream {
    Random r = new Random();

    override public int next() {
        return r.Next();
    }
    override public bool eos() {
        return false;
    }
};

class RandomWordStream {
    PrimeStream len_str;
    RandomStream content_str;

    public RandomWordStream() {
        len_str = new PrimeStream();
        content_str = new RandomStream();
    }

    char RandomChar() {
        return (char)(content_str.next() % 26 + 'a');
    }
    public string next() {
        string res = "";
        int len = len_str.next();
        while(len --> 0) res += RandomChar();
        return res;
    }
};

class MAIN {
    public static void Main() {
        IntStream Is = new IntStream();
        for(int i = 0 ; i < 10; i++)
            Console.Write("{0} ", Is.next());
        for(int i = 0 ; i < 10; i++)
            Console.Write("{0} ", Is.next());
        Console.WriteLine("\neos: {0}",Is.eos());
        Is.reset();
        for(int i = 0 ; i < 10; i++)
            Console.Write("{0} ", Is.next());
        Console.Write("\n");

        PrimeStream ps = new PrimeStream();
        for(int i = 0; i < 20; i++)
            Console.Write("{0} ", ps.next());
        Console.Write("\n");
        ps.reset(); 
        for(int i = 0; i < 20; i++)
            Console.Write("{0} ", ps.next());

        RandomStream rs = new RandomStream();
        for(int i = 0; i < 5; i++) Console.WriteLine("{0}", rs.next());

        RandomWordStream rws = new RandomWordStream();
        for(int i = 0; i < 10; i++)
            Console.WriteLine("{0}",rws.next());
    }
}