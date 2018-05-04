package src.users;

import java.util.*;

import saving.SavingGroups;
import src.organization.Functions;


@SuppressWarnings("serial")
public class Group implements java.io.Serializable, Functions {
	
	SavingGroups arrayGroups = new SavingGroups();	
	ArrayList<Student> groupArrayStudents = new ArrayList<Student>();
	private ArrayList<Teacher> groupArrayTeachers = new ArrayList<Teacher>();
	
	int ID, size;
	public String time;
	public String day;
	public int dayOfTheWeek;
	public String room;
	public int duration = 0;
	public Date beginning = new Date();
	public Date end = new Date();
	long difference = 0;
	
	public Group (int grID) throws ClassNotFoundException {
		ID = grID;
		
		arrayGroups.saveGroup(this);
	}
	
	public int getID() {
		return this.ID;
	}

	public ArrayList<Teacher> getGroupArrayTeachers() {
		return groupArrayTeachers;
	}

	public void setGroupArrayTeachers(ArrayList<Teacher> groupArrayTeachers) {
		this.groupArrayTeachers = groupArrayTeachers;
	}

			

}
