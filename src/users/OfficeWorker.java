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

@SuppressWarnings("serial")
public class OfficeWorker extends User implements Serializable {
	SavingOfficeWorkers arrayOffice = new SavingOfficeWorkers();
	public SavingGroups arrayGroups = new SavingGroups();
	SavingTeachers arrayTeachers = new SavingTeachers();
	SavingStudents arrayStudents = new SavingStudents();
	private String message = "";
	
	int userID = 3;
	private double salary = 0;
	
		// O
		//	F
		//	  F
		//		I
		//		   C
		//			  E
	
	public OfficeWorker(String userFirstName, String newPassword) throws ClassNotFoundException {
		super(userFirstName, newPassword);
		arrayOffice = new SavingOfficeWorkers();
		arrayGroups = new SavingGroups();
		arrayStudents = new SavingStudents();
		arrayTeachers = new SavingTeachers();
		arrayOffice.saveOffice(this);
	}
	
	public void checkMessage(JTextArea area) {
		if (message.length() > 0) {
			area.append(message);
		}
		else {
			area.append("No new tasks.");
		}
	}
	
	public void markDone(JTextArea area) {
		message = "";
		area.setText("");
		arrayOffice.save();
	}
	
	public void setMessage(String newMessage) {
		message = newMessage;
	}
	
	public void numberOfStudents(JTextArea area) {
		area.append("Number of students: " + String.valueOf(arrayStudents.getLenght()));
	}
	
	public void numberOfGroups(JTextArea area) {
		area.append("Number of groups: " + String.valueOf(arrayGroups.getLenght()));
	}
	
	public int getUserID() {
		return userID;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double amount) {
		salary = salary + amount;
		arrayOffice.save();
	}

		
	//		ADD TEACHER	TO GROUP	//
	
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
	
	private void vypis(int ID) throws ClassNotFoundException {
		arrayTeachers.load();
		System.out.println(arrayTeachers.findTeacherByGroup(ID).username + " added to group " + arrayTeachers.findTeacherByGroup(ID).groupTaught);
	}
	
	//		DELETE TEACHER		//
	
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
	
	//		ADD STUDENT		//
	
	public void addStudent(String username, int ID) throws ClassNotFoundException {
		arrayStudents.load();
		arrayStudents.findStudent(username).setGroupID(ID);
		arrayStudents.save();
	}
	
	//		DELETE STUDENT		//
	
	public void removeStudent(String username, int ID) throws ClassNotFoundException {
		arrayStudents.load();
		arrayStudents.findStudent(username).setGroupID(0);
		arrayStudents.save();
	}
	
	
	//	SET THE DAY OF WEEK		//
	
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
		
	//		ADD DETAILS		//
	
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
	
	//		SET DATE		//	
	WorkTime weeksToDays = (WorkTime & Serializable)(int week) -> week * 7;
	
	private Date duration(Date beginningDate, int week) throws ParseException {
		Date endDate = new Date();
		Calendar c = Calendar.getInstance();
		int days = weeksToDays.perform(week);
		
		c.setTime(beginningDate);
		c.add(Calendar.DAY_OF_MONTH, days);  
		endDate = c.getTime();

		return endDate;
	}
	
	private void setDate(int ID, String timeInput, int duration) throws IOException, ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date beginningDate = null;
		Date endDate;
		
		try {
			beginningDate = format.parse(timeInput);
			arrayGroups.findGroup(ID).beginning = beginningDate;
		}
		catch (ParseException e) {
			System.out.println("Invalid date format");
		}
		
		endDate = duration(beginningDate, duration);
		
		arrayGroups.findGroup(ID).end = endDate;

		//String beg = format.format(beginningDate);
		//String end = format.format(endDate);
		
		arrayGroups.save();
	}

	
	
}

	





interface WorkTime {
	int perform(int week);
}
