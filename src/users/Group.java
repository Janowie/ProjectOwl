package src.users;
import java.util.*;

import src.organization.Functions;


@SuppressWarnings("serial")
public class Group implements java.io.Serializable, Functions {
	
	
	int ID, size;
	public List<Student> groupArrayStudents = new ArrayList<Student>();
	public List<Teacher> groupArrayTeachers = new ArrayList<Teacher>();
	public String time;
	public String day;
	public int dayOfTheWeek;
	public String room;
	public int duration = 0;
	public Date beginning = new Date();
	public Date end = new Date();
	long difference = 0;
	
	
	
	public void printArray() {
		System.out.println("\n\nUcitel: " + groupArrayTeachers.get(1).firstName + "\nCas: " + this.time + "\nMiestnost: " + this.room + "\nDen: " + this.day + "\nTravnie do : " + this.end.toString() + "\nZiaci: ");
		for (int i = 0; i < groupArrayStudents.size(); i++) {
			System.out.println(groupArrayStudents.get(i).firstName + " " + groupArrayStudents.get(i).groupNum);
		}	
	}
	
	//		SET THE DAY OF WEEK		//
	//		to be executed by officeWorker
	int setDayOfWeek() {
		String[] strDays = new String[] {"Pondelok", "Utorok", "Streda", "Stvrtok",
		        "Piatok", "Sobota",  "Nedela" };
		int dayOfWeek = 0;
		for (int i = 0; i < strDays.length; i++) {
			if (this.day.equals(strDays[i])) {
				dayOfWeek = i + 1;
			}
			else 
				dayOfWeek = 0;
		}
		return dayOfWeek;
 
	}

	public Group (int grID) {
		ID = grID;
	}
	
	
	
	
	

}
