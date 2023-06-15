package telran.people;

import java.util.Comparator;

public class YearAscendingComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		return o2.getBirthYear() - o1.getBirthYear();
	}

}
