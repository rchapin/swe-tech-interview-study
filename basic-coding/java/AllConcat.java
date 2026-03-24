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
public class AllConcat {

    public static void printArr(String[][] arr) {
        for (String[] s : arr) {
            System.out.printf("[");
            for (String s1 : s) {
                System.out.printf("%s ", s1); 
            }
            System.out.println("]");
        }
    }
    public static void printResult(String[][] result, String target, String[] words) {
        if (result.length == 0) {
            System.out.println("[]");
        }
        for (String[] s : result) {
            System.out.printf("[");
            for (String s1 : s) {
                System.out.printf("%s ", s1); 
            }
            System.out.println("]");
        }
    }

    public static String[][] allConcat(String target, String[] words, Map<String, String[][]> memo ) {
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        if (target.isEmpty()) {
            // Return an array with a sub array of 0 length
            return new String[1][0];
        }

        // Instantiate an array of 0 length without any sub-arrays.  This is what
        // we will return if we do not find any permutations of a base case while
        // continuing the recursion from this point.
        String[][] retVal = new String[0][];
        for (String word : words) {
            if (target.indexOf(word) == 0) {
                // System.out.println("removed suffx=" + word);
                String suffix = target.substring(word.length(), target.length());
                String[][] resultArrays = allConcat(suffix, words, memo);

                for (String[] resultSubArray : resultArrays) {
                    // Create a new array and add the word removed to each
                    String[] wordResult = new String[resultSubArray.length + 1];
                    for (int i = 0; i < resultSubArray.length; i++) {
                        wordResult[i] = resultSubArray[i]; 
                    }
                    // System.out.println("Adding word =" + word);
                    // Now we add the word to our current wordResult
                    // and then add it to our return value
                    wordResult[wordResult.length - 1] = word;

                    // Incrementally increase the first dimention size of the array
                    String[][] newRetVal = new String[retVal.length + 1][];
                    // Copy over all of the existing sub arrays
                    for (int i = 0; i < retVal.length; i++) {
                        newRetVal[i] = retVal[i];
                    }
                    newRetVal[newRetVal.length - 1] = wordResult;
                    // Update the reference to our retVal to reflext the newly
                    // constituted return value.
                    retVal = newRetVal;
                }
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
                "substrings to determine all of the permutations of the " +
                "substrings that we can use to generate the target: ");
            String[] tokens = scanner.nextLine().split("\\s+");
            String target = tokens[0];
            String[] words = Arrays.copyOfRange(tokens, 1, tokens.length);
            printResult(allConcat(target, words, new HashMap<String, String[][]>()), target, words);
        }
    }
}
