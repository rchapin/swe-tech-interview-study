import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

// Test data
// 7 [2, 3] = true
// 7 [5, 3, 4, 7] = true
// 7 [2, 4]= false
// 8 [2, 3, 5] = true
// 300 [7, 14] = false
public class CanSum {

    public static boolean canSum(int target, int[] numbers, Map<Integer, Boolean> memo) {
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        if (target < 0) {
            return false;
        }
        if (target == 0) {
            return true;
        }
        
        boolean retVal = false;
        for (int i : numbers) {
            retVal = canSum(target - i, numbers, memo);
            if (retVal) {
                memo.put(target, retVal);
                return true;
            }
        }

        memo.put(target, retVal);
        return false;
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
            System.out.printf("Result=%b%n", canSum(target, numbers, new HashMap<Integer, Boolean>())); 
        } 
    }
}


