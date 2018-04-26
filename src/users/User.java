package src.users;

//@SuppressWarnings("serial")

@SuppressWarnings("serial")
public abstract class User implements java.io.Serializable {

	public String username;
	private String password;
	String emailAddress;
		
	public User(String userUsername, String newPassword, String userEmailAddres) {
		username = userUsername;
		setPassword(newPassword);
		emailAddress = userEmailAddres;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
