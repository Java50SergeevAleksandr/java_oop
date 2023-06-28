package telran.shapes;

public class Rectangle implements Shape{

private int height;
 private int width;
 public Rectangle(int height, int width) {
		this.height = height;
		this.width = width;
	}
@Override
public int perimeter() {

	return 2 * height + 2 * width;
}
@Override
public int square() {

	return height * width;
}

}