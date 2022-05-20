package edu.umb.cs681.Hw11;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {

	private HashMap<java.nio.file.Path, Integer> Map = new HashMap<java.nio.file.Path, Integer>();
	private ReentrantLock lock = new ReentrantLock();
	private static AccessCounter instance = null;
	private static ReentrantLock staticLock = new ReentrantLock();

	public static AccessCounter getInstance() {
		staticLock.lock();
		try {
			if (instance == null) {
				instance = new AccessCounter();
			}
		} finally {
			staticLock.unlock();
		}
		return instance;
	}

	void increment(Path path) {
		lock.lock();
		try {
			if (Map.containsKey(path)) {
				// increment the path’s access count.
				Map.put(path, Map.get(path) + 1);
			} else {
				Map.put(path, 1);
			}
		} finally {
			lock.unlock();
		}
	}

	int getCount(Path path) {
		lock.lock();
		try {
			if (Map.containsKey(path)) {
				// get the path’s access count and return it:
				return Map.get(path);
			} else {
				return 0;
			}
		} finally {
			lock.unlock();
		}
	}
}
