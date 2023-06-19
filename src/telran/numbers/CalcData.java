package telran.numbers;

public class CalcData {
	double op1;
	double op2;
	char operation; // '*', '-'

	public CalcData(double op1, double op2, char operation) {
		this.op1 = op1;
		this.op2 = op2;
		this.operation = operation;
	}

	public double getOp1() {
		return op1;
	}

	public double getOp2() {
		return op2;
	}

	public char getOperation() {
		return operation;
	}

}
