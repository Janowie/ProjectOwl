package src.gui;

import javax.swing.JOptionPane;

public class InvalidName extends Exception {

	public InvalidName(String s) {
		super(s);
		JOptionPane.showMessageDialog(null, "Invalid name.");
	}
	
	
}
