package saving;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import src.users.Student;

@SuppressWarnings("serial")
public class SavingStudents implements Serializable {
	private ArrayList<Student>  savedStudents = new ArrayList<Student>();
	
	public SavingStudents() throws ClassNotFoundException {
		if (fileExists()) {
			savedStudents = load();
		}
	}
	
	public Student getStudent(int i) {
		return savedStudents.get(i);
	}
	
	public int getLenght() {
		return this.savedStudents.size();
	}
	
	private boolean fileExists() {
		File f = new File("savedStudents.ser");
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		else {
			return false;
		}
	}
	
	public Student findStudent(String string) {	
		for (int i = 0; i < savedStudents.size(); i++) {
			if (savedStudents.get(i).username.equals(string)) {
				return savedStudents.get(i);
			}
		}
		return null;
	}
	
	public void saveStudent(Student student) {
		savedStudents.add(student);
		save();
	}
	
	public void deleteStudent(Student student) {
		savedStudents.remove(student);
		save();
	}
	
	private void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("userData/savedStudents.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);			
			out.writeObject(savedStudents);
			out.close();
			fileOut.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
		System.out.println("Saving array saved students.");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Student> load() throws ClassNotFoundException {
		ArrayList<Student> loadedList = null;
		try {
			FileInputStream fileIn = new FileInputStream("userData/savedStudents.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loadedList = (ArrayList<Student>) in.readObject();
			in.close();
			fileIn.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
		System.out.println("Loading array saved students.");
		return loadedList;
	}
	
	public void printSavedStudents() {
		if (savedStudents.size() > 0) {
			for (int i = 0; i < savedStudents.size(); i++) {
				System.out.println(savedStudents.get(i).username + "\n");
			}
		}
		else {
			System.out.println("No students");
		}
	}
}
