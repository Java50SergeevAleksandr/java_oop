package telran.numbers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.numbers.Range;

class RangeTest {
	Range range;
	Predicate<Integer> evenPredicate = num -> num % 2 == 0;

	@BeforeEach
	void setUp() {
		range = new Range(1, 5);
	}

	@Test
	void abnormalConstructingTest() {
		assertThrowsExactly(IllegalArgumentException.class, () -> new Range(5, 1));
	}

	@Test
	void lengthTest() {
		assertEquals(4, range.length());
	}

	@Test
	void toArrayTest() {
		int[] expected = { 1, 2, 3, 4 };
		assertArrayEquals(expected, range.toArray());
	}

	@Test
	void iteratorTest() {
		int current = 1;
		Iterator<Integer> it = range.iterator();
		while (it.hasNext()) {
			assertEquals(current, it.next());
			current++;
		}
		assertEquals(5, current);
		assertThrowsExactly(NoSuchElementException.class, () -> it.next());
	}

	@Test
	void removeIf() {
		int[] expected = { 1, 3 };
		assertTrue(range.removeIf(evenPredicate));
		assertFalse(range.removeIf(evenPredicate));
		assertArrayEquals(expected, range.toArray());
	}

	@Test
	void removeIfAll() {
		int[] expected = {};
		assertTrue(range.removeIf(num -> true));
		assertEquals(0, range.length());
		assertArrayEquals(expected, range.toArray());
	}

	@Test
	void removeIteratorTest() {
		int[] expected = { 2, 3, 4 };
		Iterator<Integer> it = range.iterator();
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
		it.next();
		it.remove();
		assertArrayEquals(expected, range.toArray());
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());

	}

}