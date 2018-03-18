import java.io.*;
public class officeWorker {
	String name;
	
	
	public officeWorker(String newName) {
		name = newName;
	}
	
	//		SAVE GROUP		//
	public void groupSave(Group group) {
		try {
			FileOutputStream fileOut = new FileOutputStream("saves/group" + group.ID + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);			
			out.writeObject(group);
			out.close();
			fileOut.close();
			System.out.println("Saved successfully by " + this.name);			
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	
	//		LOAD GROUP		//
	public Group groupLoad(int ID) {
		Group loadedGroup = new Group(5);
		try {
			FileInputStream fileIn = new FileInputStream("saves/group" + ID +".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loadedGroup = (Group) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("Loaded successfully by " + this.name);
			return loadedGroup;
		}
	  catch (IOException i) {
        i.printStackTrace();
        return null;
	  }
      catch (ClassNotFoundException c) {
        System.out.println("Group class not found");
        c.printStackTrace();
        return null;
     }
	}

	
	//	DELETE GROUP		//
	public Group deleteGroup(Group group) {
		File file = new File("saves/group" + group.ID + ".ser");
		if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
		group = null;
		return group;
	}
	
	
	//		ADD DETAILS		//
	public void addDetails(Group group, String gTime, String gDay, String gRoom) {
		group.time = gTime;
		group.day = gDay;
		group.room = gRoom;
	}
	
	
	
	


}
