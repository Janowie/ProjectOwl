package src.users;
import java.io.*;
import java.text.*;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class officeWorker extends User {
	
	private double salary = 0;

	public officeWorker(String firstName, String lastName, String emailAddress) {
		super(firstName, lastName, emailAddress);
	}
	
	//		SAVE GROUP		//
	private void groupSave(Group group) {
		try {
			FileOutputStream fileOut = new FileOutputStream("saves/group" + group.ID + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);			
			out.writeObject(group);
			out.close();
			fileOut.close();
			//System.out.println("Saved successfully by " + this.firstName);			
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

	
	//		DELETE GROUP		//
	public Group deleteGroup(Group group) {
		
		for (int i = 0; i < group.groupArrayTeachers.size(); i++) {
			group.groupArrayTeachers.get(i).salary -= 10;
			group.groupArrayTeachers.remove(i);
		}
		
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
	
	//		ADD TEACHER		//
	public void setTeacher(Group group, Teacher newTeacher) {
		group.groupArrayTeachers.add(newTeacher);
		newTeacher.salary = newTeacher.getSalary() + 10;
		newTeacher.notifyAllObservers();
		this.groupSave(group);
	}
	
	//		DELETE TEACHER		//
	public void removeTeacher(Group group, Teacher deleteTeacher) {
		group.groupArrayTeachers.remove(deleteTeacher);		
		deleteTeacher.salary -= 10;
		deleteTeacher.notifyAllObservers();
		this.groupSave(group);
	}
	
	//		ADD STUDENT		//
	public void addStudentToGroup(Group group, Student student) {
	group.groupArrayStudents.add(student);
	student.groupNum = group.ID;
	this.groupSave(group);
	}	
	
	//	SET THE DAY OF WEEK		//
	private int setDayOfWeek(Group group) {
		String[] strDays = new String[] {"Pondelok", "Utorok", "Streda", "Stvrtok",
		        "Piatok", "Sobota",  "Nedela" };
		int dayOfWeek = 0;
		for (int i = 0; i < strDays.length; i++) {
			if (group.day.equals(strDays[i])) {
				dayOfWeek = i + 1;
			}
		}
		
		return dayOfWeek;
	
	}
	
	//		ADD DETAILS		//
	public void addDetails(Group group, String gTime, String gDay, int gDuration, String gRoom) {
		group.time = gTime;
		group.day = gDay;
		group.duration = gDuration;
		group.room = gRoom;
		group.dayOfTheWeek = setDayOfWeek(group);
		
		this.groupSave(group);
	}
	
	//		SET DATE		//	
	WorkTime weeksToDays = (int week) -> week * 7;
	
	private Date duration(Date beginningDate, int week) throws ParseException {
		Date endDate = new Date();
		Calendar c = Calendar.getInstance();
		int days = weeksToDays.perform(week);
		
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
		
		this.groupSave(group);
	}	
}

interface WorkTime {
	int perform(int week);
}
