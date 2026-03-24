import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


// Test data
// abcdef ab abc cd def abcd == true
// enterapotentpot a p ent enter ot o t == true
// skateboard bo rd ate t ska sk boar == false
// rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrg r rr rrr rrrr rrrrr rrrrrr == false
public class CanConcat {

    public static void printResult(boolean result, String target, String[] words) {
        List<String> wordsList = new ArrayList<String>(Arrays.asList(words));
        System.out.printf("%s, %s = %b%n", target, wordsList, result);
    }

    public static boolean canConcat(String target, String[] words, HashMap<String, Boolean> memo) {
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        if (target.isEmpty()) {
            return true;
        }

        boolean retVal = false;
        for (String word : words) {
            // Check to see if this word is a prefix of our current target
            if (target.indexOf(word) == 0) {
                // Generate a String that is the remainder of the word minus
                // the prefix.
                String suffix = target.substring(word.length(), target.length());
                retVal = canConcat(suffix, words, memo);
                if (retVal) {
                    memo.put(target, true);
                    return true;
                }
            }
            // If not, continue to
            // Recursively call and check our result.  If it is true, we return
            // that value, otherwise, we keep iterating over the array of words.
        }
        memo.put(target, false);
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
            printResult(canConcat(target, words, new HashMap<String, Boolean>()), target, words);
        }
    }
}
