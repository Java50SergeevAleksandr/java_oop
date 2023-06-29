package telran.numbers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.module.FindException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.numbers.FilteredIterator;
import telran.numbers.Range;
import telran.numbers.RangePredicate;

class FilteredIteratorTest {
	FilteredIterator<Integer> testIterator;
	Predicate<Integer> predicateAll = num -> true;
	Predicate<Integer> predicateNone = num -> false;
	Predicate<Integer> predicateOdd = num -> num % 2 != 0;
	Predicate<Integer> predicateEven = num -> num % 2 == 0;
	Range range;
	Iterator<Integer> it;

	@BeforeEach
	void setUp() {
		range = new Range(1, 5);
		it = range.iterator();
	}

	@Test
	void allValuesAccepted() {
		testIterator = new FilteredIterator<Integer>(it, predicateAll);
		int[] expected = { 1, 2, 3, 4 };
		int[] arr = new int[4];
		int index = 0;

		while (testIterator.hasNext()) {
			arr[index++] = testIterator.next();
		}
		assertArrayEquals(expected, arr);

	}

	@Test
	void neverValuesAccepted() {
		int[] expected = {};
		int[] arr = new int[4];
		int index = 0;
		testIterator = new FilteredIterator<Integer>(it, predicateNone);
		while (testIterator.hasNext()) {
			arr[index++] = testIterator.next();
		}
		assertEquals(0, arr[0]);
		assertEquals(0, arr[1]);
		arr = Arrays.copyOf(arr, index);
		assertArrayEquals(expected, arr);
	}

	@Test
	void firstAcceptedLastNotPassed() {
		range = new Range(2, 6);
		it = range.iterator();
		int[] expected = { 2, 4 };
		int[] arr = new int[2];
		int index = 0;
		testIterator = new FilteredIterator<Integer>(it, predicateEven);
		while (testIterator.hasNext()) {
			arr[index++] = testIterator.next();
		}
		assertArrayEquals(expected, arr);
	}

	@Test
	void firstAcceptedLastNotPassed2() {
		range = new Range(2, 4);
		it = range.iterator();
		int[] expected = { 2 };
		int[] arr = new int[1];
		int index = 0;
		testIterator = new FilteredIterator<Integer>(it, predicateEven);
		while (testIterator.hasNext()) {
			arr[index++] = testIterator.next();
		}
		assertArrayEquals(expected, arr);

	}

	@Test
	void firstNotPassedLastPassed() {
		range = new Range(3, 5);
		it = range.iterator();
		int[] expected2 = { 4 };
		int[] arr = new int[1];
		int index = 0;
		testIterator = new FilteredIterator<Integer>(it, predicateEven);
		while (testIterator.hasNext()) {
			arr[index++] = testIterator.next();
		}
		assertArrayEquals(expected2, arr);

	}

	@Test
	void noValuesFromBeginning() {
		range = new Range(2, 2);
		it = range.iterator();
		int[] expected = {};
		int[] arr = new int[1];
		int index = 0;
		testIterator = new FilteredIterator<Integer>(it, predicateAll);
		while (testIterator.hasNext()) {
			arr[index++] = testIterator.next();
		}
		arr = Arrays.copyOf(arr, index);
		assertArrayEquals(expected, arr);

	}
}
