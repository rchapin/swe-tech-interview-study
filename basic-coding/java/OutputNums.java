import java.math.BigInteger;
import java.util.Scanner;

public class OutputNums {

    public static void outputNums(int target, int curr) {
        if (curr >= target) {
            return;
        }
        System.out.println(++curr);
        outputNums(target, curr);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press CTRL+C to exit");
        while (true) {
            System.out.printf("Enter a number: ");
            int n = scanner.nextInt();
            outputNums(n, 0);
        } 
    }
}
