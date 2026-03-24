import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AllConstruct {

    public static void printResult(List<List<String>> result) {
        System.out.printf("[");
        if (result != null) {
            System.out.println("");
            for (List<String> l : result) {
                Collections.sort(l);
                System.out.println(l);
            }
        }
        System.out.println("]");
    }

    public static List<List<String>> allConstruct(String target, List<String> words, Map<String, List<List<String>>> memo) {
        System.out.printf("target=%s memo=%s%n", target, memo);
        if (memo.containsKey(target)) {
            System.out.printf("Return memo=%s for target=%s%n", memo.get(target), target);
            return memo.get(target);
        }

        if (target.isEmpty()) {
            // Return a List with an empty List.
            List<List<String>> emptyList = new ArrayList<>();
            emptyList.add(new ArrayList<>());
            return emptyList;
        }

        List<List<String>> retVal = null;

        for (String word : words) {
            // Determine if the current word is a suffix of the target.
            if (target.indexOf(word) == 0) {
                String suffix = target.substring(word.length(), target.length());
                // Make our recursive call with the word removed from the
                // target to see if we can continue whittling down the word
                // with additional words.
                List<List<String>> suffixResult = allConstruct(suffix, words, memo);
                System.out.println("suffixResult=" + suffixResult);

                if (suffixResult != null) {
                    // If we have received a non-null result, we first need to
                    // check to see if we our retVal is a non-null value.  If
                    // so, instatiate it.
                    if (retVal == null) {
                        retVal = new ArrayList<>();
                    }

                    // Add the word that just tested to each of the Lists
                    // that we were returned

                    for (List<String> suffixList : suffixResult) {
                        suffixList.add(word);
                        // Add this newly concatenated List to our return value
                        retVal.add(suffixList);
                    }
                }
            }
        }

        System.out.printf("Return retVal=%s for target=%s%n", retVal, target);
        memo.put(target, retVal);
        return retVal;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press CTRL+C to exit");
        while (true) {
            System.out.printf("Enter a target word, followed by a space delimited set of substrings to calculate the number of permutations of the substrings that can be used to construct the provided word: ");
            String[] tokens = scanner.nextLine().split(" ");
            String target = tokens[0];
            List<String> words = Arrays.asList(
                Arrays.copyOfRange(tokens, 1, tokens.length));
            System.out.printf(
                "Calculating for target=%s with words=%s%n",
                target,
                words.toString());
            long start = System.currentTimeMillis();
            Map<String, List<List<String>>> memo = new HashMap<>();
            List<List<String>> result = allConstruct(target, words, memo);
            long totalMillis = System.currentTimeMillis() - start;
            System.out.println("Millis to calculate=" + totalMillis);
            printResult(result);
        } 
    }
}
