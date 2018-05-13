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

/*
 * Class ktora do arrayListu uklada vsetkych skupin
 */
@SuppressWarnings("serial")
public class SavingGroups implements Serializable {
	private ArrayList<Group>  savedGroups = new ArrayList<Group>();
	
	// konstruktor, nacita zo suboru ak uz subor existuje
	public SavingGroups() throws ClassNotFoundException {
		if (fileExists()) {
			load();
		}
	}
	
	// metoda kontrolujuca existenciu suboru
	private boolean fileExists() {
		File f = new File("userData/savedGroups.ser");
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		else {
			return false;
		}
	}
	
	// metoda vrati skupinu
	public int getLenght() {
		if(fileExists()) {
			try {
				load();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return this.savedGroups.size();
	}
	
	// metoda najde a vrati skupinu na zaklade jej ID, inak null
	public Group findGroup(int ID) {	
		for (int i = 0; i < savedGroups.size(); i++) {
			if (ID == savedGroups.get(i).getID()) {
				return savedGroups.get(i);
			}
			else  {
				System.out.println("Neviem najst skupinu");
			}
		}
		return null;
	}
	
	// metoda prida objekt skupina do arrayListu  a ulozi sa
	public void saveGroup(Group group) {
		savedGroups.add(group);
		save();
	}
	
	// metoda odstrani objekt skupina z arrayListu  a ulozi sa
	public void deleteGroup(Group group) {
		savedGroups.remove(group);
		save();
	}
	
	// metoda ulozi objekt SavingGroups
	public void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("userData/savedGroups.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);			
			out.writeObject(savedGroups);
			out.close();
			fileOut.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	// metoda nacita a vrati objekt SavingGroups
	@SuppressWarnings("unchecked")
	public void load() throws ClassNotFoundException {
		try {
			FileInputStream fileIn = new FileInputStream("userData/savedGroups.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			savedGroups = (ArrayList<Group>) in.readObject();
			in.close();
			fileIn.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	public void printSavedGroups() {
		if (savedGroups.size() > 0) {
			for (int i = 0; i < savedGroups.size(); i++) {
				System.out.println(savedGroups.get(i).getID() + "\n");
			}
		}
		else {
			System.out.println("No students");
		}
	}
}
