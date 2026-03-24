import java.util.Map;
import java.util.HashMap;

public class LongestCommonSubsequence {

    public static int lcs(String a, String b) {
        return findLcs(a, a.length()-1, b, b.length()-1, new HashMap<String, Integer>());
    }

    public static int findLcs(String a, int i, String b, int j, Map<String, Integer> memo) {
        // Ensure that we aren't going to step of the edge of either of the
        // Strings before we do anything with them.
        if (i < 0 || j < 0) {
            return 0;
        }

        // Is this value in the memo?  If so, just return the memoized result
        String key = String.format("%d,%d", i, j);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int retVal = 0;
        if (a.charAt(i) == b.charAt(j)) {
            retVal ++;
            retVal += findLcs(a, i-1, b, j-1, memo);
        } else {
            retVal += Math.max(findLcs(a, i-1, b, j, memo), findLcs(a, i, b, j-1, memo));
        } 
        // Add the value to the memo
        memo.put(key, retVal);
        return retVal;
    }

    public static void main(String[] args) {
        String a = args[0];
        String b = args[1];
        long start = System.currentTimeMillis();
        System.out.println("Longest common subsequence = " + lcs(a, b));
        System.out.println("Total time in millis = " + (System.currentTimeMillis() - start));
    }
}
