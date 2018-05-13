package src.gui;

import javax.swing.JOptionPane;

/**
 * vlastna vinimka, hodena ak zadane meno uzivatela neobsahuje @
 * @author Jan
 *
 */
public class InvalidName extends Exception {

	/**
	 * konstruktor s prekonanou metodou Exception
	 * @param s
	 */
	public InvalidName(String s) {
		super(s);
		JOptionPane.showMessageDialog(null, "Invalid name.");
	}
	
	
}
