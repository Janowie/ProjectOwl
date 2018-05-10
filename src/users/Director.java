package src.users;

import javax.swing.JTextArea;

import saving.SavingDirector;
import saving.SavingOfficeWorkers;
import saving.SavingStudents;
import saving.SavingTeachers;

@SuppressWarnings("serial")
public class Director extends OfficeWorker {
	int userID = 4;
	SavingDirector arrayDirectors;
	
	public Director(String userFirstName, String newPassword) throws ClassNotFoundException {
		super(userFirstName, newPassword);
		arrayDirectors = new SavingDirector();
		directorSave();
	}
	
	//	SAVE THIS		//
	private void directorSave() {
		arrayDirectors.saveDirector(this);
	}
	
	//	DELETE THIS		//
	public void deletePerson() {
		arrayDirectors.deleteDirector(this);
	}
	
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
	
	public int getUserID() {
		return userID;
	}
		
	public void printTeachers(JTextArea area) throws ClassNotFoundException {
		SavingTeachers savedTeachers = new SavingTeachers();
		int io = 0;
		area.setText("");
		for (int i = 0; i < savedTeachers.getLength(); i++) {
			io = i + 1;
			area.append("Ucitel" + io + " plat: " + savedTeachers.getTeacher(i).salary + "\n");
		}
	}
	
	public void printOffice(JTextArea area) throws ClassNotFoundException {
		SavingOfficeWorkers savedOffice = new SavingOfficeWorkers();
		int io = 0;
		area.setText("");
		for (int i = 0; i < savedOffice.getLenght(); i++) {
			io = i + 1;
			area.append("OfficeWorker" + io + " plat: " + savedOffice.getOffice(i).getSalary() + "\n");
		}
	}
	
	public void printStudents(JTextArea area) throws ClassNotFoundException {
		SavingStudents savedStudents = new SavingStudents();
		int io = 0;
		area.setText("");
		for (int i = 0; i < savedStudents.getLenght(); i++) {
			io = i + 1;
			area.append("Student" + io + " v skupine: " + savedStudents.getStudent(i).groupNum + "\n");
		}
	}
	
}
