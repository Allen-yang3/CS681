package edu.umb.cs681.Hw15;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
public class Directory extends FSElement {

    ConcurrentLinkedQueue<FSElement> children = new ConcurrentLinkedQueue <FSElement>();

	public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
		super(parent, name, size, creationTime);
	}

    public ConcurrentLinkedQueue<FSElement> getChildren() {
        lock.lock();
        try {
            return this.children;
        } finally {
            lock.unlock();
        }
    }
	public void appendChild(FSElement child) {
		lock.lock();
		try {

		this.children.add(child);
		child.setParent(this);
	} finally {
		lock.unlock();
	}
	}
	public LinkedList<Directory> getSubDirectories() {
		try{ lock.lock();
		LinkedList<Directory> subDirectories = new LinkedList<Directory>();
		for (FSElement fselement : children) {
			if (fselement.isDirectory())
				subDirectories.add((Directory) fselement);
		}
		return subDirectories;
	} 
		finally {
			lock.unlock();
		}
	}
	public int countChildren(){
    lock.lock();
        try {
            int countChild = 0;
            for (FSElement f : this.children)
                countChild += 1;
            return countChild;
        } finally {
            lock.unlock();
        }
    }

	public int getTotalSize() {
		lock.lock();
		try{
		int totalSize = 0;
		for (FSElement fselement : children) {
			if (fselement.isDirectory()) {
				totalSize += ((Directory) fselement).getTotalSize();
			} else {
				totalSize += fselement.getSize();
			}
		}
		return totalSize;
			}
		finally
		{
			lock.unlock();
		}
	}

	public LinkedList<File> getFiles() {
		lock.lock();
		try{
		LinkedList<File> file = new LinkedList<File>();
		for (FSElement fselement : children) {
			if (fselement.isFile())
				file.add((File) fselement);
		}
		return file;
			}
		finally
		{
			lock.unlock();
		}
	}
	public boolean isDirectory() {
		return true;
	}

	public boolean isFile() {
		return false;
	}
	public static void main(String[] args) {
		System.out.println("Program Run Successful");
	}
}
