package telran.people;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class Company {
	Employee[] employees;

	public Company(Employee[] emplArr) {
		employees = emplArr;
	}

	public Employee[] getAllEmployees() {
		Employee[] res = Arrays.copyOf(employees, employees.length);
		Arrays.sort(res);
		return res;
	}

	public Employee[] getEmployeesBy(Predicate<Employee> predicate, Comparator<Employee> comp) {
		Employee[] res = new Employee[employees.length];
		int index = 0;
		for (int i = 0; i < employees.length; i++) {
			if (predicate.test(employees[i])) {
				res[index++] = employees[i];
			}
		}
		res = Arrays.copyOf(res, index);
		Arrays.sort(res, comp);
		return res;
	}

	public Employee[] getEmployeesByAge(int yearFrom, int yearTo) {
		return getEmployeesBy(new YearRangePredicate(yearFrom, yearTo), new YearAscendingComparator());
	}

	public Employee[] getEmployeesBySalary(int salaryFrom, int salaryTo) {
		return getEmployeesBy(new SalaryRangePredicate(salaryFrom, salaryTo), new SalaryAscendingComparator());
	}

	public Employee[] getEmployeesByDepartment(String department) {
		return getEmployeesBy(new DepartmentPredicate(department), Comparator.naturalOrder());
	}

	public boolean addEmployee(Employee empl) {
		Employee employee = getEmployee(empl.getId());
		boolean res = false;
		if (employee == null) {
			res = true;
			employees = Arrays.copyOf(employees, employees.length + 1);
			employees[employees.length - 1] = empl;
		}
		return res;
	}

	public boolean removeEmployeesIf(Predicate<Employee> predicate) {
		int oldSize = employees.length;
		Employee[] tmp = new Employee[oldSize];
		int index = 0;
		for (int i = 0; i < oldSize; i++) {
			if (!predicate.test(employees[i])) {
				tmp[index++] = employees[i];
			}
		}
		employees = Arrays.copyOf(tmp, index);
		return oldSize > employees.length;
	}

	public Employee getEmployee(int id) {
		for (int i = 0; i < employees.length; i++) {
			if (id == employees[i].getId()) {
				return employees[i];
			}
		}

		return null;

	}
}
