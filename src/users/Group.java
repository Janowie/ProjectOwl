package users;
import java.util.*;

@SuppressWarnings("serial")
public class Group implements java.io.Serializable {
	
	
	int ID, size;
	public List<Student> groupArrayStudents = new ArrayList<Student>();
	public List<Teacher> groupArrayTeachers = new ArrayList<Teacher>();
	public Teacher groupTeacher;
	public String time;
	public String day;
	public String room;
	public int duration = 0;
	public Date beginning = new Date();
	public Date end = new Date();
	long difference = 0;
	
	public void printArray() {
		System.out.println("\n\nUcitelia: " + groupArrayTeachers.get(0).firstName + "\nCas: " + this.time + "\nMiestnost: " + this.room + "\nDen: " + this.day + "\nTravnie do : " + this.end.toString() + "\nZiaci: ");
		for (int i = 0; i < groupArrayStudents.size(); i++) {
			System.out.println(groupArrayStudents.get(i).firstName + " " + groupArrayStudents.get(i).groupNum);
		}	
	}

	public Group (int grID) {
		ID = grID;
	}

}
