package src.users;

import java.util.*;

import src.organization.Observer;

@SuppressWarnings("serial")
public class Teacher extends User {
	
	List<Group> groupsTaught = new ArrayList<Group>();
	private List<Observer> observers = new ArrayList<Observer>();
	private double salary = 0;
	String IBAN;

	public Teacher(String userFirstName, String userLastName, String userEmailAddres, String Iban) {
		super(userFirstName, userLastName, userEmailAddres);
		IBAN = Iban;
	}

	public double getSalary() {
	    return salary;
	}

	public void updateSalary(double addedSalary) {
		salary =  salary + addedSalary;
	    System.out.println("Salary increased to " + String.valueOf(getSalary()));
	    notifyAllObservers();
	}

	public void attach(Observer observer){
	    observers.add(observer);		
	}

	public void notifyAllObservers(){
	    for (Observer observer : observers) {
	       observer.update();
	    }
	} 	
	
	

}
