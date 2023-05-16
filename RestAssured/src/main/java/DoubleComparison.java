
import org.apache.commons.math3.util.Precision;

public class DoubleComparison {

	public static void main(String[] args) {
		double num1 = 10.002;
		double num2 = 10.001;
		double epsilon = 0.001;
//		System.out.println(Precision.equals(num1, num2));

		if (Precision.equalsWithRelativeTolerance(num1, num2, epsilon)) {
			System.out.println("The two decimal numbers are equal with the specified precision.");
		} else if (num1 < num2) {
			System.out.println(
					"The first decimal number is less than the second decimal number with the specified precision.");
		} else {
			System.out.println(
					"The first decimal number is greater than the second decimal number with the specified precision.");
		}

//		if (Precision.equals(num1, num2, epsilon)) {
//			System.out.println("The two decimal numbers are equal with the specified precision.");
//		} else if (num1 < num2) {
//			System.out.println("The first decimal number is less than the second decimal number with the specified precision.");
//		} else {
//			System.out.println("The first decimal number is greater than the second decimal number with the specified precision.");
//		}

	}

}
