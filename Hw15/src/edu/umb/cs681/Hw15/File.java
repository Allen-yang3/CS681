package edu.umb.cs681.Hw15;

import java.time.LocalDateTime;

public class File extends FSElement {
	public File(Directory parent, String name, int size, LocalDateTime creationTime) {
		super(parent, name, size, creationTime);
		parent.appendChild(this);
	}
	
	public boolean isDirectory() {
		return false;
	}
	
	public boolean isFile() {
		return true;
	}
	
}

