package telran.people;

import java.util.function.Predicate;

public class DepartmentPredicate implements Predicate<Employee> {

	private String department;

	public DepartmentPredicate(String department) {
		this.department = department;
	}

	@Override
	public boolean test(Employee emp) {
		return emp.getDepartment() == department;
	}

}
