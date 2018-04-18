package src.gui;
import java.io.IOException;
import java.text.ParseException;

import src.organization.Schedule;
import src.organization.SpecificTeacherObserver;
import src.users.Director;
import src.users.Group;
import src.users.Student;
import src.users.Teacher;
import src.users.officeWorker;

//import java.util.*;
public class Projekt_Owl {
	
	static int number = 0;

	public static void main(String [] args) throws IOException, ParseException {
		
		Teacher teacher1 = new Teacher("ucitel Larok", "K", "@", "SK11000000000054654");
		Teacher teacher2 = new Teacher("ucitel Karok", "K", "@", "SK11000000000058954");
		officeWorker office = new officeWorker("Piater", "K", "@");
		Director director = new Director("director", "Kapol", "@");
		
		new SpecificTeacherObserver(teacher1);
	    new SpecificTeacherObserver(teacher2);
		
		Student student1 = new Student("Forok", "K", "@", 0);
		Student student2 = new Student("Dorok", "K", "@", 0);
		Student student3 = new Student("Porok", "K", "@", 0);
	    Student student4 = new Student("Ferok", "K", "@", 0);
		Student student5 = new Student("Derok", "K", "@", 0);
		Student student6 = new Student("Perok", "K", "@", 0);
		Student student7 = new Student("Lerok", "K", "@", 0);
		
		Group group1 = new Group(1);
		Group group2 = new Group(2);
		Group group3 = new Group(3);
		Group group4 = new Group(4);
		Group group5 = new Group(5);
		Group group6 = new Group(6);
		
		Schedule schedule = new Schedule();
		
		office.addStudentToGroup(group1, student1);
		office.addStudentToGroup(group1, student2);
		office.addStudentToGroup(group1, student3);
		office.addStudentToGroup(group2, student7);
		office.addStudentToGroup(group2, student4);
		office.addStudentToGroup(group2, student5);
		office.addStudentToGroup(group2, student6);
		
		office.setTeacher(group1, teacher1);
		office.setTeacher(group2, teacher1);
		office.setTeacher(group3, teacher1);
		office.setTeacher(group4, teacher2);
		office.setTeacher(group5, teacher2);
		office.setTeacher(group6, teacher2);
				
		office.addDetails(group1, "22:00-23:00", "Pondelok", 6, "L. 2.19");
		office.addDetails(group2, "20:00-21:00", "Pondelok", 6, "L. 2.19");
		office.addDetails(group3, "10:00-12:00", "Utorok", 4, "L. 2.09");
		office.addDetails(group4, "20:00-21:00", "Streda", 6, "L. 2.19");
		office.addDetails(group5, "10:00-12:00", "Stvrtok", 4, "L. 2.09");
		office.addDetails(group6, "10:00-12:00", "Pondelok", 4, "L. 2.09");
	
		
		office.setDate(group1, "28.03.2018", 5);
		office.setDate(group2, "10.04.2018", 1);
		office.setDate(group3, "20.03.2018", 2);
		office.setDate(group4, "20.03.2018", 1);
		office.setDate(group5, "01.04.2018", 4);
		office.setDate(group6, "20.04.2018", 4);
		
		System.out.println("\n\n\n");
		
		schedule.printScheduleWeek();
		student1.printScheduleWeek();
		
	

	    	    	

	}
}
