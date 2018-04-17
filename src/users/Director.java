package src.users;

@SuppressWarnings("serial")
public class Director extends officeWorker {
	
	public Director(String userFirstName, String userLastName, String userEmailAddres) {
		super(userFirstName, userLastName, userEmailAddres);
	}
	
	public void updateSalary(Teacher teacher, double addedSalary) {
		teacher.salary = teacher.getSalary() + addedSalary;
	    System.out.println("Salary changed to " + String.valueOf(teacher.getSalary()));
	    teacher.notifyAllObservers();
	}
}
