package gui;
import java.io.IOException;
import java.text.ParseException;

import organization.Schedule;
import users.Group;
import users.Student;
import users.Teacher;
import users.officeWorker;

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
		Teacher teacher1 = new Teacher("ucitel Lerok", "K", "@", "SK11000000000054654");
		
		officeWorker office = new officeWorker("Piater", "K", "@");
		
		Group group1 = new Group(1);
		Group group2 = new Group(2);
		Schedule newSchedule = new Schedule();
		
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

	}
}
