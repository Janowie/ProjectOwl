package src.organization;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextArea;

import saving.SavingGroups;
import saving.SavingTeachers;
import src.users.Group;
import src.users.Teacher;

/**
 * Class Schedule sluzi na vypisovanie rozvrhov uzivatelov,
 * nie je sucastou hierarchie
 * @author Jan
 *
 */
public class Schedule {
	SavingGroups arrayGroups;
	SavingTeachers arrayTeachers;
	long difference = 0;

	/**
	 * konstruktor, vytvori Objekt ulozenych ucitelov a skupin
	 * @throws ClassNotFoundException
	 */
	public Schedule() throws ClassNotFoundException {
		arrayGroups = new SavingGroups();
		arrayTeachers = new SavingTeachers();
	}

	/**
	 * vrati pocet ostavajucich dni trvania skupiny
	 * @param group
	 * @return
	 */
	private long dayLeft(Group group) {
		Date today = new Date();
		return group.end.getTime() - today.getTime();		
	}

	/**
	 * vypise informacie o skupine
	 * @param group
	 * @param area
	 */
	private void printGroup(Group group, JTextArea area) {	
		difference = dayLeft(group);	
		

			area.append("\nSkupina: " + group.getID() 
			+ "\nCas: " + group.time 
			+  "\nMiestnost: " + group.room 
			+ "\nDen: " + group.day 
			+ "\nOstava: " + (difference/604800000+1));
	
		
	}
	
	/**
	 * metoda vypise vsetky skupiny, ktore su dane na dany den
	 * @param string
	 * @param area
	 * @throws ClassNotFoundException
	 */
	public void printScheduleDay(String string, JTextArea area) throws ClassNotFoundException {
		int i = 0;
		int counter = 0;
		
		arrayGroups = new SavingGroups();
		
		while (i < arrayGroups.getLenght()) {
			if (string.isEmpty()) {
				System.out.println("\n");
				printGroup(arrayGroups.findGroup(i), area);
				counter++;
			}
			else if (arrayGroups.findGroup(i).day.equals(string)) {
				System.out.println("\n");
				printGroup(arrayGroups.findGroup(i), area);
				counter++;
			}
			i++;
		}
		if (counter == 0) {
			System.out.println("Nenasiel som skupinu na den " + string + ".");
		}
	}	

	/**
	 * z daneho datumu vrati den v tyzdni
	 * @param date
	 * @return
	 */
	private int dayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * vrati datum dalsieho tyzdna
	 * @param date
	 * @return
	 */
	private Date nextWeek(Date date) {
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 7);
		date = c.getTime();
		
		return date;
	}

	/**
	 * metoda nastavi a vrati datum zaciatku tyzdna
	 * @param date
	 * @return
	 */
	private Date boundaryStart(Date date) {
		Date boundaryStartDate = new Date();
		int dayOfWeek = dayOfWeek(date);
		int decrement = dayOfWeek - 1;
		Calendar c = Calendar.getInstance();
		
		c.setTime(date);
		c.add(Calendar.DATE, -decrement);
		boundaryStartDate = c.getTime();		
		
		return boundaryStartDate;
	}

	/**
	 * metoda nastavi a vrati datum konca tyzdna
	 * @param date
	 * @return
	 */
	private Date boundaryEnd(Date date) {
		Date boundaryEndDate = new Date();
		int dayOfWeek = dayOfWeek(date);
		int increment = 7 - dayOfWeek;
		Calendar c = Calendar.getInstance();
		
		c.setTime(date);
		c.add(Calendar.DATE, increment);
		boundaryEndDate = c.getTime();		
		
		return boundaryEndDate;
	}

	/**
	 * metoda zisti, ci je dana skupina v nastavenych hraniciach - v danom tyzdni
	 * @param group
	 * @param today
	 * @return
	 */
	private boolean inBoundaries(Group group, Date today) {
		boolean inBoundaries = false;
		
		if ((group.beginning.before(boundaryStart(today)) || group.beginning.equals(boundaryStart(today))) && (group.end.after(boundaryEnd(today)) || group.end.equals(boundaryEnd(today)))) {
			inBoundaries = true;
		}
		else {
			inBoundaries = false;
		}		
		
		return inBoundaries;
	}
 
	/**
	 * metoda vypise vsetky skupiny, ktore su v rozmedzi aktualneho tyzdna
	 * @param area
	 * @throws ClassNotFoundException
	 */
	public void printScheduleWeek(JTextArea area) throws ClassNotFoundException {
		int counter = 0;
		Date today = new Date();
		Group group;
		arrayGroups.load();
		
		for (int i = 0; i < arrayGroups.getLenght() + 1; i++) {
			group = arrayGroups.findGroup(i);
			if((group != null) && (inBoundaries(group, today))) {
				printGroup(group, area);
				counter++;
			}
		}


		
	}

	/**
	 * metoda vypise tyzdenny rozvrh ucitela
	 * @param area
	 * @param teacher
	 * @throws ClassNotFoundException
	 */
	public void printTeachersScheduleWeek(JTextArea area, Teacher teacher) throws ClassNotFoundException {
		int counter = 0;
		Date today = new Date();
		arrayGroups.load();
		
		
		for (int i = 0; i < arrayGroups.getLenght(); i++) {
			
			if ((arrayGroups.findGroup(i) != null) && (inBoundaries(arrayGroups.findGroup(i), today))) {
				if (teacher.getGroup(i) == arrayGroups.findGroup(i).getID()) {
					System.out.println("nasiel som skupinu s ID: " + arrayGroups.findGroup(i).getID());
					printGroup(arrayGroups.findGroup(i), area);
					counter++;
				}	
			}
			
			
		}	

		area.append("\n\n");

		
	}
	
	/**
	 * metoda vypise nasledujuci tyzdenny rozvrh ucitela
	 * @param area
	 */
	public void printScheduleNextWeek(JTextArea area) {
		int counter = 0;
		Date today = new Date();
		
		for (int i = 0; i < arrayGroups.getLenght(); i++) {
			if (inBoundaries(arrayGroups.findGroup(i), nextWeek(today))) {
				printGroup(arrayGroups.findGroup(i), area);
				counter++;
			}
			if (counter == 0) {
				System.out.println("Nenasiel som skupinu na dany tyzden.");
			}	
		}
		
	}
	
}


