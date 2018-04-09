package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;


public class GUI {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(129, 272, 172, 29);
		btnLogin.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBackground(Color.WHITE);
		frame.getContentPane().add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Source Sans Pro", Font.PLAIN, 16));
		btnRegister.setBounds(129, 312, 172, 29);
		frame.getContentPane().add(btnRegister);
		
		textField = new JTextField();
		textField.setBounds(129, 183, 172, 29);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblMeno = new JLabel("User name");
		lblMeno.setBounds(59, 190, 60, 14);
		frame.getContentPane().add(lblMeno);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(129, 223, 172, 29);
		frame.getContentPane().add(passwordField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(59, 230, 46, 14);
		frame.getContentPane().add(lblPassword);
		
		JLabel label = new JLabel("");
		label.setBounds(143, 33, 121, 125);
		frame.getContentPane().add(label);
	}
}
