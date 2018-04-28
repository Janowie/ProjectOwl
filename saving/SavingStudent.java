package saving;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import src.users.Student;

@SuppressWarnings("serial")
public abstract class SavingStudent implements Serializable {
	private ArrayList<Student>  savedStudents = new ArrayList<Student>();
	
	public Student findStudent(String string) {
		
		for (int i = 0; i < savedStudents.size(); i++) {
			if ((savedStudents.get(i).username.equals(string)) || (savedStudents.get(i).getID() == (Integer.parseInt(string)))) {
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
			FileOutputStream fileOut = new FileOutputStream("savedStudents.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);			
			out.writeObject(this);
			out.close();
			fileOut.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
		System.out.println("Saving array saved students.");
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList<Student> load() throws ClassNotFoundException {
		ArrayList<Student> loadedList = new ArrayList<Student>();
		try {
			FileInputStream fileIn = new FileInputStream("savedStudents.ser");
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
}
