package telran.points;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class Space {
	private Point[] points;

	public Space(Point[] points) {
		this.points = Arrays.copyOf(points, points.length);
	}

	public void addPoint(Point point) {
		points = Arrays.copyOf(points, points.length + 1);
		points[points.length - 1] = point;
	}

	public Point[] getAllPoints(Comparator<Point> comp) {
		Point[] res = Arrays.copyOf(points, points.length);
		Arrays.sort(res, comp);
		return res;
	}

	public Point[] getPointsByPredicate(Predicate<Point> predicate, Comparator<Point> comp) {
		Point[] res = new Point[points.length];
		int index = 0;
		for (int i = 0; i < points.length; i++) {
			if (predicate.test(points[i])) {
				res[index++] = points[i];
			}
		}
		res = Arrays.copyOf(res, index);
		Arrays.sort(res, comp);
		return res;
	}

	public boolean removePointsByPredicate(Predicate<Point> predicate) {
		int oldSize = points.length;
		Point[] tmp = new Point[oldSize];
		int index = 0;
		for (int i = 0; i < oldSize; i++) {
			if (!predicate.test(points[i])) {
				tmp[index++] = points[i];
			}
		}
		points = Arrays.copyOf(tmp, index);
		return oldSize > points.length;

	}

}