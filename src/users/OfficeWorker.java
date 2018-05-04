package src.users;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;

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
	
	int userID = 0;
	private double salary = 0;
	
		// O
		//	F
		//	  F
		//		I
		//		   C
		//			  E
	
	public OfficeWorker(String userFirstName, String newPassword, String userEmailAddres) throws ClassNotFoundException {
		super(userFirstName, newPassword, userEmailAddres);
		arrayOffice = new SavingOfficeWorkers();
		arrayGroups = new SavingGroups();
		arrayStudents = new SavingStudents();
		arrayTeachers = new SavingTeachers();
		
		setUserID();
		arrayOffice.saveOffice(this);
	}
	
	private void setUserID() {
		this.userID = arrayOffice.getLenght();
	}

		
	//		ADD TEACHER	TO GROUP	//
	
	public void setTeacher(int ID, String teacherUsername) throws IOException {
		arrayGroups.findGroup(ID).getGroupArrayTeachers().add(arrayTeachers.findTeacher(teacherUsername));
		arrayTeachers.findTeacher(teacherUsername).salary += 10;
		arrayTeachers.findTeacher(teacherUsername).notifyAllObservers();
		arrayGroups.save();
	}
	
	//		DELETE TEACHER		//
	
	public void removeTeacher(int ID, String teacherUsername) throws IOException {
		arrayGroups.findGroup(ID).getGroupArrayTeachers().remove(arrayTeachers.findTeacher(teacherUsername));
		arrayTeachers.findTeacher(teacherUsername).salary -= 10;
		arrayTeachers.findTeacher(teacherUsername).notifyAllObservers();
		arrayGroups.save();
	}
	
	//		ADD STUDENT		//
	
	public void addStudent(String username, int ID) {
		arrayGroups.findGroup(ID).groupArrayStudents.add(arrayStudents.findStudent(username));
		arrayGroups.save();
	}
	
	//		DELETE STUDENT		//
	
	public void removeStudent(String username, int ID) {
		arrayGroups.findGroup(ID).groupArrayStudents.remove(arrayStudents.findStudent(username));
		arrayGroups.save();
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
	
	public void addDetails(int ID, String gTime, String gDay, int gDuration, String gRoom) throws ClassNotFoundException {
		arrayGroups = new SavingGroups();
		
		arrayGroups.findGroup(ID).time = gTime;
		arrayGroups.findGroup(ID).day = gDay;
		arrayGroups.findGroup(ID).duration = gDuration;
		arrayGroups.findGroup(ID).room = gRoom;
		arrayGroups.findGroup(ID).dayOfTheWeek = setDayOfWeek(arrayGroups.findGroup(ID));
		
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
	
	public void setDate(int ID, String timeInput, int duration) throws IOException, ParseException {
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
		
		String beg = format.format(beginningDate);
		String end = format.format(endDate);
		
		arrayGroups.save();
	}
	
	
	
	
	
	
	
	
	
	
	
//	NUMBER OF SAVED OFFICE		//
	
	
	//	BILD ARRAY FROM ALL SAVED OFFICE		//
	
	
	
	
	//		SAVE THIS		//
	
	
	
	//		LOAD THIS		//
	
	
	
	//		DELETE THIS		//
	
}

	





interface WorkTime {
	int perform(int week);
}
