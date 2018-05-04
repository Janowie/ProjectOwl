package src.gui;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JTextArea;

import com.sun.istack.internal.FinalArrayList;

import saving.SavingGroups;
import saving.SavingStudents;
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

	public static void main(String [] args) throws IOException, ParseException, ClassNotFoundException {
		
		Student ferko = new Student("Ferko", "", "", 0);
		SavingStudents savedStudents = new SavingStudents();
		savedStudents.saved = savedStudents.load();
		//savedStudents.saveStudent(ferko);
		//Student skuska = new Student("skuska","skuska", "skuska",0);
		
		//skuska = savedStudents.findStudent("Ferko");
		
		//savedStudents.printSavedStudents();
		
		
		
	


	    	    	

	}
}
