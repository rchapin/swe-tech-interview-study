import java.util.Scanner;

public class SwapNumsTwoVars {

    public static void swapNums(int a, int b) {
        // If:
        // a = 1000 = 8
        // b = 0100 = 4
        //
        // a will be 1100
        a = a ^ b;

        // a is now 1100
        // b is now 0100
        // b will be 1000 = 8
        b = a ^ b;

        // a is now 1100
        // b is now 1000
        // a will be 0100 = 4
        a = a ^ b;
        System.out.println("a now=" + a + ", b now=" + b);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("CTRL+C to quit");
        while (true) {
            System.out.printf("Enter two positive ints to swap separated by a space: ");
            String in = scanner.nextLine();
            String[] tokens = in.split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            System.out.println("Swapping a=" + a + ", b=" + b);
            swapNums(a, b);
        }
    }
}
