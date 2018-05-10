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

public class Test extends BasicFile implements Serializable {
	
	Date date = new Date();
	boolean openTest = false;
	String description;

	public Test(int addPoints, int addNumber, String addDate, String addDescription) {
		super(addPoints, addNumber);
		date = setDate(addDate);
		description = addDescription;
		save();
	}
	
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

	
	public boolean openTest(Date newDate) {
		Date today = new Date();
		if (today.compareTo(newDate) == 0) {
			return true;
		}
		else 
			return false;
	}
	
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
