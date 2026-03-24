import java.util.Scanner;

public class IsPowerOfTwo {

    public static boolean isPowerOfTwo(int input) {
        // A method whereby we simply AND the input number against the input
        // number - 1.
        // If the input number is 8 it is the following in binary
        //   0000 1000
        // 8 - 1 in binary is
        //   0000 0111
        //
        // The result of the AND of those two values is 0.
        // Any non, power of two number will be a value other than 0.
        if ((input & (input - 1)) == 0) {
            return true;
        }
        return false;
    }

    public static boolean altIsPowerOfTwo(int input) {
        // An alternate method is to take the input number's unary compliment
        // and add one to it.  Then AND the orginal input number and we should
        // get the original input value.
        //
        // If the input number is 8 it is the following in binary
        // 
        // 8      = 0000 1000
        // ~8     = 1111 0111
        // ~8+1   = 1111 1000
        // 
        // 8 & (~8+1) 0000 1000
        //            1111 1000
        //            ---------
        //            0000 1000 , our original input number
        if ((input & (~input+1)) == input) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("CTRL+C to quit");
        while (true) {
            System.out.printf("Enter a number to determine whether it is a power of two: ");
            String in = scanner.nextLine();
            int inNum = Integer.parseInt(in);
            System.out.println(" -- Input number [" + inNum + "] isPowerOfTwo    = " + isPowerOfTwo(inNum));
            System.out.println(" -- Input number [" + inNum + "] altIsPowerOfTwo = " + altIsPowerOfTwo(inNum));
        }
    }
}
