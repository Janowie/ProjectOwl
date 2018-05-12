package src.users;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import javax.swing.JTextArea;

import materials.Homework;
import materials.Test;
import saving.SavingTeachers;
import src.organization.Observer;

@SuppressWarnings("serial")
public class Teacher extends User implements Serializable {
	
	/*
	 * Class uèite¾ rozširuje class User, špecifické metódy
	 * umoòujú pridáva študentov a testy èi úlohy. 
	 */
	
	int groupTaught = 0;
	SavingTeachers arrayTeachers;
	private List<Observer> observers = new ArrayList<Observer>();
	private int userID;
	double salary = 0;
	String IBAN;
	
	// konštruktor (super od Usera)
	public Teacher(String userFirstName, String userPassword, String Iban) throws ClassNotFoundException {
		super(userFirstName, userPassword);
		IBAN = Iban;
		arrayTeachers = new SavingTeachers();
		arrayTeachers.saveEmployee(this);
	}
	
	// metóda aktivovaná OfficeWorkerom alebo riadite¾om
	public void addGroup(int i) {
		groupTaught = i;
	}
	
	// metóda na získanie skupiny, ktorú danı uèite¾ uèí
	public int getGroup(int i) {
		return groupTaught;
	}
	
	// vráti ID uívate¾a
	public int getUserID() {
		return this.userID;
	}
	
	// nastaví ID uívate¾a a uloí zoznam uèite¾ov
	public void setUserID(int newID) {
		this.userID = newID;
		arrayTeachers.save();
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
	
	@SuppressWarnings("unused")
	public void addStudent(String username, String password, int IDgroup) throws ClassNotFoundException {
		try {
			Student newStudent = new Student(username,password, IDgroup);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addHomework(JTextArea area, int max, int min, String date, int num, String zadanie) {
		Homework newHomework = new Homework(max, min, date, num, zadanie);
		area.append(
				"Pridana uloha " + newHomework.getNumber() 
				+ "\nZadanie:\n" 
				+ newHomework.getDescription());
	}
	
	public void addTest(JTextArea area, int min, int cislo, String date, String addDescription) {
		Test newTest = new Test(min, cislo, date, addDescription);
	}

	
	
	

}
