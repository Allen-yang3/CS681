package edu.umb.cs681.Hw7;


public class RunnableConcurrentSingleton implements Runnable {


	public void run() {
		System.out.println(ConcurrentSingleton.getInstance());
		
	}
	
	public static void main(String[] args) {
		
		RunnableConcurrentSingleton s1 = new RunnableConcurrentSingleton();
		RunnableConcurrentSingleton s2 = new RunnableConcurrentSingleton();
		RunnableConcurrentSingleton s3 = new RunnableConcurrentSingleton();
		RunnableConcurrentSingleton s4 = new RunnableConcurrentSingleton();
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
