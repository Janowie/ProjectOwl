
import java.util.*;

public class Teacher extends User {
	
	List<Group> groupsTaught = new ArrayList<Group>();

	public Teacher(String userFirstName, String userLastName, String userEmailAddres) {
		super(userFirstName, userLastName, userEmailAddres);
	}

}
