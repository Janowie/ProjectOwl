package src.gui;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import materials.Homework;
import saving.SavingDirector;
import saving.SavingGroups;
import saving.SavingOfficeWorkers;
import saving.SavingStudents;
import saving.SavingTeachers;
import src.organization.Schedule;
import src.users.Director;
import src.users.Group;
import src.users.OfficeWorker;
import src.users.Student;
import src.users.Teacher;

/*
 * Class s mainon. Sluzi najma na rychle vytvorenie prvotnych uzivatelov
 */
public class Projekt_Owl {
	
	static int number = 0;

	public static void main(String [] args) throws IOException, ParseException, ClassNotFoundException {
		
		Date today = new Date();
		Student student1 = new Student("student1@", "heslo1", 0);
		Student student2 = new Student("student2@", "heslo2", 0);
		Student student3 = new Student("student3@", "heslo3", 0);
		Student student4 = new Student("student4@", "heslo4", 0);
		Student student5 = new Student("student5@", "heslo5", 0);
		Student student6 = new Student("student6@", "heslo6", 0);
		OfficeWorker office = new OfficeWorker("office1@", "heslo1");
		Teacher teacher1 = new Teacher("teacher1@","heslo1","");
		Group skupina1 = new Group(1);
		Group skupina2 = new Group(2);
		Director director = new Director("director1@", "heslo1");
		SavingStudents savedStudents = new SavingStudents();
		SavingOfficeWorkers savedOffice = new SavingOfficeWorkers();
		SavingGroups savedGroups = new SavingGroups();
		SavingTeachers savedTeachers = new SavingTeachers();
		SavingDirector savedDirectors = new SavingDirector();
		
		Homework homework1 = new Homework(20, 5, "20.5.2018", 1, "Napis ako sa povie pes.");
		
		Schedule schedule = new Schedule();

		//office.addStudent("student1", 1);
		office.addDetails(1, "13:00", "Pondelok","03.05.2018", 4, "L.9");
		
		
		System.out.println("\n");
	
		
		
	}
}
