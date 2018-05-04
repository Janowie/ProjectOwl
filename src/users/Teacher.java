package src.users;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;


import saving.SavingTeachers;
import src.organization.Observer;

@SuppressWarnings("serial")
public class Teacher extends User implements Serializable {
	
	int[] groupsTaught = new int[5];
	SavingTeachers arrayTeachers;
	private List<Observer> observers = new ArrayList<Observer>();
	private int userID;
	double salary = 0;
	String IBAN;
	

	public Teacher(String userFirstName, String userPassword, String userEmailAddres, String Iban) throws ClassNotFoundException {
		super(userFirstName, userPassword, userEmailAddres);
		IBAN = Iban;
		arrayTeachers = new SavingTeachers();
		arrayTeachers.saveEmployee(this);
	}
	
	public int getUserID() {
		return this.userID;
	}
	
	public void setUserID(int newID) {
		this.userID = newID;
		arrayTeachers.saveEmployee(this);
	}
	
	public double getSalary() {
	    return salary;
	}	

	// Observers
	public void attach(Observer observer){
	    observers.add(observer);		
	}

	public void notifyAllObservers(){
	    for (Observer observer : observers) {
	       observer.update();
	    }
	}
	
	public void addStudent(String username, String password, String emailAdress, int IDgroup) throws ClassNotFoundException {
		try {
			@SuppressWarnings("unused")
			Student newStudent = new Student(username,password,emailAdress, IDgroup);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	

}
