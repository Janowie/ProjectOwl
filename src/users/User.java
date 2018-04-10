package src.users;

//@SuppressWarnings("serial")

@SuppressWarnings("serial")
public abstract class User implements java.io.Serializable {

	String firstName;
	String lastName;
	String emailAddress;
		
	public User(String userFirstName, String userLastName, String userEmailAddres) {
		firstName = userFirstName;
		lastName = userLastName;
		emailAddress = userEmailAddres;
	}

}
