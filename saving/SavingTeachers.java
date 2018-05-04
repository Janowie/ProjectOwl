package saving;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import src.users.Teacher;

public class SavingTeachers implements Serializable {
private ArrayList<Teacher>  savedTeachers = new ArrayList<Teacher>();
	
	public SavingTeachers() throws ClassNotFoundException {
		if (fileExists()) {
			savedTeachers = load();
		}
	}
	
	private boolean fileExists() {
		File f = new File("savedTeachers.ser");
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		else {
			return false;
		}
	}
	
	public Teacher findTeacher(String string) {	
		for (int i = 0; i < savedTeachers.size(); i++) {
			if (savedTeachers.get(i).username.equals(string)) {
				return savedTeachers.get(i);
			}
		}
		return null;
	}
	
	public Teacher getTeacher(int i) {
		return savedTeachers.get(i);
	}
	
	public void saveEmployee(Teacher teacher) {
		savedTeachers.add(teacher);
		save();
	}
	
	public void deleteEmployee(Teacher teacher) {
		savedTeachers.remove(teacher);
		save();
	}
	
	private void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("savedTeachers.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);			
			out.writeObject(savedTeachers);
			out.close();
			fileOut.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
		System.out.println("Saving array saved teachers.");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Teacher> load() throws ClassNotFoundException {
		ArrayList<Teacher> loadedList = null;
		try {
			FileInputStream fileIn = new FileInputStream("savedTeachers.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loadedList = (ArrayList<Teacher>) in.readObject();
			in.close();
			fileIn.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
		System.out.println("Loading array saved teachers.");
		return loadedList;
	}

	public int getLength() {
		return savedTeachers.size();
	}
}
