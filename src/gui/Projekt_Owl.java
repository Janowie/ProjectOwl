package gui;
import java.io.IOException;
import java.text.ParseException;

import organization.FinishedLectureObserver;
import organization.Schedule;
import users.Group;
import users.Student;
import users.Teacher;
import users.officeWorker;

//import java.util.*;
public class Projekt_Owl {
	
	static int number = 0;

	public static void main(String [] args) throws IOException, ParseException {
		users.Student student1 = new users.Student("Forok", "K", "@", 0);
		users.Student student2 = new users.Student("Dorok", "K", "@", 0);
		users.Student student3 = new users.Student("Porok", "K", "@", 0);
		users.Student student4 = new users.Student("Ferok", "K", "@", 0);
		users.Student student5 = new users.Student("Derok", "K", "@", 0);
		users.Student student6 = new users.Student("Perok", "K", "@", 0);
		users.Student student7 = new users.Student("Lerok", "K", "@", 0);
		users.Teacher teacher1 = new users.Teacher("ucitel Lerok", "K", "@", "SK11000000000054654");
		
		users.officeWorker office = new users.officeWorker("Piater", "K", "@");
		
		users.Group group1 = new users.Group(1);
		users.Group group2 = new users.Group(2);
		organization.Schedule newSchedule = new organization.Schedule();
		
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
		
		organization.Money money = new organization.Money();
	    //new FinishedLectureObserver(money);

	}
}
