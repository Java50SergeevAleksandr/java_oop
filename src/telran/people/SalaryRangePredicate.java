package telran.people;

import java.util.function.Predicate;

public class SalaryRangePredicate implements Predicate<Employee> {
	private int salaryFrom;
	private int salaryTo;

	public SalaryRangePredicate(int yearFrom, int yearTo) {
		this.salaryFrom = yearFrom;
		this.salaryTo = yearTo;
	}

	@Override
	public boolean test(Employee emp) {
		return salaryFrom <= emp.getSalary() && emp.getSalary() <= salaryTo;
	}

}
