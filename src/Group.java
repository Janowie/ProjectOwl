package src;

import java.util.*;

@SuppressWarnings("serial")
public class Group implements java.io.Serializable {
	
	
	int ID, size;
	List<Student> groupArray = new ArrayList<Student>();
	String teacherName;
	String time, day, room;
	int duration = 0;
	Date beginning = new Date();
	Date end = new Date();
	long difference = 0;
	
	public void addStudentToGroup(Student student) {
		groupArray.add(student);
		student.groupNum = ID;
	}
		
	
	public void printArray() {
		for (int i = 0; i < groupArray.size(); i++) {
			System.out.println(groupArray.get(i).firstName + " " + groupArray.get(i).groupNum);
		}	
		System.out.println("Cas: " + this.time + "\nMiestnost: " + this.room + "\nDen: " + this.day + "\nTravnie do : " + this.end.toString());
	}

	public Group (int grID) {
		ID = grID;
		}

}
