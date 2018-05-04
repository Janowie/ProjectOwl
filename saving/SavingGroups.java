package saving;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import src.users.Group;

@SuppressWarnings("serial")
public class SavingGroups implements Serializable {
	private ArrayList<Group>  savedGroups = new ArrayList<Group>();
	
	public SavingGroups() throws ClassNotFoundException {
		if (fileExists()) {
			savedGroups = load();
			System.out.println("Object savedGroups loaded");
		}
		else {
			System.out.println("Object savedGroups created");
		}
	}
	
	private boolean fileExists() {
		File f = new File("savedGroups.ser");
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		else {
			return false;
		}
	}
	
	public int getLenght() {
		return this.savedGroups.size();
	}
	
	public Group findGroup(int ID) {	
		for (int i = 0; i < savedGroups.size(); i++) {
			if (ID == savedGroups.get(i).getID()) {
				return savedGroups.get(i);
			}
		}
		return null;
	}
	
	public void saveGroup(Group group) {
		savedGroups.add(group);
		save();
	}
	
	public void deleteGroup(Group group) {
		savedGroups.remove(group);
		save();
	}
	
	public void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("savedGroups.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);			
			out.writeObject(savedGroups);
			out.close();
			fileOut.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
		System.out.println("Saving array saved groups.");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Group> load() throws ClassNotFoundException {
		ArrayList<Group> loadedList = new ArrayList<Group>();
		try {
			FileInputStream fileIn = new FileInputStream("savedGroups.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loadedList = (ArrayList<Group>) in.readObject();
			in.close();
			fileIn.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
		System.out.println("Loading array saved groups.");
		return loadedList;
	}
}
