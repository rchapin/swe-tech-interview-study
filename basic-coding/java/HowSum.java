import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

// Test data
// 7 [2, 3] = [2, 2, 3]
// 7 [5, 3, 4, 7] = [3, 4]
// 7 [2, 4] =  null
// 20 [4, 2] = [4, 4, 4, 4, 4]
// 8 [2, 3, 5] = [2, 2, 2, 2]
// 30 [9, 1] = [1, 1, 1, 9, 9, 9]
// 3000 [7, 14] = null
public class HowSum {

    public static List<Integer> howSum(int target, int[] numbers, Map<Integer, List<Integer>>memo) {
        if (target < 0) {
            return null;
        }
        if (target == 0) {
            return new ArrayList<Integer>();
        }
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        
        List<Integer> retVal = null;
        for (int i : numbers) {
            retVal = howSum(target - i, numbers, memo);
            // If the result is not null, we want to add to the returned list
            // the value that we just used to subtract from our target sum and
            // then return because we have found a path through the tree that
            // gives us a solution to the problem.
            if (retVal != null) {
                retVal.add(i);
                memo.put(target, retVal);
                return retVal;
            }
        }

        memo.put(target, null);
        return null;
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
            List<Integer> result = howSum(target, numbers, new HashMap<Integer, List<Integer>>());
            String output = result != null ? result.toString() : "null";
            System.out.printf("Result=%s%n", output);
        } 
    }
}


