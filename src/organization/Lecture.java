package organization;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Lecture {

	Date lectureDate;
	users.Teacher lectureTeacher;
	private List<Observer> observers = new ArrayList<Observer>();
	
	
	Lecture(Date setDate, users.Teacher setTeacher) {
		this.lectureDate = setDate;
		this.lectureTeacher = setTeacher;
	}
	
	public void attach(Observer observer){
	      observers.add(observer);		
	}

	public void notifyAllObservers(){
	   for (Observer observer : observers) {
	         observer.update();
	   }
	}
	
	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}

}
