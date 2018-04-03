<<<<<<< HEAD
package src;

=======
>>>>>>> 7e9b9bd82263dbe262fb688b3528f2fd412dd8b2
@SuppressWarnings("serial")
public class Student extends User implements java.io.Serializable {
	int groupNum;
	
	public Student(String firstName, String lastName, String emailAddress, int stGroup) {
		super(firstName, lastName, emailAddress);
		groupNum = stGroup;
	}
	
}

