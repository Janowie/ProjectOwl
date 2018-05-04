package src.users;


import java.io.IOException;
import java.io.Serializable;
//import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextArea;

import saving.SavingGroups;
import saving.SavingStudents;


@SuppressWarnings("serial")
public class Student extends User implements Serializable {
	
	SavingStudents arrayStudents;
	SavingGroups arrayGroups;
	int groupNum;
	int userID = 0;
	
	public Student(String firstName, String newPassword,String emailAddress, int stGroup) throws IOException, ClassNotFoundException {
		super(firstName, newPassword, emailAddress);
		groupNum = stGroup;
		arrayStudents = new SavingStudents();
		arrayGroups = new SavingGroups();
		this.setID(arrayStudents.getLenght());
		arrayStudents.saveStudent(this);
	}
	
	public int getID() {
		return userID;
	}
	
	public void setID(int newID) {
		this.userID = newID;
	}
				
	private boolean withinBorders(Date today) {
		groupNum = arrayStudents.findStudent(this.username).groupNum;
		boolean comp1 = arrayGroups.findGroup(groupNum).beginning.before(today);
		boolean comp2 = arrayGroups.findGroup(groupNum).beginning.equals(today);
		boolean comp3 = arrayGroups.findGroup(groupNum).end.after(today);
		boolean comp4 = arrayGroups.findGroup(groupNum).end.equals(today);
		
		if ((comp1 || comp2) && (comp3 || comp4)) {
			return true;
		}
		else return false;
	}
	
	public void printScheduleWeek(JTextArea area, Date today) throws ClassNotFoundException {
		arrayStudents = new SavingStudents();
		arrayGroups = new SavingGroups();
		
		groupNum = arrayStudents.findStudent(this.username).groupNum;
		
		System.out.println("this.studentsGroup.end " + arrayGroups.findGroup(groupNum).end);
		
		area.append("Group ends " + arrayGroups.findGroup(groupNum).end);
		
		if (withinBorders(today)) {
			printSchedule(area);
		}
		else
			area.append("Ziadna hodina sa na dany tyzden nenasla..");
	}
	
	public void printScheduleWeekNorm(Date today) throws ClassNotFoundException {
		arrayStudents = new SavingStudents();
		arrayGroups = new SavingGroups();
		
		groupNum = arrayStudents.findStudent(this.username).groupNum;
		
		System.out.println("this.studentsGroup.end " + arrayGroups.findGroup(groupNum).end);
		
		System.out.println("Group ends " + arrayGroups.findGroup(groupNum).end);
		
		if (withinBorders(today)) {
			printScheduleNorm();
		}
		else
			System.out.println("Ziadna hodina sa na dany tyzden nenasla..");
	}
	
	private void printSchedule(JTextArea area) {
		area.append(
				"Trieda: " + arrayGroups.findGroup(groupNum).room +
				"\nCas: " + arrayGroups.findGroup(groupNum).time
		);
	}
	
	private void printScheduleNorm() {
		System.out.println(
				"Trieda: " + arrayGroups.findGroup(groupNum).room +
				"\nCas: " + arrayGroups.findGroup(groupNum).time
		);
	}
	
	
}

