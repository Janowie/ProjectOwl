package src.gui;

import saving.SavingDirector;
import saving.SavingOfficeWorkers;
import saving.SavingStudents;
import saving.SavingTeachers;
import src.users.Director;
import src.users.OfficeWorker;
import src.users.Student;
import src.users.Teacher;

public class Login {
	
	SavingStudents arrayStudents;
	SavingTeachers arrayTeachers;
	SavingOfficeWorkers arrayOffice;
	SavingDirector arrayDirector;
	
	int ID;
	
	public Login() throws ClassNotFoundException {
		arrayStudents = new SavingStudents();
		arrayTeachers = new SavingTeachers();
		arrayOffice = new SavingOfficeWorkers();
		arrayDirector = new SavingDirector();
	}
	
	//////// hierarchia
	///////  
	
	//		USERS CONTROL		//
	public int loginMethod(String username, String password) {
		int type = -1;
		int counter = 0;
		String arrayUsername;
		String arrayPassword;
		
		while (counter < 5) {
			if (type == -1) {
				System.out.println("login hlada student");
				counter++;
				for (int i = 0; i < arrayStudents.getLenght(); i++) {
					arrayUsername = arrayStudents.getStudent(i).username;
					arrayPassword = arrayStudents.getStudent(i).getPassword();
					if ((arrayUsername.equals(username)) && (arrayPassword.equals(password))) {
						ID = i;
						return 1;
					}
					else type = -2;
				}
			}
			else if (type == -2) {
				System.out.println("login hlada ucitel");
				counter++;
				for (int i = 0; i < arrayTeachers.getLength(); i++) {
					arrayUsername = arrayTeachers.getTeacher(i).username;
					arrayPassword = arrayTeachers.getTeacher(i).getPassword();
					if ((arrayUsername.equals(username)) && (arrayPassword.equals(password))) {
						ID = i;
						return 2;
					}
					else type = -3;
				}
			}
			else if (type == -3) {
				System.out.println("login hlada office");
				counter++;
				for (int i = 0; i < arrayOffice.getLenght(); i++) {
					arrayUsername = arrayOffice.getOffice(i).username;
					arrayPassword = arrayOffice.getOffice(i).getPassword();
					if ((arrayUsername.equals(username)) && (arrayPassword.equals(password))) {
						ID = i;
						if (arrayOffice.getOffice(i).getUserID() == 3) {
							return 3;
						}
						else if (arrayOffice.getOffice(i).getUserID() == 4) {
							type = -4;
						}
						
					}
					else type = -4;
				}
			}
			else if (type == -4) {
				System.out.println("login hlada director");
				counter++;
				for (int i = 0; i < arrayDirector.getLenght(); i++) {
					arrayUsername = arrayDirector.getDirector(i).username;
					arrayPassword = arrayDirector.getDirector(i).getPassword();
					if ((arrayUsername.equals(username)) && (arrayPassword.equals(password))) {
						ID = i;
						return 4;
					}
					else System.out.println("No such user exists");
				}
			}
		}
		
		return type;
		
	}
	
	public Student returnStudent() {	
		return arrayStudents.getStudent(ID);
	}
	public Teacher returnTeacher() {
		return arrayTeachers.getTeacher(ID);
	}
	public OfficeWorker returnOffice() {
		return arrayOffice.getOffice(ID);		
	}
	public Director returnDirector() {
		return arrayDirector.getDirector(ID);
	}

}
