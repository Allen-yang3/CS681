package edu.umb.cs681.Hw4;

public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {

	public RunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}

	public void run() {
		generatePrimes();
	}

	public static void main(String[] args) {

		long start = System.currentTimeMillis();

		RunnablePrimeGenerator gen1 = new RunnablePrimeGenerator(1L, 1000000L);
		RunnablePrimeGenerator gen2 = new RunnablePrimeGenerator(1000001L, 2000000L);
		RunnablePrimeGenerator gen3 = new RunnablePrimeGenerator(2000001L, 3000000L);
		RunnablePrimeGenerator gen4 = new RunnablePrimeGenerator(3000001L, 4000000L);
		Thread t1 = new Thread(gen1);
		Thread t2 = new Thread(gen2);
		Thread t3 = new Thread(gen3);
		Thread t4 = new Thread(gen4);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
		}

		gen1.getPrimes().forEach((Long prime) -> System.out.print(prime + ", "));
		gen2.getPrimes().forEach((Long prime) -> System.out.print(prime + ", "));
		gen3.getPrimes().forEach((Long prime) -> System.out.print(prime + ", "));
		gen4.getPrimes().forEach((Long prime) -> System.out.print(prime + ", "));

		long primeNum = gen1.getPrimes().size() + gen2.getPrimes().size() + gen3.getPrimes().size()
				+ gen4.getPrimes().size();
		System.out.println("\n" + primeNum + " prime numbers are found in total.");
		long estimatedTime = (((long) (System.currentTimeMillis() - start)));
		System.out.println("\nTime: " + estimatedTime + " milliseconds to run the code.");

	}

}
