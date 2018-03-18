
@SuppressWarnings("serial")
public class Student implements java.io.Serializable {
	String name;
	int groupNum;
	
	public Student(String stName, int stGroup) {
		name = stName;
		groupNum = stGroup;
	}
}
