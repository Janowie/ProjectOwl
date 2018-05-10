package src.users;

import java.util.*;

import saving.SavingGroups;
import src.organization.Functions;


@SuppressWarnings("serial")
public class Group implements java.io.Serializable, Functions {
	
	SavingGroups arrayGroups = new SavingGroups();	
	
	int ID, size;
	public String time;
	public String day;
	public int dayOfTheWeek;
	public String room;
	public int duration = 0;
	public Date beginning = new Date();
	public Date end = new Date();
	long difference = 0;
	
	Teacher teacher;
	
	public Group (int grID) throws ClassNotFoundException {
		ID = grID;
		arrayGroups.saveGroup(this);
	}
	
	public int getID() {
		return this.ID;
	}
	
	public void addTeacher(Teacher addedTeacher) {
		teacher = addedTeacher;
	}
		

}
