package src.organization;

import src.users.Teacher;

public abstract class Observer {
	   protected Teacher teacher;
	   public abstract void update();
}
