import java.util.Scanner;

public class CanSumTabulated {

    public static boolean[] initTable(int x) {
        boolean[] retVal = new boolean[x];

        // Set the 0th position to true to seed the table with what we know
        // is the value for the base case for this cell in the table.
        retVal[0] = true;
        return retVal;
    }

    public static boolean canSum(int target, int[] numbers) {
        // Initialize a table that is the size of target + 1.
        boolean[] t = initTable(target + 1);
        for (int i = 0; i < t.length; i++) {
            // Only look ahead and mutate values if the current index in the
            // table is true
            if (t[i] == true) {
                for (int number : numbers) {
                    int targetIdx = i + number;
                    if (targetIdx < t.length) {
                        t[targetIdx] = true;
                    }
                }
            }
        }

        return t[target];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press CTRL+C to exit");
        while (true) {
            System.out.printf("Enter x for the target and n number of ints separated by a space for the numbers array: ");
            String in = scanner.nextLine();
            String[] tokens = in.split("\\s+");
            int target = Integer.parseInt(tokens[0]);
            int[] numbers = new int[tokens.length-1];
            for (int i = 1, j = 0; i < tokens.length; i++, j++) {
                numbers[j] = Integer.parseInt(tokens[i]);
            }
            System.out.printf("Result=%b%n", canSum(target, numbers)); 
        } 
    }
}


