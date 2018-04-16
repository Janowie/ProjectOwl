package src.organization;

import src.users.Teacher;

public class SpecificTeacherObserver extends Observer {

	   public SpecificTeacherObserver(Teacher teacher){
	      this.teacher = teacher;
	      this.teacher.attach(this);
	   }

	   public void update() {
		  System.out.println("Observer updated");
	   }

}
