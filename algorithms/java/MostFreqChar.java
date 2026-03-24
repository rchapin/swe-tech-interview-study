import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class MostFreqChar {

    public static String mostFreqChar(String input) {
        Map<Character, int[]> occurrences = new HashMap<>();
        HighestCounts hc = new HighestCounts();
        int i = 0;
        char c;
        while (i < input.length()) {
            c = input.charAt(i); 

            // See if there is already an array for this character
            int[] idAndCount = occurrences.get(c);
            if (idAndCount == null) {
                idAndCount = new int[2];
                // The 0th element is the index in the array that we first
                // saw this character.  We will initialize the count to 0
                // because we will increment it outside of the null guard
                // block.
                idAndCount[0] = i;
                idAndCount[1] = 0;
                occurrences.put(c, idAndCount);
            }
            // Bump the count of this character
            idAndCount[1] = idAndCount[1] + 1;

            // Utilize the logic in the Highest count to see if we have a
            // tie for the number of times that we have already seen a
            // character.
            hc.addChar(c, idAndCount[1]);
            // System.out.println(hc);
            i++;
        }

        // Now that we have iterated over the input string we need to
        // iterate over the set of characters in the HighestCount object.
        // For each, we check the 0th element in the int[] in the
        // occurrences map to determine which we saw first and that is
        // our answer.
        String retVal = null;
        if (hc.chars.size() == 1) {
            for (char d : hc.chars) {
                retVal = String.valueOf(d);
            }
        } else {
            // If we have more than one character that we have seen at
            // the same occurrence we need to look in the occurrences
            // map at each to figure out which we saw first.  The 0th
            // index in the in[] array is the index in the original String
            // when we first encountered the character.
            int lowestIdx = Integer.MAX_VALUE;
            int curIdx;
            char answerChar = '\0';
            for (char d : hc.chars) {
                int[] idAndCount = occurrences.get(d);
                curIdx = idAndCount[0];
                if (curIdx < lowestIdx) {
                    lowestIdx = curIdx;
                    answerChar = d;
                }
            }
            retVal = String.valueOf(answerChar);
        }

        return retVal;
    }

    public static void main(String[] args) {
        System.out.println(mostFreqChar("potato")); 
        System.out.println(mostFreqChar("bookeeper")); 
        System.out.println(mostFreqChar("mississippi"));
        System.out.println(mostFreqChar("eleventennine"));
        System.out.println(mostFreqChar("riverbed"));
    }

    public static class HighestCounts {
        int count = Integer.MIN_VALUE;
        Set<Character> chars;

        public HighestCounts() {
            chars = new HashSet<>();
        }

        public void addChar(char c, int count) {
            if (count > this.count) {
                // Create a new HashSet, dumping previously recorded chars
                this.count = count;
                chars = new HashSet<>();
                chars.add(c);
            }
            if (count == this.count) {
                chars.add(c);
            }
        }

        @Override
        public String toString() {
            return String.format("count=%d, chars=%s", count, chars);
        }
    }
}
