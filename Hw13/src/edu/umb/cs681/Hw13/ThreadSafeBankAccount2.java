package edu.umb.cs681.Hw13;

import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2 implements BankAccount{
	private double balance = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition sufficientFundsCondition = lock.newCondition();
	private Condition belowUpperLimitFundsCondition = lock.newCondition();
	
	public void deposit(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + 
					" (d): current balance: " + balance);
			while(balance >= 300){
				System.out.println(Thread.currentThread().getId() + 
						" (d): await(): Balance exceeds the upper limit.");
				belowUpperLimitFundsCondition.await();
			}
			balance += amount;
			System.out.println(Thread.currentThread().getId() + 
					" (d): new balance: " + balance);
			sufficientFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public void withdraw(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + 
					" (w): current balance: " + balance);
			while(balance <= 0){
				System.out.println(Thread.currentThread().getId() + 
						" (w): await(): Insufficient funds");
				sufficientFundsCondition.await();
			}
			balance -= amount;
			System.out.println(Thread.currentThread().getId() + 
					" (w): new balance: " + balance);
			belowUpperLimitFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public static void main(String[] args){
		ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
		DepositRunnable depositRunnable= new DepositRunnable(bankAccount);
		WithdrawRunnable withdrawRunnable = new WithdrawRunnable(bankAccount);
		
		int num = 5;
		LinkedList<Thread> threads = new LinkedList<Thread>();

		for (int i = 0; i < num; i++) {
			Thread depositThread = new Thread(depositRunnable);
			Thread withdrawThread = new Thread(withdrawRunnable);
			threads.add(withdrawThread);
			threads.add(depositThread);
			depositThread.start();
			withdrawThread.start();
		}
		try {
			Thread.sleep(200);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		
//		Have the main thread call interrupt(), after calling setDone(), on each “withdrawal” thread.
		depositRunnable.setDone();
		withdrawRunnable.setDone();
		for (int i = 0; i < num; i++) {
			 threads.get(i).interrupt();
		}
		for (int i = 0; i < num; i++){
			try {								
				threads.get(i).join();
			}
			catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
}
