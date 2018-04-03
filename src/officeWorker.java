package src;

import java.io.*;
import java.text.*;
import java.util.Calendar;
import java.util.Date;
@SuppressWarnings("serial")
public class officeWorker extends User {
<<<<<<< HEAD
	
	
	
	
=======
	
	
	
	
>>>>>>> 7e9b9bd82263dbe262fb688b3528f2fd412dd8b2
	public officeWorker(String firstName, String lastName, String emailAddress) {
		super(firstName, lastName, emailAddress);
	}
	
	//		SAVE GROUP		//
	public void groupSave(Group group) {
		try {
			FileOutputStream fileOut = new FileOutputStream("saves/group" + group.ID + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);			
			out.writeObject(group);
			out.close();
			fileOut.close();
			System.out.println("Saved successfully by " + this.firstName);			
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	
	//		LOAD GROUP		//
	public Group groupLoad(int ID) {
		Group loadedGroup = new Group(5);
		try {
			FileInputStream fileIn = new FileInputStream("saves/group" + ID +".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loadedGroup = (Group) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("Loaded successfully by " + this.firstName);
			return loadedGroup;
		}
	  catch (IOException i) {
        i.printStackTrace();
        return null;
	  }
      catch (ClassNotFoundException c) {
        System.out.println("Group class not found");
        c.printStackTrace();
        return null;
     }
	}

	
	//	DELETE GROUP		//
	public Group deleteGroup(Group group) {
		File file = new File("saves/group" + group.ID + ".ser");
		if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
		group = null;
		return group;
	}
	
	
	//		ADD DETAILS		//
	public void addDetails(String teachersName, Group group, String gTime, String gDay, int gDuration, String gRoom) {
		group.teacherName = teachersName;
		group.time = gTime;
		group.day = gDay;
		group.duration = gDuration;
		group.room = gRoom;
	}
	
	
	//		SET DATE		//
	
	//Skusit ako lambdu
	private int weeksToDays(int week) {
		int days = 0;
		days = week*7;
		return days;
	}
	
	
	private Date duration(Date beginningDate, int week) throws ParseException {
		Date endDate = new Date();
		Calendar c = Calendar.getInstance();
		int days = weeksToDays(week);
		
		c.setTime(beginningDate);
		c.add(Calendar.DAY_OF_MONTH, days);  
		endDate = c.getTime();

		return endDate;
	}
	
	public void setDate(Group group, String timeInput, int duration) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date beginningDate = null;
		Date endDate = new Date();
		
		try {
			beginningDate = format.parse(timeInput);
			group.beginning = beginningDate;
		}
		catch (ParseException e) {
			System.out.println("Invalid date format");
		}
		
		endDate = duration(beginningDate, duration);
		
		group.end = endDate;
		
		String beg = format.format(beginningDate);
		String end = format.format(endDate);
		
		System.out.println("Course " + group.ID + " begins: " + beg + " ends: " + end);
	}
	
	
	
	


}
