import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


// Test data
// 7 [2, 4] = null
// 7 [2, 3, 5] = [5, 2]
// 7 [5, 3, 4, 7] = [7]
// 20 [2, 4] = [4, 4, 4, 4, 4]
// 30 [9, 1] = [1, 1, 1, 9, 9, 9]
// 30 [1, 9] = [9, 9, 9, 1, 1, 1]
// 8 [2, 3, 5] = [5, 3]
public class BestSum {

    public static List<Integer> bestSum(int target, int[] numbers, Map<Integer, List<Integer>>memo) {
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        if (target == 0) {
            return new ArrayList<Integer>();
        }
        if (target < 0) {
            return null;
        }

        List<Integer> best = null;
        for (int i : numbers) {
            int newTarget = target - i;
            List<Integer> result = bestSum(newTarget, numbers, memo);
            if (result != null) {
                List<Integer> combination = new ArrayList<>();
                combination.add(i);
                combination.addAll(result);

                // Now, figure out if we have already seen a result with
                // fewer number of elements, or if this is now the best option.
                if (best == null || combination.size() < best.size()) {
                    best = combination;
                }
            }
        }
        memo.put(target, best);
        return best;
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
            List<Integer> result = bestSum(target, numbers, new HashMap<Integer, List<Integer>>());
            String output = result != null ? result.toString() : "null";
            List<Integer> numbersList = new ArrayList<>();
            for (int i : numbers) {
                numbersList.add(i);
            }
            System.out.printf("%d %s = %s%n", target, numbersList.toString(), output);
        }
    }
}


