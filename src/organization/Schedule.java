package src.organization;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;

import src.users.Group;

// test 2
public class Schedule {
	ArrayList<Group> arrayGroups = new ArrayList<Group>();
	int index = 0;
	long difference = 0;
	
	//		NUMBER OF SAVED GROUPS		//
	private int numberOfGroups() throws IOException {
		try (Stream<Path> files = Files.list(Paths.get("saves"))) {
			long count = files.count();
		    index = (int) count; 
		    return (int) count;
		}		
	}
	
	//	BILD ARRAY FROM ALL SAVED GROUPS		//
	private void buildArrayGroups() {
		if (index > 0) {
			for (int i = 0; i < index; i++) {
				int ione = i + 1;
				try {
					FileInputStream fileIn = new FileInputStream("saves/group" + ione +".ser");
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
	
	
	private void printGroup(Group group) {
		difference = dayLeft(group);
		System.out.println("\nSkupina: " + group.getID() + "\nCas: " + group.time + "\nUcitel: " + group.groupArrayTeachers.get(0).firstName +  "\nMiestnost: " + group.room + "\nDen: " + group.day + "\nOstava: " + (difference/604800000+1));
	}
	
	//		PRINT SCHEDULE DAY		//
	public void printScheduleDay(String string) {
		int i = 0;
		int counter = 0;
		
		buildArrayGroups();
		
		while (i < index) {
			if (string.isEmpty()) {
				System.out.println("\n");
				printGroup(arrayGroups.get(i));
				counter++;
			}
			else if (arrayGroups.get(i).day.equals(string)) {
				System.out.println("\n");
				printGroup(arrayGroups.get(i));
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
	
	//	PRINT SCHEDULE WEEK		//
	public void printScheduleWeek() {
		int counter = 0;
		Date today = new Date();
		
		for (int i = 0; i < arrayGroups.size(); i++) {
			
			if (inBoundaries(arrayGroups.get(i), today)) {
				printGroup(arrayGroups.get(i));
				counter++;
			}
			if (counter == 0) {
				System.out.println("Nenasiel som skupinu na dany tyzden.");
			}	
		}
		
	}
	
	public void printScheduleNextWeek() {
		int counter = 0;
		Date today = new Date();
		
		for (int i = 0; i < arrayGroups.size(); i++) {
			if (inBoundaries(arrayGroups.get(i), nextWeek(today))) {
				printGroup(arrayGroups.get(i));
				counter++;
			}
			if (counter == 0) {
				System.out.println("Nenasiel som skupinu na dany tyzden.");
			}	
		}
		
	}
	
}


