package telran.numbers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Range implements Iterable<Integer> {
	private int minInclusive;
	private int maxExclusive;
	private int[] removedNumbers = new int[0];

	private class RangeIterator implements Iterator<Integer> {
		int current = containsInRemoved(minInclusive) ? getCurrent(minInclusive) : minInclusive;
		int previous = 0; // initial value doesn't matter
		boolean flNext = false;

		@Override
		public boolean hasNext() {

			return current < maxExclusive;
		}

		private int getCurrent(int current) {
			current++;
			while (current < maxExclusive && containsInRemoved(current)) {
				current++;
			}
			return current;
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			int res = current;
			previous = current;
			current = getCurrent(current);
			flNext = true;
			return res;
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			flNext = false;
			addRemoved(previous);

		}

	}

	public Range(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("min must be less than max");
		}
		minInclusive = min;
		maxExclusive = max;
	}

	public void addRemoved(int number) {
		removedNumbers = Arrays.copyOf(removedNumbers, removedNumbers.length + 1);
		removedNumbers[removedNumbers.length - 1] = number;

	}

	public boolean containsInRemoved(int number) {
		boolean res = false;
		int index = 0;
		while (index < removedNumbers.length && !res) {
			if (removedNumbers[index] == number) {
				res = true;
			}
			index++;

		}
		return res;
	}

	public int length() {
		return maxExclusive - minInclusive - removedNumbers.length;
	}

	public int[] toArray() {
		int[] res = new int[length()];
		int index = 0;
		for (int num : this) {
			res[index++] = num;
		}
		return res;
	}

	@Override
	public Iterator<Integer> iterator() {

		return new RangeIterator();
	}

	public boolean removeIf(Predicate<Integer> predicate) {
		int oldLength = length();
		Iterator<Integer> it = iterator();
		while (it.hasNext()) {
			int num = it.next();
			if (predicate.test(num)) {
				it.remove();
			}
		}
		return oldLength > length();
	}

}