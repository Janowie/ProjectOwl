import java.io.IOException;

//import java.util.*;
public class Projekt_Owl {
	
	static int number = 0;

	public static void main(String [] args) throws IOException {
		Student student1 = new Student("Forok", 0);
		Student student2 = new Student("Dorok", 0);
		Student student3 = new Student("Porok", 0);
		Student student4 = new Student("Furok", 0);
		Student student5 = new Student("Durok", 0);
		Student student6 = new Student("Purok", 0);
		Student student7 = new Student("Perek", 0);
		
		officeWorker office = new officeWorker("Piater");
		
		Group group1 = new Group(1);
		Group group2 = new Group(2);
		Schedule newSchedule = new Schedule();
		
		group1.addStudentToGroup(student1);
		group1.addStudentToGroup(student2);
		group1.addStudentToGroup(student3);
		group1.addStudentToGroup(student7);
		group2.addStudentToGroup(student4);
		group2.addStudentToGroup(student5);
		group2.addStudentToGroup(student6);
				
		office.addDetails(group1, "10:00-12:00", "Pondelok", "L. 2.09");
		office.addDetails(group2, "20:00-21:00", "Pondelok", "L. 2.19");
		office.groupSave(group1);
		office.groupSave(group2);
		
		number = newSchedule.numberOfGroups();
		newSchedule.printSchedule("Pondelok");
	}
}
