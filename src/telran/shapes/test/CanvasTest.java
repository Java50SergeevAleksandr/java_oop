package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.shapes.Canvas;
import telran.shapes.Rectangle;
import telran.shapes.Shape;
import telran.shapes.Square;

class CanvasTest {
	Square sq1 = new Square(2);
	Square sq2 = new Square(10);
	Square sq3 = new Square(5);
	Rectangle re1 = new Rectangle(10, 2);
	Rectangle re2 = new Rectangle(10, 5);
	Rectangle re3 = new Rectangle(10, 6);
	Canvas ca1;
	Canvas ca2;
	Canvas ca3;
	Canvas test;

	@BeforeEach
	void setUp() {
		ca1 = new Canvas();
		ca2 = new Canvas();
		ca3 = new Canvas();
		test = new Canvas();
	}

	@Test
	void testPerimetr() {
		test.addShape(sq1);
		assertEquals(8, test.perimeter());
		test.addShape(sq2);
		assertEquals(48, test.perimeter());
		test.addShape(re1);
		assertEquals(72, test.perimeter());
		ca1.addShape(sq2);
		test.addShape(ca1);
		assertEquals(112, test.perimeter());

	}

	@Test
	void testSquare() {
		test.addShape(sq1);
		assertEquals(4, test.square());
		test.addShape(sq2);
		assertEquals(104, test.square());
		test.addShape(re1);
		assertEquals(124, test.square());
		ca2.addShape(sq2);
		test.addShape(ca2);
		assertEquals(224, test.square());
	}

	@Test
	void testVoidCanvas() {
		assertEquals(0, test.perimeter());
		assertEquals(0, test.square());
	}

	@Test
	void testRemoveNestedCanvas() {
		test.addShape(ca1);
		test.addShape(ca2);
		test.addShape(ca3);
		test.addShape(sq2);
		ca1.addShape(sq2);
		ca2.addShape(sq2);
		ca3.addShape(sq2);
		

		assertEquals(400, test.square());
		assertTrue(test.removeNestedCanvases());
		assertFalse(test.removeNestedCanvases());
		assertEquals(100, test.square());
	}
	
	@Test
	void removeIteratorTest() {
		test.addShape(sq2);
		Iterator<Shape> it = test.iterator();
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
		it.next();
		it.remove();
		assertEquals(0, test.perimeter());
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());

	}
	@Test
	void testRemoveIf() {
		test.addShape(sq1);
		test.addShape(sq2);
		test.addShape(sq3);
		test.addShape(re1);
		test.addShape(re2);
		test.addShape(re3);
		

		assertEquals(259, test.square());
		assertTrue(test.removeIf(shape -> shape.square() > 25));
		assertFalse(test.removeIf(shape -> shape.square() > 25));
		assertEquals(49, test.square());
	}
}
