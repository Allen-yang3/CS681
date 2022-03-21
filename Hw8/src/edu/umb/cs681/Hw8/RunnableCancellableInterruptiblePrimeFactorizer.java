package edu.umb.cs681.Hw8;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {

	public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
		super(dividend, from, to);
	}

	private boolean done = false;
	private ReentrantLock lock = new ReentrantLock();

	public void setDone() {
		lock.lock();
		try {
			done = true;
		} finally {
			lock.unlock();
		}
	}

	public void generatePrimeFactors() {
		long divisor = from;
		while (dividend != 1 && divisor <= to) {
			lock.lock();
			if (Thread.interrupted()) {
				System.out.println("Stopped");
				this.factors.clear();
				break;
			}
			lock.unlock();
			try {
				if (divisor > 2 && isEven(divisor)) {
					divisor++;
					continue;
				}
				if (dividend % divisor == 0) {
					factors.add(divisor);
					dividend /= divisor;
				} else {
					if (divisor == 2) {
						divisor++;
					} else {
						divisor += 2;
					}
				}
			} finally {
				lock.unlock();
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				continue;
			}
		}
	}

	public void run() {
		generatePrimeFactors();
	}

	public static void main(String[] args) {
		System.out.println("Factorization of 36");
		RunnableCancellableInterruptiblePrimeFactorizer runnable = new RunnableCancellableInterruptiblePrimeFactorizer(
				36, 2, (long) Math.sqrt(36));
		Thread thread = new Thread(runnable);
		System.out.println("Thread #" + thread.getId() + " performs factorization in the range of " + runnable.getFrom()
				+ "->" + runnable.getTo());
		thread.start();
		runnable.setDone();
		thread.interrupt();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Final result: " + runnable.getPrimeFactors() + "\n");
	}

}
