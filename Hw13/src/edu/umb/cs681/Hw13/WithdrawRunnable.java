package edu.umb.cs681.Hw13;

public class WithdrawRunnable implements Runnable {
	private BankAccount account;
	volatile boolean done = false;

	public void setDone() {
		done = true;
	}

	public WithdrawRunnable(BankAccount account) {
		this.account = account;
	}

	public void run() {
		while (true) {
			try {
				if (done) {
					break;
				}
				for (int i = 0; i < 10; i++) {
					account.withdraw(100);
					Thread.sleep(2);
				}
			} catch (InterruptedException exception) {
			}
		}
	}
}
