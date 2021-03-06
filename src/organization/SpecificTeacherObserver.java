package src.organization;

import src.users.Teacher;

/**
 * Observer pozoruje zmenu platu ucitela
 * @author Jan
 *
 */
@SuppressWarnings("serial")
public class SpecificTeacherObserver extends Observer implements java.io.Serializable {

	   /**
	   * pripojenie observera k ucitelovi
	   * @param teacher
	   */
	   public SpecificTeacherObserver(Teacher teacher){
	      this.teacher = teacher;
	      this.teacher.attach(this);
	   }

	   /**
	    * vypis pri zmene platu
	    */
	   public void update() {
		  System.out.println("Observer updated");
	   }

}
