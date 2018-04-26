package src.users;
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

import src.organization.Functions;


@SuppressWarnings("serial")
public class Group implements java.io.Serializable, Functions {
	
	
	int ID, size;
	public List<Student> groupArrayStudents;
	public List<Teacher> groupArrayTeachers;
	public String time;
	public String day;
	public int dayOfTheWeek;
	public String room;
	public int duration = 0;
	public Date beginning = new Date();
	public Date end = new Date();
	long difference = 0;
	
	ArrayList<Group> listPeople = new ArrayList<Group>();
	int numberOfPeople = 0;
	
	
	private List<Teacher> buildArrayTeachers() {
		Group newGroup = groupLoad(this.ID);
		if (newGroup != null) {
			return newGroup.groupArrayTeachers;
		}
		else return null;	
	}
	
	private List<Student> buildArrayStudents() {
		Group newGroup = groupLoad(this.ID);
		if (newGroup != null) {
			return newGroup.groupArrayStudents;
		}
		else return null;	
	}
	
	public boolean addTeacher(Teacher teacher) {
		this.groupArrayTeachers = buildArrayTeachers();
		if (this.groupArrayTeachers == null) {
			this.groupArrayTeachers = new ArrayList<Teacher>();
		}
		this.groupArrayTeachers.add(teacher);
		return this.groupArrayTeachers.contains(teacher);
	}
	
	public boolean addStudent(Student student) {
		this.groupArrayStudents = buildArrayStudents();
		if (this.groupArrayStudents == null) {
			this.groupArrayStudents = new ArrayList<Student>();
		}
		this.groupArrayStudents.add(student);
		return this.groupArrayStudents.contains(student);
	}
	
	public int getID() {
		return this.ID;
	}
	
	public void printArray() {
		Group printGroup = this.groupLoad(this.ID);
		
		if (printGroup.groupArrayTeachers.isEmpty() == false) {
			
			System.out.println("\n\nUcitel: " + printGroup.groupArrayTeachers.get(0).username + "\nCas: " 
			+ printGroup.time + "\nMiestnost: " + printGroup.room + "\nDen: " + printGroup.day + "\nTravnie do : " 
			+ printGroup.end.toString() + "\nZiaci: ");
		}
		else {
			System.out.println("Skupina nema ucitela..");
		}
		for (int i = 0; i < printGroup.groupArrayStudents.size(); i++) {
			System.out.println(printGroup.groupArrayStudents.get(i).username + " " + printGroup.groupArrayStudents.get(i).groupNum);
		}	
	}
	

	public Group (int grID) throws IOException {
		ID = grID;
		numberOfPeople = numberOfGroups();
		groupSave(this);
	}
	
	//		NUMBER OF SAVED GROUPS		//
	private int numberOfGroups() throws IOException {
		try (Stream<Path> files = Files.list(Paths.get("saves/groups"))) {
			long count = files.count();
		    return (int) count;
		}		
	}
	
	//	SAVE THIS		//
	public void groupSave(Group group) {	
		numberOfPeople++;
		try {
			FileOutputStream fileOut = new FileOutputStream("saves/groups/group" + group.ID + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);			
			out.writeObject(group);
			out.close();
			fileOut.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
		System.out.println("Saving group " + group.ID );
	}
	
	//		LOAD THIS		//
	public Group groupLoad(int userID) {
		Group loadedgroup;
		try {
			FileInputStream fileIn = new FileInputStream("saves/groups/group" + userID +".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loadedgroup = (Group) in.readObject();
			in.close();
			fileIn.close();
			return loadedgroup;
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
	
	/*
	 *  1 kap okrem 1.2.1
	 *  2 kap cela
	 *  3 kap cela okrem 3.1.1, 3.3.2
	 *  4 kap cela okrem 4.1.2, 4.3
	 *  5 kap cela 
	 *  6 kap okrem str 206 - 207
	 *  7 kap cela
	 *  8 kap cela
	 *  9 kap 
	 * 
	 */
	
	
	
	

}
