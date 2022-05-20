package edu.umb.cs681.Hw15;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;
public abstract class FSElement {
	
		private String name;
		private int size;
		private LocalDateTime creationTime;
		private Directory parent;
		
		protected ReentrantLock lock = new ReentrantLock();
		
		public FSElement(Directory parent, String name, int size, LocalDateTime creationTime) {
			if (parent != null) {
				parent.appendChild(this);
			} else {
				this.parent = null;
			}
			this.name = name;
			this.size = size;
			this.creationTime = creationTime;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public LocalDateTime getCreationTime() {
			return creationTime;
		}

		public void setCreationTime(LocalDateTime creationTime) {
			this.creationTime = creationTime;
		}

		public Directory getParent() {
			return parent;
		}

		public void setParent(Directory parent) {
			this.parent = parent;
		}
		
		abstract public boolean isDirectory();
		
		abstract public boolean isFile();
}