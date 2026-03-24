import java.util.Scanner;

public class IntPalindrome {

    public static int getMsbIdx(int x) {
        int y = x;
        int retVal = 0;
        // As long as y, a copy of the number that we are examining, is
        // greater than 0, we have not yet shifted off the most significant,
        // or first, bit in the value.  Continue shifting until we know the
        // index of that MSB.
        while (y > 0) {
            y = y >> 1;
            retVal++;
        }
        return retVal;
    }

    public static boolean isBitSet(int x, int idx) {
        // Dynamically build a bitmask based on the index of the bit, assuming
        // that we are writing out the bits as follows
        // 8 = 1000
        // Such that index 4 is the bit that is set.
        int mask = 1 << (idx - 1);

        // Use the mask along with an & operation to see if that bit is set.
        // If so, our resulting value will be greater than 0.
        int y = x & mask;
        if (y > 0) {
            return true;
        }
        return false;
    }

    public static boolean isIntPalindrome(int input) {
        int msbIdx = getMsbIdx(input);

        // Now we iteratively compare each end of the remaining bits, from the
        // msbIdx to the first element (1) each time checking that each bit is
        // the same value.
        for (int left = msbIdx, right = 1; right < left; left--, right++) {
            if (isBitSet(input, left) != isBitSet(input, right)) {
                // We do not have a palendrome
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);        
        System.out.println("Press CTRL+C to exit");
        while (true) {
            System.out.printf("Enter an integer to check whether or not it is a bit palendrome: ");
            String input = scanner.nextLine();
            int i = Integer.parseInt(input);
            System.out.printf("int %d is a bit palendrome=%b%n", i, isIntPalindrome(i));
        } 
    }
}
