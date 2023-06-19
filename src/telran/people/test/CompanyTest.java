package telran.people.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.people.Company;
import telran.people.DepartmentPredicate;
import telran.people.Employee;

class CompanyTest {
	private static final int ID1 = 123;
	private static final int BIRTH_YEAR1 = 1999;
	private static final String DEPARTMENT1 = "department1";
	private static final int SALARY1 = 10000;
	private static final int BIRTH_YEAR2 = 1980;
	private static final int BIRTH_YEAR3 = 2000;
	private static final int BIRTH_YEAR4 = 1985;
	private static final String DEPARTMENT2 = "department2";
	private static final int SALARY2 = 5000;
	private static final int SALARY3 = 15000;
	private static final int SALARY4 = 12000;
	private static final int ID2 = 124;
	private static final int ID3 = 125;
	private static final int ID4 = 126;
	private static final int ID5 = 127;

	Employee empl1 = new Employee(ID1, "name1", BIRTH_YEAR1, DEPARTMENT1, SALARY1);
	Employee empl2 = new Employee(ID2, "name2", BIRTH_YEAR2, DEPARTMENT1, SALARY2);
	Employee empl3 = new Employee(ID3, "name3", BIRTH_YEAR3, DEPARTMENT2, SALARY3);
	Employee empl4 = new Employee(ID4, "name4", BIRTH_YEAR4, DEPARTMENT2, SALARY4);
	Employee empl5 = new Employee(ID5, "name5", BIRTH_YEAR4, DEPARTMENT2, SALARY4);
	Employee[] employees = { empl1, empl2, empl3, empl4 };
	Company company;

	@BeforeEach
	void setUp() {
		company = new Company(employees);
	}

	@Test
	void testGetAllEmployees() {
		Employee[] expected = { new Employee(ID1, "name1", BIRTH_YEAR1, DEPARTMENT1, SALARY1),
				new Employee(ID2, "name2", BIRTH_YEAR2, DEPARTMENT1, SALARY2),
				new Employee(ID3, "name3", BIRTH_YEAR3, DEPARTMENT2, SALARY3),
				new Employee(ID4, "name4", BIRTH_YEAR4, DEPARTMENT2, SALARY4) };
		assertArrayEquals(expected, company.getAllEmployees());
	}

	@Test
	void testGetEmployeesByAge() {
		Employee[] expected1 = { empl3, empl1 };
		Employee[] expected2 = { empl3, empl1, empl4, empl2 };
		Employee[] expected3 = {};
		assertArrayEquals(expected1, company.getEmployeesByAge(1995, 2001));
		assertArrayEquals(expected2, company.getEmployeesByAge(1970, 2000));
		assertArrayEquals(expected3, company.getEmployeesByAge(2001, 2003));

	}

	@Test
	void testGetEmployeesBySalary() {
		Employee[] expected1 = { empl2, empl1 };
		Employee[] expected2 = { empl2, empl1, empl4, empl3 };
		Employee[] expected3 = {};
		assertArrayEquals(expected1, company.getEmployeesBySalary(5000, 11000));
		assertArrayEquals(expected2, company.getEmployeesBySalary(4500, 15000));
		assertArrayEquals(expected3, company.getEmployeesBySalary(16000, 20000));

	}

	@Test
	void testGetEmployeesByDepartment() {
		Employee[] expected1 = { empl1, empl2 };
		Employee[] expected2 = { empl3, empl4 };
		Employee[] expected3 = {};
		assertArrayEquals(expected1, company.getEmployeesByDepartment(DEPARTMENT1));
		assertArrayEquals(expected2, company.getEmployeesByDepartment(DEPARTMENT2));
		assertArrayEquals(expected3, company.getEmployeesByDepartment("dep"));

	}

	@Test
	void testAddEmployee() {
		Employee[] expected = { empl1, empl2, empl3, empl4, empl5 };
		assertFalse(company.addEmployee(empl1));
		assertTrue(company.addEmployee(empl5));
		assertArrayEquals(expected, company.getAllEmployees());

	}

	@Test
	void testGetEmployee() {
		assertEquals(empl1, company.getEmployee(ID1));
		assertNull(company.getEmployee(ID5));
	}

	@Test
	void testRemoveEmployeesIf() {
		Employee[] expected1 = { empl1, empl2 };
		Employee[] expected2 = {};
		assertFalse(company.removeEmployeesIf(new DepartmentPredicate("dep")));
		assertArrayEquals(employees, company.getAllEmployees());
		assertTrue(company.removeEmployeesIf(new DepartmentPredicate(DEPARTMENT2)));
		assertArrayEquals(expected1, company.getAllEmployees());
		assertTrue(company.removeEmployeesIf(new DepartmentPredicate(DEPARTMENT1)));
		assertArrayEquals(expected2, company.getAllEmployees());

	}

}