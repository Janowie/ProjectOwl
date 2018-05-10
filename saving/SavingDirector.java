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


@SuppressWarnings("serial")
public class SavingDirector implements Serializable {
private ArrayList<Director>  savedDirector = new ArrayList<Director>();
	
	public SavingDirector() throws ClassNotFoundException {
		if (fileExists()) {
			load();
		}
	}
	
	private boolean fileExists() {
		File f = new File("userData/savedDirectors.ser");
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
			FileOutputStream fileOut = new FileOutputStream("userData/savedDirectors.ser");
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
	public void load() throws ClassNotFoundException {
		try {
			FileInputStream fileIn = new FileInputStream("userData/savedDirectors.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			savedDirector = (ArrayList<Director>) in.readObject();
			in.close();
			fileIn.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}

	public int getLenght() {
		return savedDirector.size();
	}

}
