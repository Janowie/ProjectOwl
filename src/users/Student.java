package src.users;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
//import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextArea;

import materials.Test;
import saving.SavingGroups;
import saving.SavingStudents;

/**
 * Class Student je zakladny neabstraktny typ pouzivatela (tuto klasu Usera aj rozsiruje)
 * @author Jan
 *
 */
@SuppressWarnings("serial")
public class Student extends User implements Serializable {
	
	SavingStudents arrayStudents;
	SavingGroups arrayGroups;
	Test test;
	int groupNum;
	int userID = 0;
		
	/**
	 * Konstruktor prekonava konstruktor Usera a zaroven kontroluje
	 * ci pre daneho studenta existuje test. Pri vytvoreni sa student 
	 * ulozi do arrayListu studentov.
	 * @param firstName
	 * @param newPassword
	 * @param stGroup
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Student(String firstName, String newPassword, int stGroup) throws IOException, ClassNotFoundException {
		super(firstName, newPassword);
		groupNum = stGroup;
		arrayStudents = new SavingStudents();
		arrayGroups = new SavingGroups();
		this.setID(arrayStudents.getLenght());
		test = takeTest();
		
		arrayStudents.saveStudent(this);
	}
	

	/**
	 * Nastavi ID skupiny v ktorej je student priradeny
	 * @param i
	 */
	public void setGroupID(int i) {
		groupNum = i;
	}
	
	/**
	 * 
	 * @return ID skupiny studenta
	 */
	public int getGroupID() {
		return groupNum;
	}
	
	/**
	 * 
	 * @return ID studenta
	 */
	public int getID() {
		return userID;
	}

	/**
	 * nastavi ID studenta
	 * @param newID
	 */
	public void setID(int newID) {
		this.userID = newID;
	}
					
	/**
	 * privatna funkcia ktora kontroluje, ci sa zadany datum nachadza v rozmedzi 
	 * na kedy je dana skupina vytvorena
	 * @param today
	 * @return
	 */
	private boolean withinBorders(Date today) {
		groupNum = arrayStudents.findStudent(this.username).groupNum;
		boolean comp1 = arrayGroups.findGroup(groupNum).beginning.before(today);
		boolean comp2 = arrayGroups.findGroup(groupNum).beginning.equals(today);
		boolean comp3 = arrayGroups.findGroup(groupNum).end.after(today);
		boolean comp4 = arrayGroups.findGroup(groupNum).end.equals(today);
		
		if ((comp1 || comp2) && (comp3 || comp4)) {
			System.out.println("Sedi cas");
			return true;
		}
		else {
			System.out.println("Nesedi cas");
			return false;
		}
	}
	
	/**
	 * Na JTextArea vypise informacie o skupine, v ktorej je student priradeny,
	 * ak v tyzdni v ktorom si student chce svoj rozvrh zobrazit skupina ma hodinu
	 * @param area
	 * @param today
	 * @throws ClassNotFoundException
	 */
	public void printScheduleWeek(JTextArea area, Date today) throws ClassNotFoundException {
		arrayStudents = new SavingStudents();
		arrayGroups = new SavingGroups();
		
		groupNum = arrayStudents.findStudent(this.username).groupNum;
		
		if (groupNum == 0) {
			area.append("Student nie je priradeny v ziadnej skupine");
		}
		else {
			System.out.println("this.studentsGroup.end " + arrayGroups.findGroup(groupNum).end);
			
			area.append("Group ends " + arrayGroups.findGroup(groupNum).end);
			
			if (withinBorders(today)) {
				printSchedule(area);
			}
			else
				area.append("Ziadna hodina sa na dany tyzden nenasla..");
		}
	}
			
	/**
	 * Vypise informacie o skupine
	 * @param area
	 */
	private void printSchedule(JTextArea area) {
		area.append(
				"Trieda: " + arrayGroups.findGroup(groupNum).room +
				"\nCas: " + arrayGroups.findGroup(groupNum).time
		);
	}

	/**
	 * metoda vrati test ak ho moze student pisat
	 * @return
	 * @throws ClassNotFoundException
	 */
	private Test takeTest() throws ClassNotFoundException {
		Test newTest = null;
		try {
			newTest = newTest.load();
		}
		catch (NullPointerException e) {
			e.getMessage();
		}
		
		
		return newTest;
	}
	
	
}

