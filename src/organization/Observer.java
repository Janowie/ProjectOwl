package src.organization;

import src.users.Teacher;

/**
 * zakladna Class observera
 * @author Jan
 *
 */
public abstract class Observer {
	   protected Teacher teacher;
	   public abstract void update();
}
