package src.users;

import java.util.*;

import saving.SavingGroups;
import src.organization.Functions;

/**
 * Class Group nestoji v ziadnej hierarchii
 * @author Jan
 *
 */
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
	
	/**
	 * konstruktor pre Group, vytvorenu skupinu ulozi
	 * @param grID
	 * @throws ClassNotFoundException
	 */
	public Group (int grID) throws ClassNotFoundException {
		ID = grID;
		arrayGroups.saveGroup(this);
	}
		
	/**
	 * 
	 * @return ID skupiny
	 */
	public int getID() {
		return this.ID;
	}
	
	/**
	 * do skupiny prida ucitela - agregacia
	 * @param addedTeacher
	 */
	public void addTeacher(Teacher addedTeacher) {
		teacher = addedTeacher;
	}
		

}
