package edu.umb.cs681.Hw14;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;

public class AccessCounter {

	private ConcurrentHashMap<Path, Integer> Map = new ConcurrentHashMap<Path, Integer>();
	private static AccessCounter instance = null;
	
	public static AccessCounter getInstance() {
		if (instance == null) {
			instance = new AccessCounter();
		}
		return instance;
	}

	public void increment(Path path) {
		// increment the path’s access count.
		Map.compute(path, (Path k, Integer v) -> {
			return v == null ? 1 : ++v;
		});
	}

	public int getCount(Path path) {
		return Map.compute(path, (Path k, Integer v) -> {
			return v == null ? 0 : v;
		});
	}

}
