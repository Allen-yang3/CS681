package edu.umb.cs681.Hw13;

public class DepositRunnable implements Runnable {
	private BankAccount account;

	volatile boolean done = false;

	public void setDone() {
		done = true;
	}

	public DepositRunnable(BankAccount account) {
		this.account = account;
	}

	public void run() {
		while (true) {
			try {
				if (done) {
					break;
				}
				for (int i = 0; i < 10; i++) {
					account.deposit(100);
					Thread.sleep(2);
				}
			} catch (InterruptedException exception) {
			}
		}
	}
}
