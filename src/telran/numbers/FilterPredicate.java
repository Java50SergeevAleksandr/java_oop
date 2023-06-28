package telran.numbers;

import java.lang.module.FindException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class FilterPredicate implements Iterable<Integer> {
	int minInclusive;
	int maxExclusive;
	Predicate<Integer> predicate = num -> true;

	public FilterPredicate(int minInclusive, int maxExclusive) {
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
		int[] res = new int[maxExclusive - minInclusive];
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
		int next;
		Iterator<Integer> it;
		Iterator<Integer> helper;
		Predicate<Integer> innerPredicate;
		Range range;

		public RangePredicateIterator(Predicate<Integer> predicate) {
			innerPredicate = predicate;
			range = new Range(minInclusive, maxExclusive);
			it = range.iterator();
			helper = range.iterator();
			next = getNext();
			next = getNext();
		}

		private int getNext() {
			boolean isNotFind = true;
			while (isNotFind && it.hasNext()) {
				next = it.next();
				if (innerPredicate.test(current)) {
					isNotFind = false;
				}

			}
			return next;

		}
		
		private void getCur() {
			boolean isNotFind = true;
			while (isNotFind && it.hasNext()) {
				current = it.next();
				if (innerPredicate.test(current)) {
					isNotFind = false;
				}

			}

		}

		@Override
		public boolean hasNext() {

			return it.hasNext();
		}

		@Override
		public Integer next() {

			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			getNext();
			int res = current;
			
			return res;
		}
	}
}