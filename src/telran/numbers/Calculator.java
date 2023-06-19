package telran.numbers;
import java.util.function.BinaryOperator;

public class Calculator {
	static DoubleBinaryOperator[] operators = {
			(a, b) -> a + b,
			(a, b) -> a - b,
			(a, b) -> a * b,
			(a, b) -> a / b 
	};

	static char[] operations = { '+', '-', '*', '/' };	

	static public double calculate(CalcData cd) {
		int index = -1;
		for (int i = 0; i < operations.length && index == -1; i++) {
			if (cd.operation == operations[i]) {
				index = i;
			}
		}

		return operators[index].apply(cd.op1, cd.op2);
	}
}

interface DoubleBinaryOperator extends BinaryOperator<Double> {

}