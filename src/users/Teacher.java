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
import java.util.*;
import java.util.stream.Stream;

import src.organization.Observer;

@SuppressWarnings("serial")
public class Teacher extends User {
	
	List<Group> groupsTaught = new ArrayList<Group>();
	private List<Observer> observers = new ArrayList<Observer>();
	ArrayList<Teacher> listPeople = new ArrayList<Teacher>();
	int index = 0;
	int numberOfPeople = 0;
	
	double salary = 0;
	String IBAN;
	private int userCode = 2;
	int userID = 0;

	public Teacher(String userFirstName, String userPassword, String userEmailAddres, String Iban) throws IOException {
		super(userFirstName, userPassword, userEmailAddres);
		IBAN = Iban;
		numberOfPeople = numberOfPeople();
		teacherSave();
	}

	public void setUserCode(int newCode) {
		this.userCode = newCode;
	}
	
	public double getSalary() {
	    return salary;
	}	

	public void attach(Observer observer){
	    observers.add(observer);		
	}

	public void notifyAllObservers(){
	    for (Observer observer : observers) {
	       observer.update();
	    }
	}
	
	
//	NUMBER OF SAVED TEACHERS		//
	private int numberOfPeople() throws IOException {
		try (Stream<Path> files = Files.list(Paths.get("saves/employees/Teachers"))) { // zmenit
			long count = files.count();
		    return (int) count;
		}		
	}
	
	//	BILD ARRAY FROM ALL SAVED TEACHERS		//
	private void buildArrayPeople() {
		if (index > 0) {
			for (int i = 0; i < index; i++) {
				int ione = i + 1;
				try {
					FileInputStream fileIn = new FileInputStream("saves/employees/Teachers/teacher" + ione +".ser"); // zmenit
					ObjectInputStream in = new ObjectInputStream(fileIn);
					listPeople.add((Teacher) in.readObject());
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
		
	}
	
	private void setUserID() {
		this.userID = numberOfPeople + 1;
	}
	
	//		SAVE THIS		//
	private void teacherSave() {
		setUserID();	
		numberOfPeople++;
		try {
			FileOutputStream fileOut = new FileOutputStream("saves/employees/Teachers/teacher" + this.userID + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);			
			out.writeObject(this);
			out.close();
			fileOut.close();
			System.out.println("Teacher " + this.userID + " saved, number of people: " + numberOfPeople);
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	
	//		LOAD THIS		//
	public Teacher personLoad(int userID) {
		Teacher loadedPerson;
		try {
			FileInputStream fileIn = new FileInputStream("saves/employees/Teachers/teacher" + userID +".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loadedPerson = (Teacher) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("Loaded successfully by " + this.username);
			return loadedPerson;
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
	public Teacher deletePerson(Teacher person) {
		buildArrayPeople();
		
		File file = new File("saves/employees/Teachers/teacher" + person.userID + ".ser");
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
			if ((i + 1) <= person.userID) {
				continue;
			}
			else {
				listPeople.get(i).userID = i;
			}
		}
		
		person = null;
		
		return person;
	}
	
	public void addStudent(String username, String password, String emailAdress, int IDgroup) throws IOException {
		@SuppressWarnings("unused")
		Student newStudent = new Student(username,password,emailAdress, IDgroup);
	}
	
	
	

}
