package src.gui;

public class ThreadTimeSpent extends Thread {
	public void run() {
		int counter = 0;
		while (true) {
			System.out.println("Time spent " + counter + " sec");
			counter++;
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e){}
		}
	};
}
