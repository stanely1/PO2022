using System;
using System.Collections.Generic;

class LazyIntList {
	protected List<int> list;

	public LazyIntList() {
		list = new List<int>();
	}

	virtual public int element(int i) {
		while(size() <= i) list.Add(size());
		return list[i];
	}
	public int size() {
		return list.Count;
	}
};

class LazyPrimeList : LazyIntList {
	int last_prime;

	bool isPrime(int p) {
		if(p != 2 && p%2 == 0) return false;
		for(int i = 3; i*i <= p; i+=2)
			if(p%i == 0) return false;
		return true;
	}
	int NextPrime() {
		if(last_prime == 2) last_prime--;

		last_prime += 2;
		while(!isPrime(last_prime)) last_prime += 2;

		return last_prime;
	}

	public LazyPrimeList() : base() {
		last_prime = 0;
	}
	
	override public int element(int i) {
		while(size() <= i) list.Add(NextPrime());
		return list[i];
	}
};

class MAIN {
	public static void Main() {
		LazyIntList lista = new LazyIntList();
		Console.WriteLine("{0} {1} {2}", lista.size(), lista.element(100), lista.size());
		Console.WriteLine("{0} {1}", lista.element(50), lista.size());
		Console.WriteLine("{0} {1}", lista.element(102), lista.size());

		LazyPrimeList primes = new LazyPrimeList();
		Console.WriteLine("{0} {1}", primes.element(17), primes.size());

		for(int i = 0; i <= 20; i++) Console.Write("{0} ", primes.element(i));
		Console.WriteLine("\n{0}", primes.size());
		Console.WriteLine("{0} {1}", primes.element(10), primes.size());
	}
};