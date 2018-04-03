import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Toolkit;
import javax.swing.*;

import java.awt.event.*;


public class GUI extends JFrame{
	
	JButton button1;
	JTextField textField1;
	JTextArea textArea1;
	int buttonClicked;
	
	public static void main(String[] args) {
		new GUI();
	}

	public GUI() {
		this.setSize(400,400);
		//this.setLocationRelativeTo(null);
		
		// centering
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();		
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		
		// other stuff
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // zatvor ked odchadzas
		this.setResizable(false);		
		this.setTitle("TEST GUI"); // nazov Framu
		
		
		
		JPanel thePanel = new JPanel();
		button1 = new JButton("Click");
		textField1 = new JTextField("", 15);
		
		ListenForButton listenerB1 = new ListenForButton();
		ListenForKeys listenerK1 = new ListenForKeys();
		
		button1.addActionListener(listenerB1);
		textField1.addKeyListener(listenerK1);
		
		thePanel.add(button1);
		this.setVisible(true);
		thePanel.add(textField1);
		this.setVisible(true);
		
		
		
		
		
		
	}
	
	
	private class ListenForButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == button1) {
				buttonClicked++;
				textArea1.append("BTN1 clicke: " + buttonClicked + "\n");
			}
		}
	}
	
	private class ListenForKeys implements KeyListener {

		
		public void keyPressed(KeyEvent e) {
			textArea1.append("Key hit: " + e.getKeyChar() + "\n");
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
