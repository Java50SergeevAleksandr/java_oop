package telran.shapes;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Canvas implements Shape, Iterable<Shape> {
	private Shape[] shapes = new Shape[0];

	@Override
	public int perimeter() {
		// sum of perimeter values for all shapes in this canvas
		int res = 0;
//		forEach(shape -> res += shape.perimeter());
		for (Shape shape : this) {
			res += shape.perimeter();
		}

		return res;
	}

	@Override
	public int square() {
		// sum of square values for all shapes in this canvas
		int res = 0;
		for (Shape shape : this) {
			res += shape.square();
		}

		return res;
	}

	public void addShape(Shape shape) {
		shapes = Arrays.copyOf(shapes, shapes.length + 1);
		shapes[shapes.length - 1] = shape;
	}

	public boolean removeIf(Predicate<Shape> predicate) {
		int oldLenght = shapes.length;
		Iterator<Shape> it = iterator();

		while (it.hasNext()) {
			Shape sp = it.next();

			if (predicate.test(sp)) {
				it.remove();
			}

		}

		return oldLenght > shapes.length;
	}

	public boolean removeNestedCanvases() {
		return removeIf(shape -> shape instanceof Canvas); // this.getClass();
	}

	@Override
	public Iterator<Shape> iterator() {

		return new CanvasIterator();
	}

	private class CanvasIterator implements Iterator<Shape> {
		int current = 0;
		boolean wasNext = false;

		@Override
		public boolean hasNext() {
			return current < shapes.length;
		}

		@Override
		public Shape next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			wasNext = true;
			return shapes[current++];
		}

		@Override
		public void remove() {
			if (!wasNext) {
				throw new IllegalStateException();
			}
			wasNext = false;
			Shape[] tmp = new Shape[shapes.length - 1];
			current--;
			if (tmp.length != 0) {
				System.arraycopy(shapes, 0, tmp, 0, current);
				System.arraycopy(shapes, current + 1, tmp, current, tmp.length - current);
			}

			shapes = Arrays.copyOf(tmp, tmp.length);
		}

	}

}
