package telran.shapes;

import java.util.Iterator;
import java.util.function.Predicate;

public class Canvas implements Shape, Iterable<Shape> {
	private Shape[] shapes = new Shape[0];

	private class CanvasIterator implements Iterator<Shape> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Shape next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void remove() {
			// TODO
		}

	}

	@Override
	public int perimeter() {
		// TODO Auto-generated method stub
		// sum of perimeter values for all shapes in this canvas
		return 0;
	}

	@Override
	public int square() {
		// TODO Auto-generated method stub
		// sum of square values for all shapes in this canvas
		return 0;
	}

	@Override
	public Iterator<Shape> iterator() {

		return new CanvasIterator();
	}

	public void addShape(Shape shape) {
		// TODO
	}

	public boolean removeIf(Predicate<Shape> predicate) {
		// TODO
		return false;
	}

	public boolean removeNestedCanvases() {
		return removeIf(shape -> shape instanceof Canvas);
	}

}
