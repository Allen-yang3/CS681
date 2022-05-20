package edu.umb.cs681.Hw14;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;


public class RequestHandler implements Runnable {
	private ReentrantLock lock = new ReentrantLock();
	private boolean done = false;
	private AccessCounter access;
	private final static String[] paths = {"./a.html", "./b.html", "./c.html"};
	
	public RequestHandler(AccessCounter a) {
		this.access = a;
	}

	public void run() {
		System.out.println(AccessCounter.getInstance());

		while (true) {
			Random rand = new Random();
			int random = rand.nextInt(3);
			Path path = null;
			if (random == 1) {
				path = Paths.get("a.html");
			} else if (random == 2) {
				path = Paths.get("b.html");
			} else if (random == 3) {
				path = Paths.get("c.html");
			}
			AccessCounter.getInstance().increment(path);
			AccessCounter.getInstance().getCount(path);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getId() + Thread.currentThread().getName() + " " + e);
				continue;
			}
		}
		
	}

	public static void main(String[] args) throws InterruptedException  {

		AccessCounter a = AccessCounter.getInstance();
		RequestHandler r1 = new RequestHandler(a);
		RequestHandler r2 = new RequestHandler(a);
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		try {
			Thread.sleep(500);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		r1.setDone(); 
		r2.setDone();
        t1.interrupt();
        t2.interrupt();
		try {
			t1.join();
			t2.join();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
        for (String p: paths) {
            Path path = Paths.get(p);
            System.out.println("Access count: " + AccessCounter.getInstance().getCount(path));
        }
	}

	public void setDone() {
		lock.lock();
		try {
			done = true;
		} finally {
			lock.unlock();
		}
	}

}
