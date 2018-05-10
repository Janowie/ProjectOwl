package src.organization;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextArea;

import saving.SavingGroups;
import saving.SavingTeachers;
import src.users.Group;
import src.users.Teacher;


public class Schedule {
	SavingGroups arrayGroups;
	SavingTeachers arrayTeachers;
	long difference = 0;

	
	//		CONSTRUCTOR		//
	public Schedule() throws ClassNotFoundException {
		arrayGroups = new SavingGroups();
		arrayTeachers = new SavingTeachers();
	}
	
	//		DAYS LEFT		//
	private long dayLeft(Group group) {
		Date today = new Date();
		return group.end.getTime() - today.getTime();		
	}
	
	
	private void printGroup(Group group, JTextArea area) {	
		difference = dayLeft(group);	
		

			area.append("\nSkupina: " + group.getID() 
			+ "\nCas: " + group.time 
			+  "\nMiestnost: " + group.room 
			+ "\nDen: " + group.day 
			+ "\nOstava: " + (difference/604800000+1));
	
		
	}
	
	//		PRINT SCHEDULE DAY		//
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
	
	//		PRINT CURRENT WEEK SCHEDULE		//
	private int dayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK);
	}
	
	private Date nextWeek(Date date) {
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 7);
		date = c.getTime();
		
		return date;
	}
	
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
	
	//private ArrayList<Group> reorderArrayList(ArrayList<Group> unordered) {
	//	ArrayList<Group> orderedList = new ArrayList<Group>();
	//	int index = 1;
	//	int listSize = unordered.size();
		
	//	while (listSize > 0) {
	//		for (int i = 0; i < unordered.size(); i++) {
	//			if (unordered.get(i).dayOfTheWeek == index) {
	//				orderedList.add(unordered.get(i));
	//				listSize--;
	//			}
	//		}
	//		index++;
	//	}		
		
	//	return orderedList;
	//}
	
	//	PRINT SCHEDULE WEEK		//
	public void printScheduleWeek(JTextArea area) throws ClassNotFoundException {
		int counter = 0;
		Date today = new Date();
		Group group;
		arrayGroups.load();
		//ArrayList<Group> workList = new ArrayList<Group>();
		
		for (int i = 0; i < arrayGroups.getLenght() + 1; i++) {
			group = arrayGroups.findGroup(i);
			if((group != null) && (inBoundaries(group, today))) {
				//workList.add(arrayGroups.findGroup(i));
				printGroup(group, area);
				counter++;
			}
		}
		
		if (counter == 0) {
			System.out.println("Nenasiel som skupinu na dany tyzden.");
		}
		
		//workList = reorderArrayList(workList);
		
		//for (int i = 0; i < workList.size(); i++) {
		//	printGroup(workList.get(i), area);
		//}

		
	}
	
	//	PRINT TEACHERS SCHEDULE WEEK		//
	//arrayGroups.findGroup(i).getGroupArrayTeachers().get(0).username.equals(teacher.username)
	public void printTeachersScheduleWeek(JTextArea area, Teacher teacher) throws ClassNotFoundException {
		int counter = 0;
		Date today = new Date();
		//ArrayList<Group> workList = new ArrayList<Group>();
		arrayGroups.load();
		
		
		for (int i = 0; i < arrayGroups.getLenght(); i++) {
			
			if ((arrayGroups.findGroup(i) != null) && (inBoundaries(arrayGroups.findGroup(i), today))) {
				if (teacher.getGroup(i) == arrayGroups.findGroup(i).getID()) {
					//workList.add(arrayGroups.findGroup(i));
					System.out.println("nasiel som skupinu s ID: " + arrayGroups.findGroup(i).getID());
					printGroup(arrayGroups.findGroup(i), area);
					counter++;
				}	
			}
			
			
		}
		
		if (counter == 0) {
			System.out.println("Nenasiel som skupinu na dany tyzden.");
		}	
		
		//workList = reorderArrayList(workList);
		
		//for (int i = 0; i < workList.size(); i++) {
		//	printGroup(workList.get(i), area);
			area.append("\n\n");
		//}

		
	}
	
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


