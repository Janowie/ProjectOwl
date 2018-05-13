package src.gui;

/**
 * class pre thread, pocita pocet minut stravenych pouzivanim aplikacie
 * @author Jan
 *
 */
public class ThreadTimeSpent extends Thread {
	public void run() {
		int counter = 0;
		while (true) {
			if (counter % 60 == 0) {
				System.out.println("Time spent " + counter/60 + " min");
			}
			counter++;
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e){}
		}
	};
}
