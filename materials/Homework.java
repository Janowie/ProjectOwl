package materials;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Class extenduje BasicFile
 * @author Jan
 *
 */
@SuppressWarnings("serial")
public class Homework extends BasicFile implements Serializable {
	private int number;
	int minPoints;
	String dueDate;
	private String description;

	/**
	 * konstruktor prekonavajuci metodu 
	 * @param addPoints
	 * @param addMinPoints
	 * @param addDueDate
	 * @param addNumber
	 * @param addDescription
	 */
	public Homework(int addPoints, int addMinPoints, String addDueDate, int addNumber, String addDescription) {
		super(addPoints, addNumber);
		minPoints = addMinPoints;
		dueDate = addDueDate;
		setDescription(addDescription);
		save();
	}	
	
	/**
	 * Ulozenie danej ulohy
	 */
	private void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("userMaterials/homework" + getNumber() + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);			
			out.writeObject(this);
			out.close();
			fileOut.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	/**
	 * nacitanie danej ulohy
	 * @return
	 * @throws ClassNotFoundException
	 */
	public Homework load() throws ClassNotFoundException {
		Homework loaded = null;
		try {
			FileInputStream fileIn = new FileInputStream("userMaterials/homework" + getNumber() + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loaded = (Homework) in.readObject();
			in.close();
			fileIn.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
		return loaded;
		
	}
	
	/** 
	 * Vymazanie ulohy
	 */
	public void deleteFile() {
		File fileDel = new File("userMaterials/homework" + getNumber() + ".ser");
        if(fileDel.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
	}

	/**
	 * 
	 * @return cislo
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * set cislo
	 * @param number
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * 
	 * @return opis
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * nastav opis
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	

}
