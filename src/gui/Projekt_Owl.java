package src.gui;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JTextArea;

import src.organization.Schedule;
import src.organization.SpecificTeacherObserver;
import src.users.Director;
import src.users.Group;
import src.users.OfficeWorker;
import src.users.Student;
import src.users.Teacher;

//import java.util.*;
public class Projekt_Owl {
	
	static int number = 0;

	public static void main(String [] args) throws IOException, ParseException {
		
		Teacher teacher1 = new Teacher("ucitelLarok", "heslo", "pp","@");
		Teacher teacher2 = new Teacher("ucitelKarok", "heslo", "pp","@");
		
		OfficeWorker office = new OfficeWorker("menoOffice", "hesloOffice", "pp");
		Director director = new Director("director", "heslo", "pp");
		
		new SpecificTeacherObserver(teacher1);
	    new SpecificTeacherObserver(teacher2);
		
		Student student1 = new Student("meno", "heslo", "pp",0);
		Student student2 = new Student("Dorok", "heslo", "pp",0);
		Student student3 = new Student("Porok", "heslo", "pp",0);
	    Student student4 = new Student("Ferok", "heslo", "pp",0);
		Student student5 = new Student("Derok", "heslo", "pp",0);
		Student student6 = new Student("Perok", "heslo", "pp",0);
		Student student7 = new Student("Lerok", "heslo", "pp",0);
		
		Group group1 = new Group(1);
		Group group2 = new Group(2);
		Group group3 = new Group(3);
		Group group4 = new Group(4);
		Group group5 = new Group(5);
		Group group6 = new Group(6);
	
		
		
		Schedule schedule = new Schedule();
		
		office.setTeacher(1, "ucitelLarok");
		office.setTeacher(2, "ucitelLarok");
		office.setTeacher(3, "ucitelLarok");
		office.setTeacher(4, "ucitelLarok");
		office.setTeacher(5, "ucitelLarok");
		office.setTeacher(6, "ucitelLarok");
		System.out.println("ALL TEACHERS ADDED");
		
				
		office.addDetails(1, "22:00-23:00", "Pondelok", 6, "L. 2.19");
		office.addDetails(2, "20:00-21:00", "Pondelok", 6, "L. 2.19");
		office.addDetails(3, "10:00-12:00", "Utorok", 4, "L. 2.09");
		office.addDetails(4, "20:00-21:00", "Streda", 6, "L. 2.19");
		office.addDetails(5, "10:00-12:00", "Stvrtok", 4, "L. 2.09");
		office.addDetails(6, "10:00-12:00", "Pondelok", 4, "L. 2.09");
	
		
		office.setDate(1, "01.04.2018", 5);
		office.setDate(2, "01.04.2018", 5);
		office.setDate(3, "01.04.2018", 5);
		office.setDate(4, "01.04.2018", 5);
		office.setDate(5, "01.04.2018", 5);
		office.setDate(6, "01.04.2018", 5);
		
		office.addStudentToGroup(1, "meno");
		office.addStudentToGroup(1, "Dorok");
		office.addStudentToGroup(1, "Porok");
		
		group1.printArray();
		
		office.addStudentToGroup(2, "Ferok");
		office.addStudentToGroup(2, "Derok");
		office.addStudentToGroup(2, "Perok");
		office.addStudentToGroup(2, "Lerok");
		
		System.out.println("\n\n\n");
		
		//System.out.println("Group1 teacher name: " + group1.groupArrayTeachers.get(0));
				
		
		//schedule.printScheduleWeek();
		
		
		
		
	


	    	    	

	}
}
