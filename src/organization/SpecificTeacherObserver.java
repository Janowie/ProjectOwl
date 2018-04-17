package src.organization;

import src.users.Teacher;


@SuppressWarnings("serial")
public class SpecificTeacherObserver extends Observer implements java.io.Serializable {

	   public SpecificTeacherObserver(Teacher teacher){
	      this.teacher = teacher;
	      this.teacher.attach(this);
	   }

	   public void update() {
		  System.out.println("Observer updated");
	   }

}
