import java.util.Map;
import java.util.HashMap;
import java.math.BigInteger;
import java.util.Scanner;

public class Fibonacci {

    public static final BigInteger ONE = BigInteger.valueOf(1L);
    public static final BigInteger TWO = BigInteger.valueOf(2L);
    public static final BigInteger THREE = BigInteger.valueOf(3L);

    public static BigInteger fib(BigInteger n, Map<BigInteger, BigInteger> memo) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        if (n.compareTo(THREE) <= 0) {
            return BigInteger.valueOf(1L);
        }
        BigInteger minus1 = fib(n.subtract(ONE), memo);
        BigInteger minus2 = fib(n.subtract(TWO), memo);
        BigInteger retVal = minus1.add(minus2);
        memo.put(n, retVal);
        return retVal;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press CTRL+C to exit");
        while (true) {
            System.out.printf("Enter a fibonnaci number to calculate: ");
            int input = scanner.nextInt();
            System.out.println(fib(new BigInteger("" + input), new HashMap<BigInteger, BigInteger>()));
        } 
    }
}
