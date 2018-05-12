package src.users;

import java.io.Serializable;

// Základný typ používate¾a z ktorého sa rozvetvuje hierarchia

@SuppressWarnings("serial")
public abstract class User implements Serializable {

	public String username;
	private String password;
		
	public User(String userUsername, String newPassword) {
		username = userUsername;
		setPassword(newPassword);
	}
	
// Geter a seter na heslo používate¾a

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
