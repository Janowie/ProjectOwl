package src.users;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Stream;

@SuppressWarnings("serial")
public class Student extends User implements java.io.Serializable {
	int groupNum;
	int index = 0;
	Group studentsGroup = new Group(0);
	
	public Student(String firstName, String lastName, String emailAddress, int stGroup) throws IOException {
		super(firstName, lastName, emailAddress);
		groupNum = stGroup;
		index = numberOfGroups();
	}
	
	//	NUMBER OF SAVED GROUPS		//
	private int numberOfGroups() throws IOException {
		try (Stream<Path> files = Files.list(Paths.get("saves"))) {
			long count = files.count();
		    index = (int) count; 
		    return (int) count;
		}		
	}
	
	//	BILD ARRAY FROM ALL SAVED GROUPS		//
	private void setStudentsGroup() {
		if (index > 0) {
			for (int i = 0; i < index; i++) {
				int ione = i + 1;
				try {
					FileInputStream fileIn = new FileInputStream("saves/group" + ione +".ser");
					ObjectInputStream in = new ObjectInputStream(fileIn);
					if (this.groupNum == ione)
						studentsGroup = (Group) in.readObject();
					in.close();
					fileIn.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				catch (ClassNotFoundException c) {
					System.out.println("Group class not found");
					c.printStackTrace();
				}
			}
		}
		else {
			System.out.println("No group..");
		}
		
	}
	
	public void printArray() {
		System.out.println("\nCas: " + studentsGroup.time + "\nMiestnost: " + studentsGroup.room + "\nDen: " + studentsGroup.day + "\nTravnie do : " + studentsGroup.end.toString());
	}
	
	public void printScheduleWeek() {
		Date today = new Date();
		
		setStudentsGroup();
		
		if ((studentsGroup.beginning.before(today) || studentsGroup.beginning.equals(today)) && (studentsGroup.end.after(today) || studentsGroup.end.equals(today))) {
			printArray();
		}
	}
	
}

