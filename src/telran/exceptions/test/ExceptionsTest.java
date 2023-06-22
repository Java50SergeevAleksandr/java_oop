package telran.exceptions.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.exceptions.BallBrokenFloor;

class ExceptionsTest {

	@Test
	void testException() {
		int res = 0;
		try {
			res = itThrowsCheckedException(10000);

			System.out.println("everything ok");
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			res = 100;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			res = 200;
		}
		assertEquals(100, res);

	}

	private int itThrowsCheckedException(int number) throws Exception {
		if (number < 0) {
			throw new Exception("just test checked exception");
		}
		if (number > 1000) {
			throw new RuntimeException("number cannot be greater than 1000");
		}
		return number * 2;

	}

	@Test
	void ballBrokenFloorTest() {
		BallBrokenFloor bbf = new BallBrokenFloor(200);
		assertEquals(bbf.getFloor(), getMinFloor(bbf));
	}

	private int getMinFloor(BallBrokenFloor bbf) {
		int res = 0;
		int left = 0;
		int right = bbf.getnFloors();

		boolean isFind = false;

		while (!isFind) {
			int middle = (right + left) / 2;

			if (tryBall(middle, bbf)) {
				right = middle;
			} else {
				left = middle + 1;
			}

			if (left == middle) {
				isFind = true;
				res = left;
				System.out.println("Random floor was " + res);
			}

		}

		return res;
	}

	private boolean tryBall(int middle, BallBrokenFloor bbf) {
		boolean isBroken = false;
		try {
			bbf.broken(middle);
		} catch (Exception e) {
			isBroken = true;
		}
		return isBroken;
	}

}