package edu.umb.cs681.Hw11;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable {
	private ReentrantLock lock = new ReentrantLock();
	private AtomicBoolean done = new AtomicBoolean(false);

	public void run() {
		System.out.println(AccessCounter.getInstance());


		while (true) {
			Random rand = new Random();
			int random = rand.nextInt(3);
			java.nio.file.Path path = null;
			if (random == 1) {
				path = java.nio.file.Paths.get("a.html");
			} else if (random == 2) {
				path = java.nio.file.Paths.get("b.html");
			} else if (random == 3) {
				path = java.nio.file.Paths.get("c.html");
			}
			AccessCounter.getInstance().increment(path);
			AccessCounter.getInstance().getCount(path);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getId() + Thread.currentThread().getName() + " " + e);
				continue;
			}
		}
	}

	public static void main(String[] args) {

		int number = 20;
		RequestHandler[] r = new RequestHandler[number];
		Thread[] t = new Thread[number];
		// create and start threads, and request handler
		for (int i = 0; i < number; i++) {
			r[i] = new RequestHandler();
			t[i] = new Thread(r[i]);
			t[i].start();
		}
		for (int i = 0; i < number; i++) {
			r[i].setDone();
			t[i].interrupt();
		}
	}

	public void setDone() {
		lock.lock();
		try {
			done.set (true);
		} finally {
			lock.unlock();
		}
	}

}
