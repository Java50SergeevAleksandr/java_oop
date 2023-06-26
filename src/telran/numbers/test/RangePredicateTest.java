package telran.numbers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.module.FindException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.numbers.RangePredicate;

class RangePredicateTest {
	RangePredicate range;
	Predicate<Integer> predicateOdd = num -> num % 2 != 0;
	Predicate<Integer> predicateEven = num -> num % 2 == 0;

	@BeforeEach
	void setUp() {
		range = new RangePredicate(1, 5);
	}

	@Test
	void fullRangeTest() {
		int[] expected = { 1, 2, 3, 4 };
		assertArrayEquals(expected, range.toArray());
	}

	@Test
	void iteratorTest() {
		range.setPredicate(predicateOdd);
		Iterator<Integer> itOdd = range.iterator();
		range.setPredicate(predicateEven);
		Iterator<Integer> itEven = range.iterator();
		while (itOdd.hasNext()) {
			assertTrue(itOdd.next() % 2 != 0);
		}
		while (itEven.hasNext()) {
			assertTrue(itEven.next() % 2 == 0);
		}
		assertThrowsExactly(NoSuchElementException.class, () -> itOdd.next());
		assertThrowsExactly(NoSuchElementException.class, () -> itEven.next());

	}

	@Test
	void oddToArrayTest() {
		int[] expected = { 1, 3 };
		range.setPredicate(predicateOdd);
		assertArrayEquals(expected, range.toArray());
	}

	@Test
	void evenToArrayTest() {
		int[] expected = { 2, 4 };
		range.setPredicate(predicateEven);
		assertArrayEquals(expected, range.toArray());
	}

	@Test
	void abnormalConstructingTest() {
		assertThrowsExactly(IllegalArgumentException.class, () -> new RangePredicate(5, 1));
	}
	
	@Test
	void abnormalRangeTest() {
		range = new RangePredicate(1, 2);
		range.setPredicate(predicateEven);
		assertThrowsExactly(FindException.class, () -> range.toArray());
	}
}
