import java.util.*;

@SuppressWarnings("serial")
public class Group implements java.io.Serializable {
	
	
	int ID, size;
	List<Student> groupArray = new ArrayList<Student>();
	String time, day, room;
	
	public void addStudentToGroup(Student student) {
		groupArray.add(student);
		student.groupNum = ID;
	}
	
	public void printArray() {
		for (int i = 0; i < groupArray.size(); i++) {
			System.out.println(groupArray.get(i).name + " " + groupArray.get(i).groupNum);
		}	
		System.out.println("Cas: " + this.time + "\nMiestnost: " + this.room + "\nDen: " + this.day);
	}

	public Group (int grID) {
		ID = grID;
	}

}
