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
import src.users.OfficeWorker;
import src.users.Teacher;
import src.users.User;

public class SavingOfficeWorkers implements Serializable {
private ArrayList<OfficeWorker>  savedOffice;
	
	public SavingOfficeWorkers() throws ClassNotFoundException {
		if (fileExists()) {
			savedOffice = load();
			System.out.println("Object savedGroups loaded");
		}
		else {
			savedOffice = new ArrayList<OfficeWorker>();
			System.out.println("Object savedGroups created");
		}
	}
	
	public int getLenght() {
		return this.savedOffice.size();
	}
	
	private boolean fileExists() {
		File f = new File("savedOfficeWorkers.ser");
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		else {
			return false;
		}
	}
	
	public OfficeWorker getOffice(int i) {
		return savedOffice.get(i);
	}
	
	public OfficeWorker findOffice(String string) {	
		for (int i = 0; i < savedOffice.size(); i++) {
			if (savedOffice.get(i).username.equals(string)) {
				return savedOffice.get(i);
			}
		}
		return null;
	}
	
	public void saveOffice(OfficeWorker office) {
		savedOffice.add(office);
		save();
	}
	
	public void deleteOffice(OfficeWorker office) {
		savedOffice.remove(office);
		save();
	}
	
	private void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("savedOfficeWorkers.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);			
			out.writeObject(savedOffice);
			out.close();
			fileOut.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
		System.out.println("Saving array saved offices.");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<OfficeWorker> load() throws ClassNotFoundException {
		ArrayList<OfficeWorker> loadedList = new ArrayList<OfficeWorker>();
		try {
			FileInputStream fileIn = new FileInputStream("savedOfficeWorkers.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loadedList = (ArrayList<OfficeWorker>) in.readObject();
			in.close();
			fileIn.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
		System.out.println("Loading array saved offices.");
		return loadedList;
	}
}
