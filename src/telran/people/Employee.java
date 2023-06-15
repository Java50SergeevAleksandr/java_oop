package telran.people;

public class Employee implements Comparable<Employee> {
	private int id;
	private String name;
	private int birthYear;
	private String department;
	private int salary;

	public Employee(int id, String name, int birthname, String depatment, int salary) {
		this.id = id;
		this.name = name;
		this.birthYear = birthname;
		this.department = depatment;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public String getDepartment() {
		return department;
	}

	public int getSalary() {
		return salary;
	}

	public boolean equals(Object emplObj) {
		Employee emp = (Employee) emplObj;
		return id == emp.id;
	}

	@Override
	public int compareTo(Employee empl) {
		return id - empl.id;
	}
	
	

}
