package src.organization;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Stream;

import src.users.Group;

// test 2
public class Schedule {
	ArrayList<src.users.Group> arrayGroups = new ArrayList<src.users.Group>();
	int index = 0;
	long difference = 0;
	
	public int numberOfGroups() throws IOException {
		try (Stream<Path> files = Files.list(Paths.get("saves"))) {
			long count = files.count();
		    index = (int) count; 
		    return (int) count;
		}		
	}
	
	// days left
	public long dayLeft(src.users.Group group) {
		Date today = new Date();
		return group.end.getTime() - today.getTime();		
	}
	
	
	private void printGroup(src.users.Group group) {
		difference = dayLeft(group);
		System.out.println("\nCas: " + group.time + "\nMiestnost: " + group.room + "\nDen: " + group.day + "\nOstava: " + (difference/604800000+1));
	}
	
	private void buildArrayGroups() {
		
		if (index > 0) {
			for (int i = 0; i < index; i++) {
				int ione = i + 1;
				try {
					FileInputStream fileIn = new FileInputStream("saves/group" + ione +".ser");
					ObjectInputStream in = new ObjectInputStream(fileIn);
					arrayGroups.add((src.users.Group) in.readObject());
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
			System.out.println("No groups at all..");
		}
		
	}
	
	public void printSchedule(String string) {
		int i = 0;
		
		buildArrayGroups();
		
		while (i < index) {
			if (arrayGroups.get(i).day.equals(string)) {
				System.out.println("\n");
				printGroup(arrayGroups.get(i));
			}
			else {
				System.out.println("Nenasiel som skupinu na tento den.");
			}
			i++;
		}
	}	
	
}


