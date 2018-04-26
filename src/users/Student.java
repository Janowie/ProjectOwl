package src.users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Stream;

import javax.swing.JTextArea;

import java.util.Random;


@SuppressWarnings("serial")
public class Student extends User implements java.io.Serializable {
	ArrayList<Student> listPeople = new ArrayList<Student>();
	int groupNum;
	int index = 0;
	int numberOfPeople = 0;
	Group studentsGroup;
	int userID = 0;
	
	
	//	NUMBER OF SAVED STUDENTS		//
	private int numberOfStudents() throws IOException {
		try (Stream<Path> files = Files.list(Paths.get("saves/students"))) { // zmenit
			long count = files.count();
		    return (int) count;
		}		
	}
	
	
	//	BILD ARRAY FROM ALL SAVED STUDENTS		//
	private void buildArrayStudents() throws IOException {
		numberOfPeople = numberOfStudents();
		if (numberOfPeople > 0) {
			for (int i = 0; i < numberOfPeople; i++) {
				int ione = i + 1;
				try {
					FileInputStream fileIn = new FileInputStream("saves/students/student" + ione +".ser"); // zmenit
					ObjectInputStream in = new ObjectInputStream(fileIn);
					listPeople.add((Student) in.readObject());
					in.close();
					fileIn.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				catch (ClassNotFoundException c) {
					System.out.println("Group class not found");
					c.printStackTrace();
				}
			}
		}
		else {
			System.out.println("No groups at all..");
		}
		
		System.out.println("Moja velkost je " + listPeople.size());
		
	}
	
	public Student(String firstName, String newPassword,String emailAddress, int stGroup) throws IOException {
		super(firstName, newPassword, emailAddress);
		groupNum = stGroup;
		index = numberOfGroups();
		numberOfPeople = numberOfStudents();
		studentSave(this);		
	}
	
	private void setUserID() {
		this.userID = numberOfPeople + 1;
	}
	
	//		SAVE THIS		//
	private void studentSave(Student student) {
		setUserID();	
		numberOfPeople++;
		try {
			FileOutputStream fileOut = new FileOutputStream("saves/students/student" + student.userID + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);			
			out.writeObject(this);
			out.close();
			fileOut.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
		System.out.println("Saving student " + student.userID );
	}
	
	
	//		LOAD THIS		//
	public Student studentLoad(int userID) {
		Student loadedStudent;
		try {
			FileInputStream fileIn = new FileInputStream("saves/students/student" + userID +".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loadedStudent = (Student) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("Loaded successfully by " + this.username);
			return loadedStudent;
		}
	  catch (IOException i) {
	    i.printStackTrace();
	    return null;
	  }
	  catch (ClassNotFoundException c) {
	    System.out.println("Group class not found");
	    c.printStackTrace();
	    return null;
	 }
	}
	
	
	//		DELETE THIS		//
	/*
	public Student deleteStudent(Student student) throws IOException {
		buildArrayStudents();
		
		System.out.println("Velkost lispPeople pre student: " + listPeople.size());
		
		
		File file = new File("saves/students/student" + student.userID + ".ser");
		if(file.delete())
	    {
	        System.out.println("File deleted successfully");
	        numberOfPeople--;
	    }
	    else
	    {
	        System.out.println("Failed to delete the file");
	    }
		
		for (int i = 0; i < listPeople.size(); i++) {
			if ((i + 1) <= student.userID) {
				continue;
			}
			else {
				listPeople.get(i).userID = i;
				studentSave(listPeople.get(i));
			}
			
		}
		
		// Delete last student //
		file = new File("saves/students/student" + listPeople.get(listPeople.size()).userID + ".ser");
		if(file.delete())
	    {
	        System.out.println("File deleted successfully");
	    }
	    else
	    {
	        System.out.println("Failed to delete the file");
	    }
		
		student = null;
		
		return student;
	}
	*/
	
	
	//	NUMBER OF SAVED GROUPS		//
	private int numberOfGroups() throws IOException {
		try (Stream<Path> files = Files.list(Paths.get("saves/groups"))) {
			long count = files.count();
		    return (int) count;
		}		
	}
	
	//	BILD ARRAY FROM ALL SAVED GROUPS		//
	private Group setStudentsGroup() throws IOException {
		index = numberOfGroups();
		Group loadGroup = null;
		if (index > 0) {
			for (int i = 0; i < index; i++) {
				int ione = i + 1;
				if (this.groupNum == ione) {
					try {
						FileInputStream fileIn = new FileInputStream("saves/groups/group" + ione +".ser");
						ObjectInputStream in = new ObjectInputStream(fileIn);
						loadGroup = (Group) in.readObject();
						in.close();
						fileIn.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
					catch (ClassNotFoundException c) {
						System.out.println("'NO STUDENTS GROUP 1' Group class not found");
						c.printStackTrace();
					}
					return loadGroup;
				}
			}
		}
		else {
			System.out.println("'NO STUDENTS GROUP 1' No group..");
			return null;
		}
		return loadGroup;
		
	}
	
	public void printArray(JTextArea area) {
		area.append("\nCas: " + this.studentsGroup.time + "\nMiestnost: " + this.studentsGroup.room + "\nDen: " + 
				this.studentsGroup.day + "\nTravnie do : " + this.studentsGroup.end.toString());
		System.out.println("Snazim sa pisat do textarea..");
	}
	
	private boolean withinBorders(Date today) {
		boolean comp1 = studentsGroup.beginning.before(today);
		boolean comp2 = studentsGroup.beginning.equals(today);
		boolean comp3 = studentsGroup.end.after(today);
		boolean comp4 = studentsGroup.end.equals(today);
		
		if ((comp1 || comp2) && (comp3 || comp4)) {
			return true;
		}
		else return false;
	}
	
	public void printScheduleWeek(JTextArea area, Date today) throws IOException {
		
		studentsGroup = setStudentsGroup();
		System.out.println("this.studentsGroup.end " + studentsGroup.end);
		
		area.append("Group ends " + studentsGroup.end);
		
		if (withinBorders(today)) {
			printArray(area);
		}
		else
			area.append("Ziadna hodina sa na dany tyzden nenasla..");
	}
	
	
}

