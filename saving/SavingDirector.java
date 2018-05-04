package saving;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import src.users.Director;
import src.users.Teacher;


public class SavingDirector implements Serializable {
private ArrayList<Director>  savedDirector = new ArrayList<Director>();
	
	public SavingDirector() throws ClassNotFoundException {
		if (fileExists()) {
			savedDirector = load();
			System.out.println("Object savedDirectors loaded");
		}
		else {
			System.out.println("Object savedDirectors created");
		}
	}
	
	private boolean fileExists() {
		File f = new File("savedDirectors.ser");
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		else {
			return false;
		}
	}
	
	public Director getDirector(int i) {
		return savedDirector.get(i);
	}
	
	public Director findDirector(String string) {	
		for (int i = 0; i < savedDirector.size(); i++) {
			if (savedDirector.get(i).username.equals(string)) {
				return savedDirector.get(i);
			}
		}
		return null;
	}
	
	public void saveDirector(Director director) {
		savedDirector.add(director);
		save();
	}
	
	public void deleteDirector(Director director) {
		savedDirector.remove(director);
		save();
	}
	
	private void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("savedDirectors.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);			
			out.writeObject(savedDirector);
			out.close();
			fileOut.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
		System.out.println("Saving array saved offices.");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Director> load() throws ClassNotFoundException {
		ArrayList<Director> loadedList = new ArrayList<Director>();
		try {
			FileInputStream fileIn = new FileInputStream("savedDirectors.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loadedList = (ArrayList<Director>) in.readObject();
			in.close();
			fileIn.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
		System.out.println("Loading array saved directors.");
		return loadedList;
	}

	public int getLenght() {
		return savedDirector.size();
	}

}
