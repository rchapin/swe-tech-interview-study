import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class OverlapSubsequence {

    public static int overlapSubsequence(String a, String b, int aIdx, int bIdx, Map<String, Integer> memo) {
        String key = String.format("%d,%d", aIdx, bIdx);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (aIdx >= a.length() || bIdx >= b.length()) {
            memo.put(key, 0);
            return 0;
        }

        int max = Integer.MIN_VALUE;
        if (a.charAt(aIdx) == b.charAt(bIdx)) {
            int retVal = 1 + overlapSubsequence(a, b, aIdx + 1, bIdx + 1, memo); 
            memo.put(key, retVal);
            return retVal;
        } else {
            max = Math.max(
                overlapSubsequence(a, b, aIdx + 1, bIdx, memo),
                overlapSubsequence(a, b, aIdx, bIdx + 1, memo)
            );
        }

        memo.put(key, max);
        return max;
    }

    public static void main(String[] args) {
        System.out.println("Press CTRL+C to exit");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(
                "Enter two strings separated by a space to find the count of the longest overlapping subsequence");
            String input = scanner.nextLine();
            String[] tokens = input.split("\\s");
            System.out.println(overlapSubsequence(tokens[0], tokens[1], 0, 0, new HashMap<String, Integer>()));
        }
    }
}
