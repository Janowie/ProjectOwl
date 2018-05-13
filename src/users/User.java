package src.users;

import java.io.Serializable;

/**
 * Základný typ používate¾a z ktorého sa rozvetvuje hierarchia
 * @author Jan
 *
 */

@SuppressWarnings("serial")
public abstract class User implements Serializable {

	public String username;
	private String password;
		
	public User(String userUsername, String newPassword) {
		username = userUsername;
		setPassword(newPassword);
	}

	/**
	 * 
	 * @return heslo
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Nastav heslo usera
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
