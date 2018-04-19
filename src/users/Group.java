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
	
	public int getID() {
		return this.ID;
	}
	
	public void printArray() {
		System.out.println("\n\nUcitel: " + groupArrayTeachers.get(1).firstName + "\nCas: " + this.time + "\nMiestnost: " + this.room + "\nDen: " + this.day + "\nTravnie do : " + this.end.toString() + "\nZiaci: ");
		for (int i = 0; i < groupArrayStudents.size(); i++) {
			System.out.println(groupArrayStudents.get(i).firstName + " " + groupArrayStudents.get(i).groupNum);
		}	
	}
	

	public Group (int grID) {
		ID = grID;
	}
	
	
	
	
	

}
