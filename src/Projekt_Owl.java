
// prekonavanie
// polymorfyzmus
// lambda

import java.io.IOException;
import java.text.ParseException;

//import java.util.*;
public class Projekt_Owl {
	
	static int number = 0;

	public static void main(String [] args) throws IOException, ParseException {
		Student student1 = new Student("Forok", "K", "@", 0);
		Student student2 = new Student("Dorok", "K", "@", 0);
		Student student3 = new Student("Porok", "K", "@", 0);
		Student student4 = new Student("Ferok", "K", "@", 0);
		Student student5 = new Student("Derok", "K", "@", 0);
		Student student6 = new Student("Perok", "K", "@", 0);
		Student student7 = new Student("Lerok", "K", "@", 0);
		
		officeWorker office = new officeWorker("Piater", "K", "@");
		
		Group group1 = new Group(1);
		Group group2 = new Group(2);
		Group toLoad = new Group(0);
		Schedule newSchedule = new Schedule();
		
		group1.addStudentToGroup(student1);
		group1.addStudentToGroup(student2);
		group1.addStudentToGroup(student3);
		group1.addStudentToGroup(student7);
		group2.addStudentToGroup(student4);
		group2.addStudentToGroup(student5);
		group2.addStudentToGroup(student6);
				
		office.addDetails("Fery", group1, "10:00-12:00", "Pondelok", 4, "L. 2.09");
		office.addDetails("Fery", group2, "20:00-21:00", "Pondelok", 6, "L. 2.19");
		office.groupSave(group1);
		office.groupSave(group2);
		
		office.setDate(group1, "20.03.2018", 4);
		
		office.groupSave(group1);
		toLoad = office.groupLoad(1);

		
		number = newSchedule.numberOfGroups();
		newSchedule.printSchedule("Pondelok");

	}
}
