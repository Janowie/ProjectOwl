package src.users;

import java.io.Serializable;

//@SuppressWarnings("serial")

@SuppressWarnings("serial")
public abstract class User implements Serializable {

	public String username;
	private String password;
		
	public User(String userUsername, String newPassword) {
		username = userUsername;
		setPassword(newPassword);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
