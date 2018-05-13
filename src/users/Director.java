package src.users;

import javax.swing.JTextArea;

import saving.SavingDirector;
import saving.SavingOfficeWorkers;
import saving.SavingStudents;
import saving.SavingTeachers;


/**
 * Class rozsiruje OfficeWorker
 * @author Jan
 *
 */
@SuppressWarnings("serial")
public class Director extends OfficeWorker {
	int userID = 4;
	SavingDirector arrayDirectors;
	
	/**
	 * konstruktor, ulozi Directora
	 * @param userFirstName
	 * @param newPassword
	 * @throws ClassNotFoundException
	 */
	public Director(String userFirstName, String newPassword) throws ClassNotFoundException {
		super(userFirstName, newPassword);
		arrayDirectors = new SavingDirector();
		directorSave();
	}
	
	/**
	 * metoda ulozi Directora
	 */
	private void directorSave() {
		arrayDirectors.saveDirector(this);
	}
	
	/**
	 * mazanie Directora
	 */
	public void deletePerson() {
		arrayDirectors.deleteDirector(this);
	}
	
	/**
	 * Metoda zmeni plat ucitela, upozorni observer
	 * @param username
	 * @param addedSalary
	 * @param area
	 * @throws ClassNotFoundException
	 */
	public void updateSalaryTeacher(String username, double addedSalary, JTextArea area) throws ClassNotFoundException {
		SavingTeachers savedTeachers = new SavingTeachers();
		area.setText("");
		if (savedTeachers.findTeacher(username) == null) {
			area.append("Incorrect username");
		}
		else {
			Teacher teacher = savedTeachers.findTeacher(username);
			teacher.salary = teacher.getSalary() + addedSalary;
		    area.append(teacher.username + " salary changed to " + String.valueOf(teacher.getSalary()));
		    teacher.notifyAllObservers();
		}	
	}

	/**
	 * Metoda zmeni plat OfficeWorkera
	 * @param username
	 * @param addedSalary
	 * @param area
	 * @throws ClassNotFoundException
	 */
	public void updateSalaryOffice(String username, double addedSalary, JTextArea area) throws ClassNotFoundException {
		SavingOfficeWorkers savedOffice = new SavingOfficeWorkers();
		area.setText("");
		if (savedOffice.findOffice(username) == null) {
			area.append("Incorrect username");
		}
		else {
			OfficeWorker office = savedOffice.findOffice(username);
			office.setSalary(office.getSalary() + addedSalary);
		    area.append(office.username + " salary changed to " + String.valueOf(office.getSalary()));
		}	
	}
	
	/**
	 * @return ID
	 */
	public int getUserID() {
		return userID;
	}
		
	/**
	 * vypise ucitelov a ich plat
	 * @param area
	 * @throws ClassNotFoundException
	 */
	public void printTeachers(JTextArea area) throws ClassNotFoundException {
		SavingTeachers savedTeachers = new SavingTeachers();
		int io = 0;
		area.setText("");
		for (int i = 0; i < savedTeachers.getLength(); i++) {
			io = i + 1;
			area.append("Ucitel" + io + " plat: " + savedTeachers.getTeacher(i).salary + "\n");
		}
	}
	
	/**
	 * vypise ulozenych OfficeWork a ich plat
	 * @param area
	 * @throws ClassNotFoundException
	 */
	public void printOffice(JTextArea area) throws ClassNotFoundException {
		SavingOfficeWorkers savedOffice = new SavingOfficeWorkers();
		int io = 0;
		area.setText("");
		for (int i = 0; i < savedOffice.getLenght(); i++) {
			io = i + 1;
			area.append("OfficeWorker" + io + " plat: " + savedOffice.getOffice(i).getSalary() + "\n");
		}
	}
	
	/**
	 *  Vypise vsetkych studentov a skupinu v ktorej su
	 * @param area
	 * @throws ClassNotFoundException
	 */
	public void printStudents(JTextArea area) throws ClassNotFoundException {
		SavingStudents savedStudents = new SavingStudents();
		int io = 0;
		area.setText("");
		for (int i = 0; i < savedStudents.getLenght(); i++) {
			io = i + 1;
			area.append("Student" + io + " v skupine: " + savedStudents.getStudent(i).groupNum + "\n");
		}
	}
	
	/**
	 * zadanie tasku pre OfficeWork
	 * @param username
	 * @param newMessage
	 * @throws ClassNotFoundException
	 */
	public void sendMessageOffice(String username, String newMessage) throws ClassNotFoundException {
		arrayOffice.load();
		OfficeWorker office = arrayOffice.findOffice(username);
		office.setMessage(newMessage);
		arrayOffice.save();
	}
	
}
