package edu.umb.cs681.Hw15;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
public class FileSystem {
    private static FileSystem instance;
    ConcurrentLinkedQueue<Directory> directory = new ConcurrentLinkedQueue<>();
	
	private FileSystem() {	
	}
	
	private static FileSystem fileSystem = null;
	
	public static FileSystem getFileSystem() {
		if(fileSystem == null) {
			fileSystem = new FileSystem();
		}
		return fileSystem;
	}
	
	public ConcurrentLinkedQueue<Directory> getRootDirs() {
		return this.directory;
	}
	
	public void addRootDir(Directory rootDir) {
		directory.add(rootDir);
	}
	
	public static void main(String[] args) {
		
	}
}