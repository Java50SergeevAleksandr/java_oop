package telran.people;

import java.util.function.Predicate;

public class YearRangePredicate implements Predicate<Employee> {
	private int yearFrom;
	private int yearTo;

	public YearRangePredicate(int yearFrom, int yearTo) {
		this.yearFrom = yearFrom;
		this.yearTo = yearTo;
	}

	@Override
	public boolean test(Employee emp) {
		return yearFrom <= emp.getBirthYear() && emp.getBirthYear() <= yearTo;
	}
}
