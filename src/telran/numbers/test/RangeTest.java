package telran.numbers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import telran.numbers.Range;

class RangeTest {
	Range range = new Range(1, 5);

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

}
