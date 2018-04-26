package src.gui;

class CustomThread extends Thread {
	   private Thread t;
	   private String threadName;
	   
	   CustomThread( String name) {
	      threadName = name;
	      System.out.println("Creating " +  threadName );
	   }
	   
	   public void run() {
		   
	   }
	   
	   public void start () {
	      System.out.println("Starting " +  threadName );
	      if (t == null) {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }
	}
