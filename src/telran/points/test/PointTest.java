package telran.points.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.points.Point;

class PointTest {
	Point[] points = { new Point(3, 4), new Point(3, 3), new Point(1, 1), new Point(4, 5) };

	@Test
	void test() {
		Arrays.sort(points);
		Point[] expected = { new Point(1, 1), new Point(3, 3), new Point(3, 4), new Point(4, 5) };
		assertArrayEquals(expected, points);
	}

}