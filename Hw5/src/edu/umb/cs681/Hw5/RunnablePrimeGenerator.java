package edu.umb.cs681.Hw5;

public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {

	public RunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}

	public void run() {
		generatePrimes();
	}

	public static void main(String[] args) {

		long start = System.currentTimeMillis();

		RunnablePrimeGenerator gen = new RunnablePrimeGenerator(1, 2000000);

		Thread t = new Thread(gen);
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
		}

		gen.getPrimes().forEach((Long prime) -> System.out.print(prime + ", "));

		long primeNum = gen.getPrimes().size();
		System.out.println("\n" + primeNum + " prime numbers are found in total.");


		long estimatedTime = (((long) (System.currentTimeMillis() - start)));
		System.out.println("\nTime: " + estimatedTime + " milliseconds to run the code.");

	}

}
