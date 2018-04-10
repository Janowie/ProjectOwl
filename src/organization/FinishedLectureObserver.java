package organization;

public class FinishedLectureObserver extends Observer {
	Money money = new Money();
	
	public FinishedLectureObserver(Lecture lecture){
	      this.lecture = lecture;
	      this.lecture.attach(this);
	}

	 @Override
	 public void update() {
	       money.addToSalary();
	 }
}
