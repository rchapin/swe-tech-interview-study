import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


// Test data
// abc a ab bc c == 2
// abcdef ab abc cd def abcd == 1
// enterapotentpot a p ent enter ot o t == 4
// skateboard bo rd ate t ska sk boar == 0
// rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrg r rr rrr rrrr rrrrr rrrrrr == 0
public class CountConcat {

    public static void printResult(long result, String target, String[] words) {
        List<String> wordsList = new ArrayList<String>(Arrays.asList(words));
        System.out.printf("%s, %s = %d%n", target, wordsList, result);
    }

    public static long countConcat(String target, String[] words, Map<String, Long> memo) {
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        if (target.isEmpty()) {
            return 1L;
        }

        long retVal = 0L;
        for (String word : words) {
            // Only execute the recursive function if the current word is a
            // prefix of the target.
            if (target.indexOf(word) == 0) {
                String suffix = target.substring(word.length(), target.length());
                retVal += countConcat(suffix, words, memo);
            }
        }

        memo.put(target, retVal);
        return retVal;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press CTRL+C to exit");
        while (true) {
            System.out.printf(
                "Enter a target word, followed by a space delimited set " +
                "substrings to determine if we can generate the target " +
                "string with any combination of the word array: ");
            String[] tokens = scanner.nextLine().split("\\s+");
            String target = tokens[0];
            String[] words = Arrays.copyOfRange(tokens, 1, tokens.length);
            printResult(countConcat(target, words, new HashMap<String, Long>()), target, words);
        }
    }
}
