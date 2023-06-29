package telran.numbers;

import java.lang.module.FindException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class FilteredIterator<T> implements Iterator<T> {
	T current;	
	boolean isFind = false;
	Iterator<T> it;	
	Predicate<T> innerPredicate;
	
	
	public FilteredIterator(Iterator<T> srcIterator, Predicate<T> filter) {
			innerPredicate = filter;	
			it = srcIterator;
			findNext();
		}

	

	private void findNext() {
		isFind = false;
		while (!isFind && it.hasNext()) {
			current = it.next();
			if (innerPredicate.test(current)) {
				isFind = true;
			}

		}

	}

	@Override
	public boolean hasNext() {
		
		return isFind;
	}	

	@Override
	public T next() {

		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		
		T res = current;
		findNext();
		return res;
	}
}

