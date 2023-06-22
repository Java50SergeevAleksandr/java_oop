package telran.exceptions;

public class BallBrokenFloor {
	private int floor;
	private int nFloors;

	public int getnFloors() {
		return nFloors;
	}

	public int getFloor() {
		// this method is only for testing
		return floor;
	}

	public void broken(int floor) throws Exception {
		if (floor >= this.floor) {
			throw new Exception();
		}
	}

	public BallBrokenFloor(int nFloors) {
		this.nFloors = nFloors;
		floor = (int) (1 + Math.random() * nFloors); // random number in the range [1, nFloors]
	}

}
