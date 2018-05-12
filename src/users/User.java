package src.users;

import java.io.Serializable;

// Z�kladn� typ pou��vate�a z ktor�ho sa rozvetvuje hierarchia

@SuppressWarnings("serial")
public abstract class User implements Serializable {

	public String username;
	private String password;
		
	public User(String userUsername, String newPassword) {
		username = userUsername;
		setPassword(newPassword);
	}
	
// Geter a seter na heslo pou��vate�a

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
