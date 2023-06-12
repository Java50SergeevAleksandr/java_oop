package telran.util.tests;

import java.util.Comparator;

public class DigitsSumComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		return getDigitsSum(o1) - getDigitsSum(o2);
	}

	private int getDigitsSum(Integer number) {
		int sum = 0;
		int digit = 0;
		do {
			digit = Math.abs(number % 10);
			sum += digit;
			number /= 10;

		} while (number != 0);
		return sum;
	}
}
