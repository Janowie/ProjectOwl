package src;

@SuppressWarnings("serial")
public class Student extends User implements java.io.Serializable {
	int groupNum;
	
	public Student(String firstName, String lastName, String emailAddress, int stGroup) {
		super(firstName, lastName, emailAddress);
		groupNum = stGroup;
	}
	
}
