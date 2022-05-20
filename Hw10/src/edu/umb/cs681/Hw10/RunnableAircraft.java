package edu.umb.cs681.Hw10;


public class RunnableAircraft implements Runnable {

	static Position position1 = new Position(12.23, 52.05, 30);
	static Position position2 = new Position(79.25, -22.10, 88);
	static Position position3 = new Position(44.12, 10.02, 21);
	
	public static void main(String[] args){
		
		RunnableAircraft one = new RunnableAircraft();
		RunnableAircraft two = new RunnableAircraft();
		RunnableAircraft three = new RunnableAircraft();
		Thread t1 = new Thread(one);
		Thread t2 = new Thread(two);
		Thread t3 = new Thread(three);
		
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {}

		System.out.println("End");
		
	}
	
	@Override
	public void run() {
		
		
		Aircraft air = new Aircraft(position1);
		
		System.out.println("The Starting Positon is : " + air.getPosition());
		
		air.setPosition(air.getPosition().changeLat(79.25).changeLong(-22.10).changeAlt(88));
		
		System.out.println("The second Positon is: " + air.getPosition());
		
		air.setPosition(air.getPosition().changeLat(44.12).changeLong(10.02).changeAlt(21));
		
		System.out.println("The last Positon is: " + air.getPosition());
		
		
	}

}