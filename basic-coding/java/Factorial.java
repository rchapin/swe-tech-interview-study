import java.math.BigInteger;
import java.util.Scanner;

public class Factorial {

    public static BigInteger factorial(int n) {
        BigInteger num = new BigInteger("" + n);
        BigInteger nextBigInt = null;
        int next = n;
        while (next > 1) {
            next--;
            nextBigInt = new BigInteger("" + next);
            num = num.multiply(nextBigInt);
        }
        return num;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press CTRL+C to exit");
        while (true) {
            System.out.printf("Enter a number to calculate the factorial: ");
            int n = scanner.nextInt();
            System.out.println(factorial(n).toString());
        } 
    }
}
