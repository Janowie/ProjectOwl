package src.organization;

import java.awt.TextArea;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;

import javax.swing.JTextArea;

import src.users.Group;
import src.users.Teacher;

// test 2
public class Schedule {
	ArrayList<Group> arrayGroups = new ArrayList<Group>();
	int index = 0;
	long difference = 0;
	int numberOfGroups = 0;
	
	//		NUMBER OF SAVED GROUPS		//
	private int numberOfGroups() throws IOException {
		try (Stream<Path> files = Files.list(Paths.get("saves/groups"))) {
			long count = files.count();
		    return (int) count;
		}		
	}
	
	//	BILD ARRAY FROM ALL SAVED GROUPS		//
	private void buildArrayGroups() throws IOException {
		numberOfGroups = numberOfGroups();
		if (index > 0) {
			for (int i = 0; i < numberOfGroups; i++) {
				int ione = i + 1;
				try {
					FileInputStream fileIn = new FileInputStream("saves/groups/group" + ione +".ser");
					ObjectInputStream in = new ObjectInputStream(fileIn);
					arrayGroups.add((Group) in.readObject());
					in.close();
					fileIn.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				catch (ClassNotFoundException c) {
					System.out.println("Group class not found");
					c.printStackTrace();
				}
			}
		}
		else {
			System.out.println("No groups at all..");
		}
		
	}
	
	//		CONSTRUCTOR		//
	public Schedule() throws IOException {
		index = numberOfGroups();
		buildArrayGroups();
	}
	
	//		DAYS LEFT		//
	private long dayLeft(Group group) {
		Date today = new Date();
		return group.end.getTime() - today.getTime();		
	}
	
	
	private void printGroup(Group group, JTextArea area) {
		difference = dayLeft(group);
		area.append("\nSkupina: " + group.getID() + "\nCas: " + group.time + "\nUcitel: " + group.groupArrayTeachers.get(0).username +  "\nMiestnost: " + group.room + "\nDen: " + group.day + "\nOstava: " + (difference/604800000+1));
	}
	
	//		PRINT SCHEDULE DAY		//
	public void printScheduleDay(String string, JTextArea area) throws IOException {
		int i = 0;
		int counter = 0;
		
		buildArrayGroups();
		
		while (i < index) {
			if (string.isEmpty()) {
				System.out.println("\n");
				printGroup(arrayGroups.get(i), area);
				counter++;
			}
			else if (arrayGroups.get(i).day.equals(string)) {
				System.out.println("\n");
				printGroup(arrayGroups.get(i), area);
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
	
	private ArrayList<Group> reorderArrayList(ArrayList<Group> unordered) {
		ArrayList<Group> orderedList = new ArrayList<Group>();
		int index = 1;
		int listSize = unordered.size();
		
		while (listSize > 0) {
			for (int i = 0; i < unordered.size(); i++) {
				if (unordered.get(i).dayOfTheWeek == index) {
					orderedList.add(unordered.get(i));
					listSize--;
				}
			}
			index++;
		}		
		
		return orderedList;
	}
	
	//	PRINT SCHEDULE WEEK		//
	public void printScheduleWeek(JTextArea area) {
		int counter = 0;
		Date today = new Date();
		ArrayList<Group> workList = new ArrayList<Group>();
		
		for (int i = 0; i < arrayGroups.size(); i++) {
			
			if (inBoundaries(arrayGroups.get(i), today)) {
				workList.add(arrayGroups.get(i));
				counter++;
			}
			if (counter == 0) {
				System.out.println("Nenasiel som skupinu na dany tyzden.");
			}	
		}
		
		workList = reorderArrayList(workList);
		
		for (int i = 0; i < workList.size(); i++) {
			printGroup(workList.get(i), area);
		}

		
	}
	
	//	PRINT TEACHERS SCHEDULE WEEK		//
	public void printTeachersScheduleWeek(JTextArea area, Teacher teacher) {
		int counter = 0;
		Date today = new Date();
		ArrayList<Group> workList = new ArrayList<Group>();
		
		for (int i = 0; i < arrayGroups.size(); i++) {
			
			if (inBoundaries(arrayGroups.get(i), today)) {
				if (arrayGroups.get(i).groupArrayTeachers.get(0).username.equals(teacher.username)) {
					workList.add(arrayGroups.get(i));
					counter++;
				}	
			}
			
			if (counter == 0) {
				System.out.println("Nenasiel som skupinu na dany tyzden.");
			}	
		}
		
		workList = reorderArrayList(workList);
		
		for (int i = 0; i < workList.size(); i++) {
			printGroup(workList.get(i), area);
			area.append("\n\n");
		}

		
	}
	
	public void printScheduleNextWeek(JTextArea area) {
		int counter = 0;
		Date today = new Date();
		
		for (int i = 0; i < arrayGroups.size(); i++) {
			if (inBoundaries(arrayGroups.get(i), nextWeek(today))) {
				printGroup(arrayGroups.get(i), area);
				counter++;
			}
			if (counter == 0) {
				System.out.println("Nenasiel som skupinu na dany tyzden.");
			}	
		}
		
	}
	
}


