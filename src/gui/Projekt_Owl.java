package src.gui;
import java.io.IOException;
import java.text.ParseException;

import src.organization.FinishedLectureObserver;
import src.organization.Schedule;
import src.users.Group;
import src.users.Student;
import src.users.Teacher;
import src.users.officeWorker;

//import java.util.*;
public class Projekt_Owl {
	
	static int number = 0;

	public static void main(String [] args) throws IOException, ParseException {
		src.users.Student student1 = new src.users.Student("Forok", "K", "@", 0);
		src.users.Student student2 = new src.users.Student("Dorok", "K", "@", 0);
		src.users.Student student3 = new src.users.Student("Porok", "K", "@", 0);
		src.users.Student student4 = new src.users.Student("Ferok", "K", "@", 0);
		src.users.Student student5 = new src.users.Student("Derok", "K", "@", 0);
		src.users.Student student6 = new src.users.Student("Perok", "K", "@", 0);
		src.users.Student student7 = new src.users.Student("Lerok", "K", "@", 0);
		src.users.Teacher teacher1 = new src.users.Teacher("ucitel Lerok", "K", "@", "SK11000000000054654");
		
		src.users.officeWorker office = new src.users.officeWorker("Piater", "K", "@");
		
		src.users.Group group1 = new src.users.Group(1);
		src.users.Group group2 = new src.users.Group(2);
		src.organization.Schedule newSchedule = new src.organization.Schedule();
		
		office.addStudentToGroup(group1, student1);
		office.addStudentToGroup(group1, student2);
		office.addStudentToGroup(group1, student3);
		office.addStudentToGroup(group2, student7);
		office.addStudentToGroup(group2, student4);
		office.addStudentToGroup(group2, student5);
		office.addStudentToGroup(group2, student6);
		
		office.addTeacherToGroup(group1, teacher1);
		office.addTeacherToGroup(group2, teacher1);
				
		office.addDetails(group1, "10:00-12:00", "Pondelok", 4, "L. 2.09");
		office.addDetails(group2, "20:00-21:00", "Pondelok", 6, "L. 2.19");
		office.groupSave(group1);
		office.groupSave(group2);
		
		office.setDate(group1, "20.03.2018", 4);
		
		office.groupSave(group1);
		
		group1.printArray();
		group2.printArray();

		
		number = newSchedule.numberOfGroups();
		
		src.organization.Money money = new src.organization.Money();
	    new FinishedLectureObserver(money);

	}
}
