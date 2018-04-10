package src.users;

import java.util.*;

public class Teacher extends User {
	
	List<Group> groupsTaught = new ArrayList<Group>();
	String IBAN;
	int money;

	public Teacher(String userFirstName, String userLastName, String userEmailAddres, String Iban) {
		super(userFirstName, userLastName, userEmailAddres);
		IBAN = Iban;
	}

}
