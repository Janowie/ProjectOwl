package materials;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Druhy class extendujuci BasicFile
 * @author Jan
 *
 */
public class Test extends BasicFile implements Serializable {
	
	Date date = new Date();
	boolean openTest = false;
	String description;

	/**
	 * konstruktor
	 * @param addPoints
	 * @param addNumber
	 * @param addDate
	 * @param addDescription
	 */
	public Test(int addPoints, int addNumber, String addDate, String addDescription) {
		super(addPoints, addNumber);
		date = setDate(addDate);
		description = addDescription;
		save();
	}
	
	/**
	 * nastavi a vrati datum na zaklade vstupneho stringu
	 * @param s
	 * @return
	 */
	private Date setDate(String s) {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date date = null;
		
		try {
			date = format.parse(s);
			return date;
		}
		catch (ParseException e) {
			System.out.println("Invalid date format");
		}
		return date;
	}

	/**
	 * metoda otvori - ukaze test ak su splnene podmienky
	 * @param newDate
	 * @return
	 */
	public boolean openTest(Date newDate) {
		Date today = new Date();
		if (today.compareTo(newDate) == 0) {
			return true;
		}
		else 
			return false;
	}
	
	/**
	 * ulozenie testu
	 */
	private void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("userMaterials/test" + number + ".ser");
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
	 * naciteanie a vratenie testu ak existuje, inak null
	 * @return
	 * @throws ClassNotFoundException
	 */
	public Test load() throws ClassNotFoundException {
		Test loaded = null;
		try {
			FileInputStream fileIn = new FileInputStream("userMaterials/test" + number + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loaded = (Test) in.readObject();
			in.close();
			fileIn.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
		return loaded;
		
	}
	
	/**
	 * vymazanie suboru s testom
	 */
	public void deleteFile() {
		File fileDel = new File("userMaterials/test" + number + ".ser");
        if(fileDel.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
	}
	
	

}
