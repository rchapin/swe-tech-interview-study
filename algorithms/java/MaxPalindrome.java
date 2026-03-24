import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class MaxPalindrome {

    public static int maxPalindrome(String s, int start, int end, Map<String, Integer> memo) {
        String key = String.format("%d,%d", start, end);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (start > end) {
            return 0;
        }
        if (start == end) {
            return 1;
        }

        int max = Integer.MIN_VALUE;
        int curr = Integer.MIN_VALUE;
        if (s.charAt(start) == s.charAt(end)) {
            // We will "remove" the matching characters and recursively call
            // the function with the truncated string.
            max = 2 + maxPalindrome(s, start + 1, end - 1, memo);
        } else {
            max = Math.max(
                maxPalindrome(s, start + 1, end, memo),
                maxPalindrome(s, start, end - 1, memo)
            );
        }
        
        memo.put(key, max);
        return max;
    }

    public static void main(String[] args) {
        System.out.println("Press CTRL-C to exit");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("Enter a string to analyze for the max palindromic substring: ");
            String input = scanner.nextLine();
            System.out.println(maxPalindrome(input, 0, input.length() - 1, new HashMap<String, Integer>()));
        }
    }
}
