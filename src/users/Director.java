package src.users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

import saving.SavingDirector;

@SuppressWarnings("serial")
public class Director extends OfficeWorker {
	int userID = 0;
	SavingDirector arrayDirectors;
	
	public Director(String userFirstName, String newPassword, String userEmailAddres) throws ClassNotFoundException {
		super(userFirstName, newPassword, userEmailAddres);
		arrayDirectors = new SavingDirector();
		directorSave();
	}
	
	//	SAVE THIS		//
	private void directorSave() {
		arrayDirectors.saveDirector(this);
	}
	
	//	DELETE THIS		//
	public void deletePerson() {
		arrayDirectors.deleteDirector(this);
	}
	
	public void updateSalary(Teacher teacher, double addedSalary) {
		teacher.salary = teacher.getSalary() + addedSalary;
	    System.out.println("Salary changed to " + String.valueOf(teacher.getSalary()));
	    teacher.notifyAllObservers();
	}
	
	private void setUserID(int ID) {
		this.userID = ID;
	}	
	
}
