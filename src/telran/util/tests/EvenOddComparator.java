package telran.util.tests;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		int res = -1;
		if (isEven(o1)) {
			if (isEven(o2)) {
				res = o1 - o2;
			}
		} else {
			res = 1;
			if (!isEven(o2)) {
				res = o2 - o1;
			}
		}
		return res;
	}

	private boolean isEven(Integer o1) {
		return o1 % 2 == 0;
	}
}
