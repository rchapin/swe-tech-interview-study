import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class CanConcatIdx {

    public static boolean canConcatIdx(String word, String[] words, int idx, Map<Integer, Boolean> memo) {
        if (memo.containsKey(idx)) {
            return memo.get(idx);
        }
        if (idx == word.length() - 1) {
            memo.put(idx, true);
            return true;
        }

        boolean curr = false;
        boolean retVal = false;
        for (String w : words) {
            // Check to see if the 
            if (word.indexOf(w) == idx) {
                // This entire word, w, is contained 
            }
        }

        return retVal;
    }

    public static void main(String[] args) {
        System.out.println("Press CTRL+c to exit...");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a word, separated by a space/tab and an array of word tokens separated by \",\"");
            String line = scanner.nextLine();
            String[] lineTokens = line.split("\\s");
            String word = lineTokens[0];
            String[] words = lineTokens[1].split(",");
            System.out.println(canConcatIdx(word, words, 0, new HashMap<Integer, Boolean>()));
        }
    }
}
