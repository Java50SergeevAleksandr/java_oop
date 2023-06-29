package telran.numbers;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {
	private int minInclusive;
	private int maxExclusive;

	private class RangeIterator implements Iterator<Integer> {
		int current = minInclusive;

		@Override
		public boolean hasNext() {

			return current < maxExclusive;
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			int res = current++;
			return res;
		}

	}

	public Range(int min, int max) {
//		if (min >= max) {
//			throw new IllegalArgumentException("min must be less than max");
//		}
		minInclusive = min;
		maxExclusive = max;
	}

	public int length() {
		return maxExclusive - minInclusive;
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

}
