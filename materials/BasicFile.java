package materials;

import java.io.Serializable;

/**
 * Zakladny class druhej hierarchie suborov
 * @author Jan
 *
 */

@SuppressWarnings("serial")
public abstract class BasicFile implements Serializable {
	int points;
	int number;
	
	/**
	 * konstruktor
	 * @param addPoints
	 * @param addNumber
	 */
	public BasicFile(int addPoints, int addNumber) {
		points = addPoints;
		number = addNumber;
	}
	
}
