package src.gui;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import src.users.Director;
import src.users.Group;
import src.users.OfficeWorker;
import src.users.Student;
import src.users.Teacher;

public class Login {
	
	ArrayList<Student> arrayStudents = new ArrayList<Student>();
	ArrayList<Teacher> arrayTeacher = new ArrayList<Teacher>();
	ArrayList<OfficeWorker> arrayOfficeWorkers = new ArrayList<OfficeWorker>();
	ArrayList<Director> arrayDirectors = new ArrayList<Director>();
	
	public int indexStudents = 0;
	public int indexTeachers = 0;
	public int indexOffice = 0;
	public int indexDirector = 0;
	
	int idStudent = 0;
	int idTeacher = 0;
	int idOffice = 0;
	int idDirector = 0;
	

	
	//		NUMBER OF SAVED STUDENTS		//
	private int numberOfStudents() throws IOException {
		try (Stream<Path> files = Files.list(Paths.get("saves/students"))) {
			long count = files.count();
		    return (int) count;
		}		
	}
	
	//		BILD ARRAY FROM ALL SAVED STUDENTS		//
	private void buildArrayStudents() {
		if (indexStudents > 0) {
			for (int i = 0; i < indexStudents; i++) {
				int ione = i + 1;
				try {
					FileInputStream fileIn = new FileInputStream("saves/students/student" + ione +".ser");
					ObjectInputStream in = new ObjectInputStream(fileIn);
					arrayStudents.add((Student) in.readObject());
					in.close();
					fileIn.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				catch (ClassNotFoundException c) {
					System.out.println("No class Student found");
					c.printStackTrace();
				}
			}
		}
		else {
			System.out.println("No students at all..");
		}
		
	}
	
	//		NUMBER OF SAVED TEACHERS		//
	private int numberOfTeachers() throws IOException {
		try (Stream<Path> files = Files.list(Paths.get("saves/employees/Teachers"))) {
			long count = files.count();
		    return (int) count;
		}		
	}
	
	//		BILD ARRAY FROM ALL SAVED TEACHERS		//
	private void buildArrayTeachers() {
		if (indexTeachers > 0) {
			for (int i = 0; i < indexTeachers; i++) {
				int ione = i + 1;
				try {
					FileInputStream fileIn = new FileInputStream("saves/employees/Teachers/teacher" + ione +".ser");
					ObjectInputStream in = new ObjectInputStream(fileIn);
					arrayTeacher.add((Teacher) in.readObject());
					in.close();
					fileIn.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				catch (ClassNotFoundException c) {
					System.out.println("No class Teacher found");
					c.printStackTrace();
				}
			}
		}
		else {
			System.out.println("No Teacher at all..");
		}
		
	}

	//		NUMBER OF SAVED OFFICE		//
	private int numberOfOfficeWorkers() throws IOException {
		try (Stream<Path> files = Files.list(Paths.get("saves/employees/Office"))) {
			long count = files.count();
		    return (int) count;
		}		
	}
	
	//		BILD ARRAY FROM ALL SAVED OFFICE		//
	private void buildArrayOfficeWorkers() {
	
	if (indexOffice > 0) {
		for (int i = 0; i < indexOffice; i++) {
			int ione = i + 1;
			try {
				FileInputStream fileIn = new FileInputStream("saves/employees/Office/office" + ione +".ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				arrayOfficeWorkers.add((OfficeWorker) in.readObject());
				in.close();
				fileIn.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException c) {
				System.out.println("No class OfficeW found");
				c.printStackTrace();
			}
		}
	}
	else {
		System.out.println("No OfficeW at all..");
	}
	
	}

	//		NUMBER OF SAVED DIRECTORS		//
	private int numberOfDirectors() throws IOException {
		try (Stream<Path> files = Files.list(Paths.get("saves/employees/Director"))) {
			long count = files.count(); 
		    return (int) count;
		}		
	}
	
	//		BILD ARRAY FROM ALL SAVED DIRECTORS		//
	private void buildArrayDirectors() {
	if (indexDirector > 0) {
		for (int i = 0; i < indexDirector; i++) {
			int ione = i + 1;
			try {
				FileInputStream fileIn = new FileInputStream("saves/employees/Director/director" + ione +".ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				arrayDirectors.add((Director) in.readObject());
				in.close();
				fileIn.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException c) {
				System.out.println("No class Director found");
				c.printStackTrace();
			}
		}
	}
	else {
		System.out.println("No Directors at all..");
	}
	
	}
	
	public Login() throws IOException {
		indexStudents = numberOfStudents();
		buildArrayStudents();
		indexTeachers = numberOfTeachers();
		buildArrayTeachers();
		indexOffice = numberOfOfficeWorkers();
		buildArrayOfficeWorkers();;
		indexDirector = numberOfDirectors();
		buildArrayDirectors();	
	}
	
	
	//		USERS CONTROL		//
	public int loginMethod(String username, String password) {
		int type = -1;
		int counter = 0;
		String arrayUsername;
		String arrayPassword;
		
		while (counter < 5) {
			if (type == -1) {
				counter++;
				for (int i = 0; i < arrayStudents.size(); i++) {
					arrayUsername = arrayStudents.get(i).username;
					arrayPassword = arrayStudents.get(i).getPassword();
					if ((arrayUsername.equals(username)) && (arrayPassword.equals(password))) {
						idStudent = i;
						return 1;
					}
					else type = -2;
				}
			}
			else if (type == -2) {
				counter++;
				for (int i = 0; i < arrayTeacher.size(); i++) {
					arrayUsername = arrayTeacher.get(i).username;
					arrayPassword = arrayTeacher.get(i).getPassword();
					if ((arrayUsername.equals(username)) && (arrayPassword.equals(password))) {
						idTeacher = i;
						return 2;
					}
					else type = -3;
				}
			}
			else if (type == -3) {
				counter++;
				for (int i = 0; i < arrayOfficeWorkers.size(); i++) {
					arrayUsername = arrayOfficeWorkers.get(i).username;
					arrayPassword = arrayOfficeWorkers.get(i).getPassword();
					if ((arrayUsername.equals(username)) && (arrayPassword.equals(password))) {
						idOffice = i;
						return 3;
					}
					else type = -4;
				}
			}
			else if (type == -4) {
				counter++;
				for (int i = 0; i < arrayDirectors.size(); i++) {
					arrayUsername = arrayDirectors.get(i).username;
					arrayPassword = arrayDirectors.get(i).getPassword();
					if ((arrayUsername.equals(username)) && (arrayPassword.equals(password))) {
						idDirector = i;
						return 4;
					}
					else System.out.println("No such user exists");
				}
			}
		}
		
		return type;
		
	}
	
	public Student returnStudent() {
		Student thisStudent = arrayStudents.get(idStudent);		
		return thisStudent;
	}
	public Teacher returnTeacher() {
		Teacher thisTeacher = arrayTeacher.get(idTeacher);		
		return thisTeacher;
	}
	public OfficeWorker returnOffice() {
		OfficeWorker thisOffice = arrayOfficeWorkers.get(idOffice);		
		return thisOffice;
	}
	public Director returnDirector() {
		Director thisDirector = arrayDirectors.get(idDirector);		
		return thisDirector;
	}

}
