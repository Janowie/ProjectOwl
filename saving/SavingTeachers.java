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

/**
 * Class ktora do arrayListu uklada vsetkych ucitelov
 * @author Jan
 *
 */
@SuppressWarnings("serial")
public class SavingTeachers implements Serializable {
private ArrayList<Teacher>  savedTeachers = new ArrayList<Teacher>();
	
	/**
	 * konstruktor, nacita zo suboru ak uz subor existuje
	 * @throws ClassNotFoundException
	 */
	public SavingTeachers() throws ClassNotFoundException {
		if (fileExists()) {
			load();
		}
	}
	
	/**
	 * metoda kontrolujuca existenciu suboru
	 * @return true / false
	 */
	private boolean fileExists() {
		File f = new File("userData/savedTeachers.ser");
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * najde v arrayListe ucitela na zaklade ID skupiny a vrati
	 * @param ID
	 * @return
	 */
	public Teacher findTeacherByGroup(int ID) {
		for (int i = 0; i < savedTeachers.size(); i++) {
			if (savedTeachers.get(i).getGroup(i) == ID) {
				return savedTeachers.get(i);
			}
		}
		return null;
	}

	/**
	 * najde v arrayListe ucitela na zaklade mena a vrati
	 * @param string
	 * @return
	 */
	public Teacher findTeacher(String string) {	
		for (int i = 0; i < savedTeachers.size(); i++) {
			if (savedTeachers.get(i).username.equals(string)) {
				return savedTeachers.get(i);
			}
		}
		return null;
	}
	
	/**
	 * metoda vrati objekt ucitel podla poradie v arrayListe
	 * @param i
	 * @return
	 */
	public Teacher getTeacher(int i) {
		return savedTeachers.get(i);
	}
	
	/**
	 * metoda prida objekt ucitel do arrayListu  a ulozi sa
	 * @param teacher
	 */
	public void saveEmployee(Teacher teacher) {
		savedTeachers.add(teacher);
		save();
	}
	
	/**
	 * metoda odstrani ucitel skupina z arrayListu  a ulozi sa
	 * @param teacher
	 */
	public void deleteEmployee(Teacher teacher) {
		savedTeachers.remove(teacher);
		save();
	}
	
	/**
	 * metoda ulozi objekt SavingTeachers
	 */
	public void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("userData/savedTeachers.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);			
			out.writeObject(savedTeachers);
			out.close();
			fileOut.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}

	/**
	 * metoda nacita a vrati objekt SavingTeachers zo suboru
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public void load() throws ClassNotFoundException {
		try {
			FileInputStream fileIn = new FileInputStream("userData/savedTeachers.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			savedTeachers = (ArrayList<Teacher>) in.readObject();
			in.close();
			fileIn.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}

	public int getLength() {
		return savedTeachers.size();
	}
}
