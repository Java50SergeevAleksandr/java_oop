package telran.numbers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class RangePredicate implements Iterable<Integer> {
	int minInclusive;
	int maxExclusive;
	Predicate<Integer> predicate = e -> true;

	public RangePredicate(int minInclusive, int maxExclusive) {
		if (minInclusive >= maxExclusive) {
			throw new IllegalArgumentException("min must be less than max");
		}
		this.minInclusive = minInclusive;
		this.maxExclusive = maxExclusive;
	}

	public Predicate<Integer> getPredicate() {
		return predicate;
	}

	public void setPredicate(Predicate<Integer> predicate) {
		this.predicate = predicate;
	}

	public int[] toArray() {
		int[] res = new int[maxExclusive];
		int index = 0;
		for (int num : this) {
			res[index++] = num;
		}
		return Arrays.copyOf(res, index);
	}

	@Override
	public Iterator<Integer> iterator() {
		return new RangePredicateIterator(predicate);
	}

	private class RangePredicateIterator implements Iterator<Integer> {

		int current;
		Predicate<Integer> innerPredicate;

		RangePredicateIterator(Predicate<Integer> predicate) {

			innerPredicate = predicate;
			current = innerPredicate.test(minInclusive) ? minInclusive : getCurrent(minInclusive);
		}

		@Override
		public boolean hasNext() {

			return current < maxExclusive;
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			int res = current;
			current = getCurrent(current);
			return res;
		}

		private int getCurrent(int current) {
			current++;
			while (current < maxExclusive && !innerPredicate.test(current)) {
				current++;
			}
			return current;
		}
	}
}