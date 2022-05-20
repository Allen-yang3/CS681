package edu.umb.cs681.Hw09;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton implements Runnable {

	private ConcurrentSingleton() {
	};
	
	private static AtomicReference<ConcurrentSingleton> instance = new AtomicReference<ConcurrentSingleton>(new ConcurrentSingleton());

	public static ConcurrentSingleton getInstance() {
			if (instance.get() == null) {
				instance.set(new ConcurrentSingleton());
			}
			return instance.get();
	}
	
	public void run() {
		System.out.println("Thread " + Thread.currentThread().getId() + " starts");
		System.out.println(ConcurrentSingleton.getInstance());
		System.out.println("Thread " + Thread.currentThread().getId() + " ends");
	}
	
	public static void main(String[] args) {
		
		ConcurrentSingleton s1 = new ConcurrentSingleton();
		ConcurrentSingleton s2 = new ConcurrentSingleton();
		ConcurrentSingleton s3 = new ConcurrentSingleton();
		ConcurrentSingleton s4 = new ConcurrentSingleton();
		Thread t1 = new Thread(s1);
		Thread t2 = new Thread(s2);
		Thread t3 = new Thread(s3);
		Thread t4 = new Thread(s4);
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
			e.printStackTrace();
		}
	}
}
