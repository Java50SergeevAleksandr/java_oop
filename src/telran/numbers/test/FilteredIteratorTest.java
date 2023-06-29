package telran.numbers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.module.FindException;
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
		testIterator = new FilteredIterator<Integer>(it, predicateAll);
	}

	@Test
	void allValuesAccepted() {

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

		int[] expected = { 1, 2, 3, 4 };
		int[] arr = new int[4];
		int index = 0;
		testIterator = new FilteredIterator<Integer>(it, predicateNone);
		while (testIterator.hasNext()) {
			arr[index++] = testIterator.next();
		}
		assertEquals(0, arr[0]);

	}
//	@Test
//	void oddToArrayTest() {
//		int[] expected = { 1, 3 };
//		range.setPredicate(predicateOdd);
//		assertArrayEquals(expected, range.toArray());
//	}
//
//	@Test
//	void evenToArrayTest() {
//		int[] expected = { 2, 4 };
//		range.setPredicate(predicateEven);
//		assertArrayEquals(expected, range.toArray());
//	}
//
//	@Test
//	void abnormalConstructingTest() {
//		assertThrowsExactly(IllegalArgumentException.class, () -> new RangePredicate(5, 1));
//	}

//	@Test
//	void abnormalRangeTest() {
//		range = new FilterPredicate(1, 2);
//		range.setPredicate(predicateEven);
//		assertThrowsExactly(FindException.class, () -> range.toArray());
//	}
}
