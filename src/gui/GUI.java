package src.gui;

import src.organization.Schedule;
import src.organization.SpecificTeacherObserver;
import src.users.Director;
import src.users.Group;
import src.users.OfficeWorker;
import src.users.Student;
import src.users.Teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import saving.SavingGroups;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.JTree;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

public class GUI extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	CardLayout cardLayout = new CardLayout(0, 0);
	public JTextField textFieldUsername;
	private JTextField password;
	private JTextField textFieldGroupID;
	private JTextField textFieldGroupID2;
	private JTextField textFieldUsernameOffice;
	private JTextField detailsTime;
	private JTextField detailDay;
	private JTextField detailDuration;
	private JTextField detailRoom;
	private JTextField detailsID;
	private JTextField addStudentUsername;
	private JTextField addStudentPassword;
	private JTextField addStudentGroup;
	private JTextField begDay;
	private JTextField directorUserName;
	private JTextField directorAddMoney;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//ThreadLogin loginThread = new ThreadLogin();
		//loginThread.run();
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public GUI() throws IOException, ClassNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 921, 737);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(cardLayout); // IMPORTANT
		JScrollPane scroll;
		
		// Users
		final Student[] student = {null};
		final Teacher[] teacher = {null};
		final OfficeWorker[] office = {null};
		final Director[] director = {null};
		
		// Other
		Date today = new Date();
		Schedule schedule = new Schedule();
		
		
		//		LOGIN PANEL		//
		JPanel login = new JPanel();
		contentPane.add(login, "login");
		login.setLayout(null);
		
			
			
			JButton btnLogin = new JButton("LOGIN");			
			
			btnLogin.setFont(new Font("Vineta BT", Font.PLAIN, 15));
			btnLogin.setBounds(382, 253, 116, 39);
			login.add(btnLogin);
			
			JLabel lblLogin = new JLabel("OWL");
			lblLogin.setBounds(342, 49, 196, 107);
			login.add(lblLogin);
			lblLogin.setFont(new Font("Vineta BT", Font.PLAIN, 50));
			
			JLabel lblPassword = new JLabel("password");
			lblPassword.setBounds(266, 221, 89, 16);
			login.add(lblPassword);
			
			JLabel lblUserName = new JLabel("username");
			lblUserName.setBounds(266, 186, 95, 16);
			login.add(lblUserName);
			
			textFieldUsername = new JTextField();
			textFieldUsername.setBounds(382, 183, 116, 22);
			login.add(textFieldUsername);
			textFieldUsername.setColumns(10);
			
			password = new JTextField();
			password.setText("heslo1");
			password.setBounds(382, 218, 116, 22);
			login.add(password);
			password.setColumns(10);
			
			
			
		//		STUDENT PANEL		//
			
		JPanel studentPanel = new JPanel();
		contentPane.add(studentPanel, "cardStudent");
		studentPanel.setLayout(null);
		

			JTextArea txtrSchedule = new JTextArea();
			txtrSchedule.setBounds(214, 135, 639, 471);
			studentPanel.add(txtrSchedule);
		
			JButton logoutButton = new JButton("LOGOUT");
			logoutButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtrSchedule.setText("");
					cardLayout.show(contentPane, "login");
				}
			});
			logoutButton.setBounds(12, 13, 122, 25);
			studentPanel.add(logoutButton);
			
			
			JLabel lblStudent = new JLabel("STUDENT");
			lblStudent.setFont(new Font("Vineta BT", Font.PLAIN, 50));
			lblStudent.setBounds(239, 39, 454, 60);
			studentPanel.add(lblStudent);
			
			JButton btnShowSchedule = new JButton("Show schedule");
			btnShowSchedule.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
					
						student[0].printScheduleWeek(txtrSchedule, today);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnShowSchedule.setBounds(29, 135, 149, 25);
			studentPanel.add(btnShowSchedule);
		
			
			
		//		TEACHER PANEL		//
		JPanel teacherPanel = new JPanel();
		CardLayout teacherCardLayout = new CardLayout(0,0);
		contentPane.add(teacherPanel, "cardTeacher");
		teacherPanel.setLayout(teacherCardLayout);
			//caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		JPanel teacherFrontPanel = new JPanel();
		teacherPanel.add(teacherFrontPanel, "teacherFrontPanel");
		teacherFrontPanel.setLayout(null);
		
		JButton btnAddStudent = new JButton("Add student");
		btnAddStudent.setBounds(68, 261, 143, 83);
		teacherFrontPanel.add(btnAddStudent);
		
		JButton btnClearSchedule = new JButton("Clear schedule");
		btnClearSchedule.setBounds(68, 223, 117, 25);
		teacherFrontPanel.add(btnClearSchedule);
		
		JButton btnShowScheduleWeek_1 = new JButton("Show schedule week");
		btnShowScheduleWeek_1.setBounds(68, 185, 153, 25);
		teacherFrontPanel.add(btnShowScheduleWeek_1);
		
		JLabel lblTeacher = new JLabel("TEACHER");
		lblTeacher.setBounds(233, 0, 385, 68);
		teacherFrontPanel.add(lblTeacher);
		lblTeacher.setFont(new Font("Vineta BT", Font.PLAIN, 50));
		
		JButton btnShowScheduleWeek = new JButton("Show my schedule");
		btnShowScheduleWeek.setBounds(68, 147, 139, 25);
		teacherFrontPanel.add(btnShowScheduleWeek);
		
			JButton btnBack = new JButton("LOGOUT");
			btnBack.setBounds(12, 13, 117, 25);
			teacherFrontPanel.add(btnBack);
			
				JTextArea textArea = new JTextArea();
				textArea.setBounds(245, 113, 585, 540);
				teacherFrontPanel.add(textArea);
				
				JPanel teacherAddStudentPanel = new JPanel();
				teacherPanel.add(teacherAddStudentPanel, "teacherAddStudentPanel");
				teacherAddStudentPanel.setLayout(null);
				
				JLabel lblAddingStudent = new JLabel("ADDING STUDENT");
				lblAddingStudent.setFont(new Font("Vineta BT", Font.PLAIN, 50));
				lblAddingStudent.setBounds(66, 23, 763, 87);
				teacherAddStudentPanel.add(lblAddingStudent);
				
				addStudentUsername = new JTextField();
				addStudentUsername.setBounds(325, 181, 290, 49);
				teacherAddStudentPanel.add(addStudentUsername);
				addStudentUsername.setColumns(10);
				
				addStudentPassword = new JTextField();
				addStudentPassword.setBounds(325, 243, 290, 49);
				teacherAddStudentPanel.add(addStudentPassword);
				addStudentPassword.setColumns(10);
				
				JLabel lblUsername = new JLabel("username");
				lblUsername.setBounds(218, 197, 95, 16);
				teacherAddStudentPanel.add(lblUsername);
				
				JLabel lblPassword_1 = new JLabel("password");
				lblPassword_1.setBounds(218, 259, 95, 16);
				teacherAddStudentPanel.add(lblPassword_1);
				
				addStudentGroup = new JTextField();
				addStudentGroup.setBounds(325, 305, 290, 49);
				teacherAddStudentPanel.add(addStudentGroup);
				addStudentGroup.setColumns(10);
				
				JLabel lblGroupId = new JLabel("Group ID");
				lblGroupId.setBounds(218, 321, 95, 16);
				teacherAddStudentPanel.add(lblGroupId);
				
				JButton btnSave_1 = new JButton("SAVE");
				btnSave_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (addStudentUsername.getText().indexOf("@") == -1) {
							try {
								throw new InvalidName("Not valid name");
							} catch (InvalidName e) {
								e.printStackTrace();
							}  
						}
						else {
							try {
								teacher[0].addStudent(addStudentUsername.getText(), addStudentPassword.getText(), Integer.parseInt(addStudentGroup.getText()));
								teacherCardLayout.show(teacherPanel, "teacherFrontPanel");
							} catch (NumberFormatException | ClassNotFoundException e) {
								e.printStackTrace();
							}
						}
					}
				});
				btnSave_1.setBounds(0, 457, 893, 225);
				teacherAddStudentPanel.add(btnSave_1);
				
				JButton btnBack_2 = new JButton("Back");
				btnBack_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						teacherCardLayout.show(teacherPanel, "teacherFrontPanel");
					}
				});
				btnBack_2.setBounds(12, 13, 97, 25);
				teacherAddStudentPanel.add(btnBack_2);
				
				DefaultCaret caret = (DefaultCaret)textArea.getCaret();
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textArea.setText("");
					cardLayout.show(contentPane, "login");
				}
			});
		btnShowScheduleWeek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					schedule.printTeachersScheduleWeek(textArea, teacher[0]);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnShowScheduleWeek_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					schedule.printScheduleWeek(textArea);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnClearSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("nieco tu idem robit");
				teacherCardLayout.show(teacherPanel, "teacherAddStudentPanel");
			}
		});
		
			
		
		//		OFFICE PANEL		//
		JPanel officePanel = new JPanel();
		CardLayout officeCardLayout = new CardLayout(0, 0); 
		contentPane.add(officePanel, "cardOffice");
		officePanel.setLayout(officeCardLayout);
		
			JPanel officePanelFront = new JPanel();
			officePanel.add(officePanelFront, "cardOfficeFront");
			officePanelFront.setLayout(null);
			
				JTextArea officeTArea = new JTextArea();
				officeTArea.setBounds(108, 399, 355, 174);
				officePanelFront.add(officeTArea);
			
				JButton btnBack_1 = new JButton("LOGOUT");
				btnBack_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cardLayout.show(contentPane, "login");
						officeTArea.setText("");
					}
				});
				btnBack_1.setBounds(12, 13, 119, 25);
				officePanelFront.add(btnBack_1);
				
				textFieldGroupID = new JTextField();
				textFieldGroupID.setBounds(310, 191, 483, 32);
				officePanelFront.add(textFieldGroupID);
				textFieldGroupID.setColumns(10);
				
				JLabel lblGroupid = new JLabel("GroupID:");
				lblGroupid.setBounds(511, 157, 56, 16);
				officePanelFront.add(lblGroupid);
				
				textFieldGroupID2 = new JTextField();
				textFieldGroupID2.setBounds(310, 354, 241, 32);
				officePanelFront.add(textFieldGroupID2);
				textFieldGroupID2.setColumns(10);
				
				JButton btnNewButton_1 = new JButton("Add student to group");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							office[0].addStudent(textFieldUsernameOffice.getText(), Integer.parseInt(textFieldGroupID2.getText()));
							officeTArea.append("Student added to group.");
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				});
				btnNewButton_1.setBounds(475, 399, 153, 81);
				officePanelFront.add(btnNewButton_1);
				
				
				JButton btnAddTeacherTo = new JButton("Add teacher to group");
				btnAddTeacherTo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							office[0].setTeacher(Integer.parseInt(textFieldGroupID2.getText()), textFieldUsernameOffice.getText());
							officeTArea.append("Teacher added to group.");
						} catch (NumberFormatException | IOException e1) {
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnAddTeacherTo.setBounds(475, 493, 153, 81);
				officePanelFront.add(btnAddTeacherTo);
				
				JButton btnDeleteGroup = new JButton("Delete group");
				btnDeleteGroup.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int groupID = Integer.parseInt(textFieldGroupID.getText());
						office[0].arrayGroups.deleteGroup(office[0].arrayGroups.findGroup(groupID));
					}
				});
				btnDeleteGroup.setBounds(310, 234, 153, 82);
				officePanelFront.add(btnDeleteGroup);
				
				JButton btnCreateGroup = new JButton("Create group");
				btnCreateGroup.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Group newGroup = null;
						try {
							newGroup = new Group(Integer.parseInt(textFieldGroupID.getText()));
							officeTArea.append("Group created.");
						} 							
						catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				});
				btnCreateGroup.setBounds(108, 191, 190, 195);
				officePanelFront.add(btnCreateGroup);
				
				JButton btnDeleteGroupTeacher = new JButton("Delete group teacher");
				btnDeleteGroupTeacher.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							office[0].removeTeacher(Integer.parseInt(textFieldGroupID2.getText()), textFieldUsernameOffice.getText());
						} catch (NumberFormatException | IOException e1) {
							e1.printStackTrace();
						}
					}
				});
				btnDeleteGroupTeacher.setBounds(640, 493, 153, 81);
				officePanelFront.add(btnDeleteGroupTeacher);
				
				JButton btnDeleteGroupStudent = new JButton("Delete group student");
				btnDeleteGroupStudent.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							office[0].removeStudent(textFieldUsernameOffice.getText(), Integer.parseInt(textFieldGroupID2.getText()));
							officeTArea.append("Student deleted.");
							
						} catch (NumberFormatException e1) {
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnDeleteGroupStudent.setBounds(640, 399, 153, 81);
				officePanelFront.add(btnDeleteGroupStudent);
				
				JButton btnAddGroupDetails = new JButton("Add group details");
				btnAddGroupDetails.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						officeCardLayout.show(officePanel, "groupAddDetails");
					}
				});
				btnAddGroupDetails.setBounds(475, 234, 153, 82);
				officePanelFront.add(btnAddGroupDetails);
				
				JButton btnSetGroupEnd = new JButton("Set group end");
				btnSetGroupEnd.setBounds(640, 234, 153, 82);
				officePanelFront.add(btnSetGroupEnd);
				
				JLabel lblSystemMaster = new JLabel("SYSTEM MASTER");
				lblSystemMaster.setFont(new Font("Vineta BT", Font.PLAIN, 50));
				lblSystemMaster.setBounds(108, 57, 715, 87);
				officePanelFront.add(lblSystemMaster);
				
				JLabel lblGroupid_1 = new JLabel("GroupID:");
				lblGroupid_1.setBounds(407, 329, 56, 16);
				officePanelFront.add(lblGroupid_1);
				
				JLabel lblStudentUsername = new JLabel("Username:");
				lblStudentUsername.setBounds(650, 329, 81, 16);
				officePanelFront.add(lblStudentUsername);
				
				textFieldUsernameOffice = new JTextField();
				textFieldUsernameOffice.setBounds(563, 354, 229, 32);
				officePanelFront.add(textFieldUsernameOffice);
				textFieldUsernameOffice.setColumns(10);
				
				JButton btngroups = new JButton("#groups");
				btngroups.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						officeTArea.setText("");
						office[0].numberOfGroups(officeTArea);
					}
				});
				btngroups.setBounds(-1, 221, 97, 25);
				officePanelFront.add(btngroups);
				
				JButton btnstudents = new JButton("#students");
				btnstudents.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						officeTArea.setText("");
						office[0].numberOfStudents(officeTArea);
					}
				});
				btnstudents.setBounds(-1, 191, 97, 25);
				officePanelFront.add(btnstudents);

			
			JPanel groupAddDetails = new JPanel();
			officePanel.add(groupAddDetails, "groupAddDetails");
			groupAddDetails.setLayout(null);
			
			detailsTime = new JTextField();
			detailsTime.setBounds(284, 126, 360, 62);
			groupAddDetails.add(detailsTime);
			detailsTime.setColumns(10);
			
			detailDay = new JTextField();
			detailDay.setBounds(284, 201, 360, 62);
			groupAddDetails.add(detailDay);
			detailDay.setColumns(10);
			
			detailDuration = new JTextField();
			detailDuration.setBounds(284, 341, 360, 62);
			groupAddDetails.add(detailDuration);
			detailDuration.setColumns(10);
			
			JLabel lblTime = new JLabel("TIME");
			lblTime.setBounds(191, 149, 56, 16);
			groupAddDetails.add(lblTime);
			
			JLabel lblDay = new JLabel("Day");
			lblDay.setBounds(191, 224, 56, 16);
			groupAddDetails.add(lblDay);
			
			JLabel lblNewLabel = new JLabel("Duration");
			lblNewLabel.setBounds(191, 364, 56, 16);
			groupAddDetails.add(lblNewLabel);
			
			detailRoom = new JTextField();
			detailRoom.setBounds(284, 416, 360, 62);
			groupAddDetails.add(detailRoom);
			detailRoom.setColumns(10);
			
			JLabel lblRoom = new JLabel("Room");
			lblRoom.setBounds(191, 439, 56, 16);
			groupAddDetails.add(lblRoom);
			
			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						office[0].addDetails(Integer.parseInt(detailsID.getText()), detailsTime.getText(), detailDay.getText(), begDay.getText(),  Integer.parseInt(detailDuration.getText()), detailRoom.getText());
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Group " + detailsID.getText() + " saved.");
					officeCardLayout.show(officePanel, "cardOfficeFront");
				}
			});
			btnSave.setBounds(284, 557, 360, 62);
			groupAddDetails.add(btnSave);
			
			detailsID = new JTextField();
			detailsID.setBounds(284, 56, 360, 57);
			groupAddDetails.add(detailsID);
			detailsID.setColumns(10);
			
			JLabel lblGroupid_2 = new JLabel("GroupID");
			lblGroupid_2.setBounds(191, 76, 56, 16);
			groupAddDetails.add(lblGroupid_2);
			
			begDay = new JTextField();
			begDay.setBounds(284, 269, 360, 59);
			groupAddDetails.add(begDay);
			begDay.setColumns(10);
			
			JLabel lblBegginingDate = new JLabel("Beggining date");
			lblBegginingDate.setBounds(136, 290, 135, 16);
			groupAddDetails.add(lblBegginingDate);
		
		JPanel directorPanel = new JPanel();
		contentPane.add(directorPanel, "cardDirector");
		directorPanel.setLayout(null);
		
			JTextArea directorArea = new JTextArea();
			directorArea.setBounds(163, 19, 558, 138);
			directorPanel.add(directorArea);
		
			JButton btnLogout = new JButton("LOGOUT");
			btnLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cardLayout.show(contentPane, "login");
					directorArea.setText("");
				}
			});
			btnLogout.setBounds(12, 13, 97, 25);
			directorPanel.add(btnLogout);
			
			JButton btnShowTeachers = new JButton("show teachers");
			btnShowTeachers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						director[0].printTeachers(directorArea);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			});
			btnShowTeachers.setBounds(163, 207, 198, 25);
			directorPanel.add(btnShowTeachers);
			
			JButton btnShowOfficeWorkers = new JButton("show office workers");
			btnShowOfficeWorkers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						director[0].printOffice(directorArea);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnShowOfficeWorkers.setBounds(163, 245, 198, 25);
			directorPanel.add(btnShowOfficeWorkers);
			
			JButton btnShowStudents = new JButton("show students");
			btnShowStudents.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						director[0].printStudents(directorArea);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnShowStudents.setBounds(163, 283, 198, 25);
			directorPanel.add(btnShowStudents);
			
			JButton btnAddsalaryteacher = new JButton("addSalaryTeacher");
			btnAddsalaryteacher.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						director[0].updateSalaryTeacher(directorUserName.getText(), Double.parseDouble(directorAddMoney.getText()), directorArea);
					} catch (NumberFormatException | ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnAddsalaryteacher.setBounds(383, 207, 338, 25);
			directorPanel.add(btnAddsalaryteacher);
			
			JButton btnAddsalaryoffice = new JButton("addSalaryOffice");
			btnAddsalaryoffice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						director[0].updateSalaryOffice(directorUserName.getText(), Double.parseDouble(directorAddMoney.getText()), directorArea);
					} catch (NumberFormatException | ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnAddsalaryoffice.setBounds(393, 245, 328, 25);
			directorPanel.add(btnAddsalaryoffice);
			
			directorUserName = new JTextField();
			directorUserName.setBounds(742, 208, 116, 22);
			directorPanel.add(directorUserName);
			directorUserName.setColumns(10);
			
			JLabel lblName = new JLabel("name");
			lblName.setBounds(779, 179, 56, 16);
			directorPanel.add(lblName);
			
			directorAddMoney = new JTextField();
			directorAddMoney.setBounds(742, 272, 116, 22);
			directorPanel.add(directorAddMoney);
			directorAddMoney.setColumns(10);
			
			JLabel lblMoney = new JLabel("Money");
			lblMoney.setBounds(779, 249, 56, 16);
			directorPanel.add(lblMoney);
			
			
			//		ACTION LISTENERS		//
			btnLogin.addActionListener(new ActionListener() {
				Login login;
				public void actionPerformed(ActionEvent arg0) {
				
					
					try {
						login = new Login();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
										
					if (textFieldUsername.getText().indexOf("@") == -1) {
						try {
							throw new InvalidName("Not valid name");
						} catch (InvalidName e) {
							e.printStackTrace();
						}  
					}
					else {
						int type = login.loginMethod(textFieldUsername.getText(), password.getText());
						if (type == 1) {
							cardLayout.show(contentPane, "cardStudent");
							student[0] = login.returnStudent();
							System.out.println("Opening student");
						}
						else if (type == 2) {
							cardLayout.show(contentPane, "cardTeacher");
							teacher[0] = login.returnTeacher();
							System.out.println("Opening teacher");
						}
						else if (type == 3) {
							cardLayout.show(contentPane, "cardOffice");
							office[0] = login.returnOffice();
							System.out.println("Opening office");
						}
						else if (type == 4) {
							cardLayout.show(contentPane, "cardDirector");
							director[0] = login.returnDirector();
							System.out.println("Opening director");
						}
					}
					
					textFieldUsername.setText("");
					password.setText("");
										
				}
				
				
			});

			
	}
}
