package src.users;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import javax.swing.JTextArea;

import materials.Homework;
import materials.Test;
import saving.SavingTeachers;
import src.organization.Observer;

/**
 *  Class ucitel rozsiruje class User, specificke metody
 *  umoznuju pridavat studentov, testy ci ulohy. 
 * @author Jan
 *
 */
@SuppressWarnings("serial")
public class Teacher extends User implements Serializable {
	
	int groupTaught = 0;
	SavingTeachers arrayTeachers;
	private List<Observer> observers = new ArrayList<Observer>();
	private int userID;
	double salary = 0;
	String IBAN;
	
	/**
	 * konstruktor (super od Usera)
	 * @param userFirstName
	 * @param userPassword
	 * @param Iban
	 * @throws ClassNotFoundException
	 */
	public Teacher(String userFirstName, String userPassword, String Iban) throws ClassNotFoundException {
		super(userFirstName, userPassword);
		IBAN = Iban;
		arrayTeachers = new SavingTeachers();
		arrayTeachers.saveEmployee(this);
	}

	/**
	 *  metoda aktivovana OfficeWorkerom alebo riaditelom
	 * @param i
	 */
	public void addGroup(int i) {
		groupTaught = i;
	}
	
	/**
	 * metoda na získanie skupiny, ktoru dany ucitel ucí
	 * @param i
	 * @return
	 */
	public int getGroup(int i) {
		return groupTaught;
	}
	
	/**
	 * vrati ID uzivatela
	 * @return
	 */
	public int getUserID() {
		return this.userID;
	}
	
	/**
	 * nastavi ID uzivatela a ulozi zoznam ucitelov
	 * @param newID
	 */
	public void setUserID(int newID) {
		this.userID = newID;
		arrayTeachers.save();
	}
	
	/**
	 * 
	 * @return velkost vyplaty
	 */
	public double getSalary() {
	    return salary;
	}	

	/**
	 * Observers
	 * @param observer
	 */
	public void attach(Observer observer){
	    observers.add(observer);		
	}

	/**
	 *  upozorni vsetkych observerov na zmenu
	 */
	public void notifyAllObservers(){
	    for (Observer observer : observers) {
	       observer.update();
	    }
	}
	
	/**
	 * metoda vytvora objekt Student 
	 * @param username
	 * @param password
	 * @param IDgroup
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unused")
	public void addStudent(String username, String password, int IDgroup) throws ClassNotFoundException {
		try {
			Student newStudent = new Student(username,password, IDgroup);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * metodou citel vytvori ulohu pre ziaka
	 * @param area
	 * @param max
	 * @param min
	 * @param date
	 * @param num
	 * @param zadanie
	 */
	public void addHomework(JTextArea area, int max, int min, String date, int num, String zadanie) {
		Homework newHomework = new Homework(max, min, date, num, zadanie);
		area.append(
				"Pridana uloha " + newHomework.getNumber() 
				+ "\nZadanie:\n" 
				+ newHomework.getDescription());
	}
	
	/**
	 * metoda vytvori test pre ziaka
	 * @param area
	 * @param min
	 * @param cislo
	 * @param date
	 * @param addDescription
	 */
	public void addTest(JTextArea area, int min, int cislo, String date, String addDescription) {
		Test newTest = new Test(min, cislo, date, addDescription);
	}

	
	
	

}
