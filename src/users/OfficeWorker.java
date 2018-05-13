package src.users;
import java.io.*;
import java.text.*;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextArea;

import saving.SavingGroups;
import saving.SavingOfficeWorkers;
import saving.SavingStudents;
import saving.SavingTeachers;


/**
 * Class OfficeWorker
 * @author Jan
 *
 */
@SuppressWarnings("serial")
public class OfficeWorker extends User implements Serializable {
	SavingOfficeWorkers arrayOffice = new SavingOfficeWorkers();
	public SavingGroups arrayGroups = new SavingGroups();
	SavingTeachers arrayTeachers = new SavingTeachers();
	SavingStudents arrayStudents = new SavingStudents();
	private String message = "";
	
	int userID = 3;
	private double salary = 0;
	
	/**
	 * konstruktor prekonany
	 * @param userFirstName
	 * @param newPassword
	 * @throws ClassNotFoundException
	 */
	public OfficeWorker(String userFirstName, String newPassword) throws ClassNotFoundException {
		super(userFirstName, newPassword);
		arrayOffice = new SavingOfficeWorkers();
		arrayGroups = new SavingGroups();
		arrayStudents = new SavingStudents();
		arrayTeachers = new SavingTeachers();
		arrayOffice.saveOffice(this);
	}
	
	/**
	 *  skontroluje ci danemu pracovnikovi nezanechal riaditel ulohu
	 * @param area
	 */
	public void checkMessage(JTextArea area) {
		if (message.length() > 0) {
			area.append(message);
		}
		else {
			area.append("No new tasks.");
		}
	}
	
	/**
	 * zadany task uznaci za splneny
	 * @param area
	 */
	public void markDone(JTextArea area) {
		message = "";
		area.setText("");
		arrayOffice.save();
	}
	
	/**
	 * nastavi task pre officeWorkera
	 * @param newMessage
	 */
	public void setMessage(String newMessage) {
		message = newMessage;
	}
	
	/**
	 * vypise pocet studentov
	 * @param area
	 */
	public void numberOfStudents(JTextArea area) {
		area.append("Number of students: " + String.valueOf(arrayStudents.getLenght()));
	}
	
	/**
	 * vypise pocet skupin
	 * @param area
	 */
	public void numberOfGroups(JTextArea area) {
		area.append("Number of groups: " + String.valueOf(arrayGroups.getLenght()));
	}
	
	/**
	 * 
	 * @return vrati ID pouzivatela
	 */
	public int getUserID() {
		return userID;
	}
	
	/**
	 * 
	 * @return vrati vyplatu
	 */
	public double getSalary() {
		return salary;
	}
	
	/**
	 * nastavi vyplatu, ulozi uzivatela
	 */
	public void setSalary(double amount) {
		salary = salary + amount;
		arrayOffice.save();
	}

	/**
	 * prida ucitela do skupiny
	 * @param ID
	 * @param teacherUsername
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void setTeacher(int ID, String teacherUsername) throws IOException, ClassNotFoundException {
		arrayTeachers.load();
		if (arrayTeachers.findTeacher(teacherUsername) != null) {
			arrayTeachers.findTeacher(teacherUsername).addGroup(ID);
			arrayTeachers.findTeacher(teacherUsername).salary += 10;
			arrayTeachers.findTeacher(teacherUsername).notifyAllObservers();
			arrayTeachers.save();
			vypis(ID);
		}
		else {
			System.out.println("Zadany ucitel neexistuje.");
		}
		
	}
	
	/**
	 * Vypise meno ucitela a skupinu, do ktorej bol priradeny
	 * @param ID
	 * @throws ClassNotFoundException
	 */
	private void vypis(int ID) throws ClassNotFoundException {
		arrayTeachers.load();
		System.out.println(arrayTeachers.findTeacherByGroup(ID).username + " added to group " + arrayTeachers.findTeacherByGroup(ID).groupTaught);
	}
	
	/**
	 * vymazat ucitela	
	 * @param ID
	 * @param teacherUsername
	 * @throws IOException
	 */
	public void removeTeacher(int ID, String teacherUsername) throws IOException {
		try {
			arrayTeachers.load();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		arrayTeachers.findTeacher(teacherUsername).addGroup(0);
		arrayTeachers.findTeacher(teacherUsername).salary -= 10;
		arrayTeachers.findTeacher(teacherUsername).notifyAllObservers();
		arrayTeachers.save();
	}
	
	/**
	 * prida studenta do skupiny
	 * @param username
	 * @param ID
	 * @throws ClassNotFoundException
	 */
	public void addStudent(String username, int ID) throws ClassNotFoundException {
		arrayStudents.load();
		arrayStudents.findStudent(username).setGroupID(ID);
		arrayStudents.save();
	}
			
	/**
	 * odstrani studenta zo skupiny
	 * @param username
	 * @param ID
	 * @throws ClassNotFoundException
	 */
	public void removeStudent(String username, int ID) throws ClassNotFoundException {
		arrayStudents.load();
		arrayStudents.findStudent(username).setGroupID(0);
		arrayStudents.save();
	}
	
	/**
	 * Premeni string s dnom v skupine na cislo
	 * @param group
	 * @return
	 */
	private int setDayOfWeek(Group group) {
		String[] strDays = new String[] {"Pondelok", "Utorok", "Streda", "Stvrtok",
		        "Piatok", "Sobota",  "Nedela" };
		int dayOfWeek = 0;
		for (int i = 0; i < strDays.length; i++) {
			if (group.day.equals(strDays[i])) {
				dayOfWeek = i + 1;
			}
		}
		
		return dayOfWeek;
	
	}
			
	/**
	 * prida detaily k skupine
	 * @param ID skupiny
	 * @param gTime cas hodin
	 * @param gDay den v tyzdni
	 * @param begTime datum zaciatku kurzu
	 * @param gDuration trvanie kurzu v tyzdnoch
	 * @param gRoom 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void addDetails(int ID, String gTime, String gDay, String begTime, int gDuration, String gRoom) throws ClassNotFoundException, IOException, ParseException {
		arrayGroups.load();
		
		arrayGroups.findGroup(ID).time = gTime;
		arrayGroups.findGroup(ID).day = gDay;
		arrayGroups.findGroup(ID).duration = gDuration;
		arrayGroups.findGroup(ID).room = gRoom;
		arrayGroups.findGroup(ID).dayOfTheWeek = setDayOfWeek(arrayGroups.findGroup(ID));
		setDate(ID, begTime, gDuration);  
		
		arrayGroups.save();
	}
	
	/**
	 * Lambda vyraz vrati pocet dni zo zadaneho poctu tyzdnov
	 */
	WorkTime weeksToDays = (WorkTime & Serializable)(int week) -> week * 7;
	
	// vrati datum konca trvania kurzu
	private Date duration(Date beginningDate, int week) throws ParseException {
		Date endDate = new Date();
		Calendar c = Calendar.getInstance();
		int days = weeksToDays.perform(week);
		
		c.setTime(beginningDate);
		c.add(Calendar.DAY_OF_MONTH, days);  
		endDate = c.getTime();

		return endDate;
	}
	
	/**
	 * na zaklade stringu nastavi zaciatok kurzu na datum, ulozi skupinu
	 * @param ID skupiny
	 * @param timeInput cas vo formate dd.mm.yyyy
	 * @param duration dlzka trvania v tyzdnoch
	 * @throws IOException
	 * @throws ParseException
	 */
	private void setDate(int ID, String timeInput, int duration) throws IOException, ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date beginningDate = null;
		Date endDate;
		
		try {
			beginningDate = format.parse(timeInput);
			arrayGroups.findGroup(ID).beginning = beginningDate;
		}
		catch (ParseException e) {
		}
		
		endDate = duration(beginningDate, duration);
		
		arrayGroups.findGroup(ID).end = endDate;

		
		arrayGroups.save();
	}

	
	
}

	




/**
 * interface k lambda vyrazu
 * @author Jan
 *
 */
interface WorkTime {
	int perform(int week);
}
