package src.gui;

import javax.swing.JOptionPane;
/*
 * vlastna vinimka, hodena ak zadane meno uzivatela neobsahuje @
 */
public class InvalidName extends Exception {

	// konstruktor s prekonanou metodou Exception
	public InvalidName(String s) {
		super(s);
		JOptionPane.showMessageDialog(null, "Invalid name.");
	}
	
	
}
