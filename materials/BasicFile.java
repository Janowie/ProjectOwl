package materials;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class BasicFile implements Serializable {
	int points;
	int number;
	
	public BasicFile(int addPoints, int addNumber) {
		points = addPoints;
		number = addNumber;
	}
	
}
