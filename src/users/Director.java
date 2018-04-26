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
import java.util.Random;
import java.util.stream.Stream;

@SuppressWarnings("serial")
public class Director extends OfficeWorker {
	ArrayList<Director> listPeople = new ArrayList<Director>();
	int userID = 0;
	int numberOfPeople = 0;
	int index = 0;
	
	public Director(String userFirstName, String newPassword, String userEmailAddres) throws IOException {
		super(userFirstName, newPassword, userEmailAddres);
		numberOfPeople = numberOfPeople();
		directorSave();
	}
	
	public void updateSalary(Teacher teacher, double addedSalary) {
		teacher.salary = teacher.getSalary() + addedSalary;
	    System.out.println("Salary changed to " + String.valueOf(teacher.getSalary()));
	    teacher.notifyAllObservers();
	}
	
	
	//	NUMBER OF SAVED PEOPLE		//
	private int numberOfPeople() throws IOException {
		try (Stream<Path> files = Files.list(Paths.get("saves/employees/Director"))) { // zmenit
			long count = files.count();
		    return (int) count;
		}		
	}
	
	//	BILD ARRAY FROM ALL SAVED PEOPLE		//
	private void buildArrayPeople() {
		if (index > 0) {
			for (int i = 0; i < index; i++) {
				int ione = i + 1;
				try {
					FileInputStream fileIn = new FileInputStream("saves/employees/Director/director" + ione +".ser"); // zmenit
					ObjectInputStream in = new ObjectInputStream(fileIn);
					listPeople.add((Director) in.readObject());
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
	private void directorSave() {
		setUserID();	
		numberOfPeople++;
		try {
			FileOutputStream fileOut = new FileOutputStream("saves/employees/Director/director" + this.userID + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);			
			out.writeObject(this);
			out.close();
			fileOut.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	
	//		LOAD THIS		//
	public Director personLoad(int userID) {
		Director loadedPerson;
		try {
			FileInputStream fileIn = new FileInputStream("saves/employees/Director/director" + userID +".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loadedPerson = (Director) in.readObject();
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
	    System.out.println("Director class not found");
	    c.printStackTrace();
	    return null;
	 }
	}
	
	
	//		DELETE THIS		//
	public Director deletePerson(Director person) {
		buildArrayPeople();
		
		File file = new File("saves/employees/Director/director" + person.userID + ".ser");
		if(file.delete())
	    {
	        System.out.println("File deleted successfully");
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
}
