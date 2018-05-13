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

/**
 * Class ktora do arrayListu uklada vsetkych Directorov
 */
@SuppressWarnings("serial")
public class SavingDirector implements Serializable {
private ArrayList<Director>  savedDirector = new ArrayList<Director>();

	/**
	 * konstruktor, nacita zo suboru ak uz subor existuje
	 * @throws ClassNotFoundException
	 */
	public SavingDirector() throws ClassNotFoundException {
		if (fileExists()) {
			load();
		}
	}

	/**
	 * metoda kontrolujuca existenciu suboru
	 * @return
	 */
	private boolean fileExists() {
		File f = new File("userData/savedDirectors.ser");
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		else {
			return false;
		}
	}

	/**
	 * 
	 * @param i
	 * @return Directora
	 */
	public Director getDirector(int i) {
		return savedDirector.get(i);
	}

	/**
	 * metoda najde a vrati directora na zaklade jeho mena, inak null
	 * @param string
	 * @return
	 */
	public Director findDirector(String string) {	
		for (int i = 0; i < savedDirector.size(); i++) {
			if (savedDirector.get(i).username.equals(string)) {
				return savedDirector.get(i);
			}
		}
		return null;
	}

	/**
	 * metoda prida objekt Director do arrayListu a ulozi sa
	 * @param director
	 */
	public void saveDirector(Director director) {
		savedDirector.add(director);
		save();
	}

	/**
	 * metoda odstrani objekt Director z arrayListu  a ulozi sa
	 * @param director
	 */
	public void deleteDirector(Director director) {
		savedDirector.remove(director);
		save();
	}

	/**
	 * metoda ulozi objekt SavingDirector
	 */
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

	/**
	 * metoda nacita a vrati objekt SavingDirector
	 * @throws ClassNotFoundException
	 */
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
