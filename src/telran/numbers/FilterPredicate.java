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
		int pointerToCurrent = 0;
		
		Predicate<Integer> innerPredicate;
		
		Range range = new Range(minInclusive, maxExclusive);
		int[] set;

		public RangePredicateIterator(Predicate<Integer> predicate) {
			innerPredicate = predicate;
//			set = getArrayByPredicate();
			current = getCurrent();
		}

		private int getCurrent() {
			int res = 0;
			do {
				res = range.iterator().next();
			} while (!innerPredicate.test(res));
			return res;
		}

//		private int[] getArrayByPredicate() {
//			int[] res = new int[range.length()];
//			int index = 0;
//			for (int num : range) {
//				if (innerPredicate == null || innerPredicate.test(num)) {
//					res[index++] = num;
//				}
//			}
//			if (index == 0) {
//				throw new FindException("No elements by predicate");
//			}
//			return Arrays.copyOf(res, index);
//		}

//		@Override
//		public boolean hasNext() {
//			return pointerToCurrent < set.length;
//		}

//		@Override
//		public Integer next() {
//			if (!hasNext()) {
//				throw new NoSuchElementException();
//			}
//			return set[pointerToCurrent++];
//		}
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
			do {
				current = range.iterator().next();
			} while (!innerPredicate.test(current));
			return res;
		}
	}
}