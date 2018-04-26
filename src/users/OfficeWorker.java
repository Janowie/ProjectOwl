package src.users;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;

@SuppressWarnings("serial")
public class OfficeWorker extends User {
	ArrayList<OfficeWorker> listPeople = new ArrayList<OfficeWorker>();
	ArrayList<Teacher> listTeachers = new ArrayList<Teacher>();
	int indexTeachers = 0;
	ArrayList<Student> listStudents = new ArrayList<Student>();
	int indexStudents = 0;
	ArrayList<Group> listGroups = new ArrayList<Group>();
	int indexGroups = 0;
	
	int userID = 0;
	int numberOfPeople = 0;
	int index = 0;
	
	private double salary = 0;
	private int userCode = 0;
	
	public void setUserCode(int newCode) {
		this.userCode = newCode;
	}
	/*
	//		LOAD GROUP		//
	public Group groupLoad(int ID) throws IOException {
		Group loadedGroup = new Group(5);
		try {
			FileInputStream fileIn = new FileInputStream("saves/groups/group" + ID +".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loadedGroup = (Group) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("Loaded successfully by " + this.username);
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
	*/

	
	//		DELETE GROUP		//
	public Group actuallyDeleteGroup(Group group) {
		
		for (int i = 0; i < group.groupArrayTeachers.size(); i++) {
			group.groupArrayTeachers.get(i).salary -= 10;
			group.groupArrayTeachers.remove(i);
		}
		
		File file = new File("saves/groups/group" + group.ID + ".ser");
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
	
	public void deleteGroup(int ID) throws IOException {
		Group toBeDeleted = findGroup(ID);
		
		toBeDeleted = actuallyDeleteGroup(toBeDeleted);
	}
	
	//	NUMBER OF SAVED TEACHERS		//
	private int numberOfTeachers() throws IOException {
		try (Stream<Path> files = Files.list(Paths.get("saves/employees/Teachers"))) {
			long count = files.count();
		    return (int) count;
		}		
	}
	
	//		BILD ARRAY FROM ALL SAVED TEACHERS		//
	private void buildArrayTeachers() throws IOException {
		indexTeachers = numberOfTeachers();
		if (indexTeachers > 0) {
			for (int i = 0; i < indexTeachers; i++) {
				int ione = i + 1;
				try {
					FileInputStream fileIn = new FileInputStream("saves/employees/Teachers/teacher" + ione +".ser");
					ObjectInputStream in = new ObjectInputStream(fileIn);
					listTeachers.add((Teacher) in.readObject());
					in.close();
					fileIn.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				catch (ClassNotFoundException c) {
					System.out.println("No class Teacher found");
					c.printStackTrace();
				}
			}
		}
		else {
			System.out.println("No Teacher at all..");
		}
		
	}
	
	//	NUMBER OF SAVED GROUPS		//
	private int numberOfGroups() throws IOException {
		try (Stream<Path> files = Files.list(Paths.get("saves/groups"))) {
			long count = files.count();
		    return (int) count;
		}		
	}
	
	//		BILD ARRAY FROM ALL SAVED GROUPS		//
	private void buildArrayGroups() throws IOException {
		indexGroups = numberOfGroups();
		if (indexGroups > 0) {
			for (int i = 0; i < indexGroups; i++) {
				int ione = i + 1;
				try {
					FileInputStream fileIn = new FileInputStream("saves/groups/group" + ione +".ser");
					ObjectInputStream in = new ObjectInputStream(fileIn);
					listGroups.add((Group) in.readObject());
					in.close();
					fileIn.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				catch (ClassNotFoundException c) {
					System.out.println("No class Teacher found");
					c.printStackTrace();
				}
			}
		}
		else {
			System.out.println("No groups to build an array at all..");
		}
		
	}
	
	//	NUMBER OF SAVED STUDENTS		//
	private int numberOfStudents() throws IOException {
		try (Stream<Path> files = Files.list(Paths.get("saves/students"))) {
			long count = files.count();
		    return (int) count;
		}		
	}
	
	//		BILD ARRAY FROM ALL SAVED STUDENTS		//
	private void buildArrayStudents() throws IOException {
		indexStudents = numberOfStudents();
		if (indexStudents > 0) {
			for (int i = 0; i < indexGroups; i++) {
				int ione = i + 1;
				try {
					FileInputStream fileIn = new FileInputStream("saves/students/student" + ione +".ser");
					ObjectInputStream in = new ObjectInputStream(fileIn);
					listStudents.add((Student) in.readObject());
					in.close();
					fileIn.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				catch (ClassNotFoundException c) {
					System.out.println("No class Students found");
					c.printStackTrace();
				}
			}
		}
		else {
			System.out.println("No Students at all..");
		}
		
	}
	
	//		SEARCH IN GROUPS (teacher & groups)		//
	private Teacher findTeacher(String teacherUsername) throws IOException {
		buildArrayTeachers();
		
		for (int i = 0; i < listTeachers.size(); i++) {
			if (teacherUsername.equals(listTeachers.get(i).username)) {
				return listTeachers.get(i);
			}
		}
		
		return null;
	}
	
	private Group findGroup(int ID) throws IOException {
		buildArrayGroups();
		
		for (int i = 0; i < listGroups.size(); i++) {
			if (ID == listGroups.get(i).ID) {
				return listGroups.get(i);
			}
		}
		
		return null;
	}
	
	private Student findStudent(String studentUsername) throws IOException {
		buildArrayStudents();
		
		for (int i = 0; i < listStudents.size(); i++) {
			if (studentUsername.equals(listStudents.get(i).username)) {
				return listStudents.get(i);
			}
		}
		
		return null;
	}
	
	
	public OfficeWorker(String userFirstName, String newPassword, String userEmailAddres) throws IOException {
		super(userFirstName, newPassword, userEmailAddres);
		numberOfPeople = numberOfOffice();
		officeSave();
	}

	
	
	
	//		ADD TEACHER		//
	private void actuallySetTeacher(Group group, Teacher newTeacher) {
		if (group.addTeacher(newTeacher)) {
			newTeacher.salary = newTeacher.getSalary() + 10;
			newTeacher.notifyAllObservers();
			
			group.groupSave(group);
		}
		else {
			System.out.println("actuallyAddTeacher UNSUCCSFULL");
		}
	}
	
	public void setTeacher(int ID, String teacherUsername) throws IOException {
		Group group = findGroup(ID);
		Teacher teacher = findTeacher(teacherUsername);		
		
		actuallySetTeacher(group, teacher);
	}
	
	//		DELETE TEACHER		//
	public void actuallyRemoveTeacher(Group group, Teacher deleteTeacher) {
		group.groupArrayTeachers.remove(deleteTeacher);		
		deleteTeacher.salary -= 10;
		deleteTeacher.notifyAllObservers();
		group.groupSave(group);
	}
	
	public void removeTeacher(int ID, String teacherUsername) throws IOException {
		Group group = findGroup(ID);
		Teacher deleteTeacher = findTeacher(teacherUsername);
		
		actuallyRemoveTeacher(group, deleteTeacher);
	}
	
	//		ADD STUDENT		//
	private void actuallyAddStudentToGroup(Group group, Student newStudent) {
		if (group.addStudent(newStudent)) {
			System.out.println("actuallyAddStudent SUCCSFULL");			
			group.groupSave(group);
		}
		else {
			System.out.println("actuallyAddStudent UNSUCCSFULL");
		}
	}	
	
	public void addStudentToGroup(int ID, String username) throws IOException {
		Group group = findGroup(ID);
		Student student = findStudent(username);
		
		actuallyAddStudentToGroup(group, student);
	}
	
	//		DELETE STUDENT		//
	public void actuallyDeleteStudentFromGroup(Group group, Student student) {
		group.groupArrayStudents.remove(student);
		student.groupNum = 0;
		group.groupSave(group);
	}	
	
	public void deleteStudentFromGroup(int ID, String username) throws IOException {
		Group group = findGroup(ID);
		Student student = findStudent(username);
		
		actuallyDeleteStudentFromGroup(group, student);
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
	
	
	
	//	ACTUALLY ADD DETAILS		//
	public void actuallyAddDetails(Group group, String gTime, String gDay, int gDuration, String gRoom) {
		group.time = gTime;
		group.day = gDay;
		group.duration = gDuration;
		group.room = gRoom;
		group.dayOfTheWeek = setDayOfWeek(group);
		
		group.groupSave(group);
	}
	
	//		ADD DETAILS		//
	public void addDetails(int ID, String gTime, String gDay, int gDuration, String gRoom) throws IOException {
		Group group = findGroup(ID);
		
		actuallyAddDetails(group, gTime, gDay, gDuration, gRoom);
	}
	
	//		SET DATE		//	
	WorkTime weeksToDays = (WorkTime & Serializable)(int week) -> week * 7;
	
	private Date duration(Date beginningDate, int week) throws ParseException {
		Date endDate = new Date();
		Calendar c = Calendar.getInstance();
		int days = weeksToDays.perform(week);
		
		c.setTime(beginningDate);
		c.add(Calendar.DAY_OF_MONTH, days);  
		endDate = c.getTime();

		return endDate;
	}
	
	public void actuallySetDate(Group group, String timeInput, int duration) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date beginningDate = null;
		Date endDate;
		
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
		
		//System.out.println("Course " + group.ID + " begins: " + beg + " ends: " + end);
		
		group.groupSave(group);
	}
	
	public void setDate(int ID, String timeInput, int duration) throws IOException, ParseException {
		Group group = findGroup(ID);
		
		actuallySetDate(group, timeInput, duration);
	}
	
	
	
	
	
	// O
	//	F
	//	  F
	//		I
	//		   C
	//			  E
	
	
	
	
	
//	NUMBER OF SAVED OFFICE		//
	private int numberOfOffice() throws IOException {
		try (Stream<Path> files = Files.list(Paths.get("saves/employees/Office"))) { // zmenit
			long count = files.count();
		    return (int) count;
		}		
	}
	
	//	BILD ARRAY FROM ALL SAVED OFFICE		//
	private void buildArrayOffice() {
		if (index > 0) {
			for (int i = 0; i < index; i++) {
				int ione = i + 1;
				try {
					FileInputStream fileIn = new FileInputStream("saves/employees/Office/office" + ione +".ser"); // zmenit
					ObjectInputStream in = new ObjectInputStream(fileIn);
					listPeople.add((OfficeWorker) in.readObject());
					in.close();
					fileIn.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				catch (ClassNotFoundException c) {
					System.out.println("Group class not found");
					c.printStackTrace();
				}
			}
		}
		else {
			System.out.println("No groups at all..");
		}
		
	}
	
	private void setUserID() {
		this.userID = numberOfPeople + 1;
	}
	
	//		SAVE THIS		//
	private void officeSave() {
		setUserID();	
		numberOfPeople++;
		try {
			FileOutputStream fileOut = new FileOutputStream("saves/employees/Office/office" + this.userID + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);			
			out.writeObject(this);
			out.close();
			fileOut.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	
	//		LOAD THIS		//
	public OfficeWorker officeLoad(int userID) {
		OfficeWorker loadedPerson;
		try {
			FileInputStream fileIn = new FileInputStream("saves/employees/Office/office" + userID +".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loadedPerson = (OfficeWorker) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("Loaded successfully by " + this.username);
			return loadedPerson;
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
	
	
	//		DELETE THIS		//
	public OfficeWorker deleteStudent(OfficeWorker person) {
		buildArrayOffice();
		
		File file = new File("saves/employees/Office/office" + person.userID + ".ser");
		if(file.delete())
	    {
	        System.out.println("File deleted successfully");
	        numberOfPeople--;
	    }
	    else
	    {
	        System.out.println("Failed to delete the file");
	    }
		
		for (int i = 0; i < listPeople.size(); i++) {
			if ((i + 1) <= person.userID) {
				continue;
			}
			else {
				listPeople.get(i).userID = i;
			}
		}
		
		person = null;
		
		return person;
	}
}

	





interface WorkTime {
	int perform(int week);
}
