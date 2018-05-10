package saving;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import src.users.OfficeWorker;


@SuppressWarnings("serial")
public class SavingOfficeWorkers implements Serializable {
private ArrayList<OfficeWorker>  savedOffice = new ArrayList<OfficeWorker>();
	
	public SavingOfficeWorkers() throws ClassNotFoundException {
		if (fileExists()) {
			load();
		}
	}
	
	public OfficeWorker getOffice(int i) {
		return savedOffice.get(i);
	}
		
	public int getLenght() {
		return this.savedOffice.size();
	}
	
	private boolean fileExists() {
		File f = new File("userData/savedOfficeWorkers.ser");
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		else {
			return false;
		}
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
	
	public void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("userData/savedOfficeWorkers.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);			
			out.writeObject(savedOffice);
			out.close();
			fileOut.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void load() throws ClassNotFoundException {
		try {
			FileInputStream fileIn = new FileInputStream("userData/savedOfficeWorkers.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			savedOffice = (ArrayList<OfficeWorker>) in.readObject();
			in.close();
			fileIn.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	public void printSavedOffice() {
		if (savedOffice.size() > 0) {
			for (int i = 0; i < savedOffice.size(); i++) {
				System.out.println(savedOffice.get(i).username + "\n");
			}
		}
		else {
			System.out.println("No office workers to print");
		}
	}
}