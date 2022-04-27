using System;
using System.Collections;

class PrimeEnumerator : IEnumerator {
	int current;

	public PrimeEnumerator() {
		current = 0;
	}

    bool isPrime(int p) {
		if(p != 2 && p%2 == 0) return false;
		for(int i = 3; i*i <= p; i+=2)
			if(p%i == 0) return false;
		return true;
	}
	public bool MoveNext() {
		if(current == 2) current--;

		current += 2;
		while(current >= 0 && !isPrime(current)) current += 2;

		return current >= 0;
	}
	public void Reset() {
		current = 0;
	}
	public object Current {
		get {return current;}
	}
}

class PrimeCollection : IEnumerable {
	PrimeEnumerator enumerator;

	public PrimeCollection() {
		enumerator = new PrimeEnumerator();
	}

	public IEnumerator GetEnumerator() {
		return enumerator;
	}
}

class Program {
	public static void Main() {
		PrimeCollection pc = new PrimeCollection();
		foreach(int p in pc) 
			Console.WriteLine(p);
	}
}
