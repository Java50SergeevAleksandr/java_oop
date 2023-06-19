package telran.people.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.people.Employee;


class EmployeeTest {
	Employee[] emp = { new Employee(100, "Bill", 1990, "Alfa", 10000),
			new Employee(1, "BoB", 1980, "Alfa", 12000),			
			new Employee(111, "Jhon", 1970, "Beta", 9000),
			new Employee(1234, "Fil", 1960, "Beta", 19000),
			new Employee(567, "Otto", 1950, "Gamma", 23000), };

	@Test
	void equalsTest() {
		assertTrue(emp[1].equals(emp[1]));
		assertFalse(emp[1].equals(emp[2]));
	}

	@Test
	void compareToTest() {
		Arrays.sort(emp);
		Employee[] expected = { new Employee(1, "BoB", 1980, "Alfa", 12000),
				new Employee(100, "Bill", 1990, "Alfa", 10000),
				new Employee(111, "Jhon", 1970, "Beta", 9000),				
				new Employee(567, "Otto", 1950, "Gamma", 23000),
				new Employee(1234, "Fil", 1960, "Beta", 19000), };
		assertArrayEquals(expected, emp);
	}
}
