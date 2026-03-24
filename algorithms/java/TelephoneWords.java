import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class TelephoneWords {

  public static final Map<Character, Set<Character>> NUM_PAD = getNumPad();
  public static Map<Character, Set<Character>> getNumPad() {
    Map<Character, Set<Character>> retVal = new HashMap<>();
    retVal.put('2', new HashSet<Character>(Arrays.asList('a', 'b', 'c')));
    retVal.put('3', new HashSet<Character>(Arrays.asList('d', 'e', 'f')));
    retVal.put('4', new HashSet<Character>(Arrays.asList('g', 'h', 'i')));
    retVal.put('5', new HashSet<Character>(Arrays.asList('j', 'k', 'l')));
    retVal.put('6', new HashSet<Character>(Arrays.asList('m', 'n', 'o')));
    retVal.put('7', new HashSet<Character>(Arrays.asList('p', 'q', 'r', 's')));
    retVal.put('8', new HashSet<Character>(Arrays.asList('t', 'u', 'v')));
    retVal.put('9', new HashSet<Character>(Arrays.asList('w', 'x', 'y')));
    return retVal;
  }

  public static Map<Character, Character> getRevIdx(String num) {
    Map<Character, Character> retVal = new HashMap<>();
    for (int i = 0; i < num.length(); i++) {
      char numChar = num.charAt(i);

      Set<Character> chars = NUM_PAD.get(numChar);
      if (chars == null) {
        continue;
      }
      for (char c : chars) {
        retVal.put(c, numChar);
      }
    }
    return retVal;
  }

  public static List<String> telephoneWords(String num, List<String> words) {
    // Build my reverse index;
    Map<Character, Character> revIdx = getRevIdx(num);

    List<String> retVal = new ArrayList<>();

    words:
    for (String word : words) {
      // Set/reset the index from which we are starting our search in our numbers
      // string.
      int numsIdx = 0;
      int lastFoundIdx = Integer.MIN_VALUE;

      chars:
      for (int i = 0; i < word.length(); i++) {
        char currChar = word.charAt(i);
        // Check that our reverse index contains a key for this character, if
        // not, we can just skip this word altogether.
        if (!revIdx.containsKey(currChar)) {
          continue words;
        }

        // Since the reverse index does contain a key for this character, get the
        // number character to which the currChar is mapped to on the keypad.
        char currNum = revIdx.get(currChar);

        // Now that we know two key things; a) the current index that we should use to
        // start our search in our provided number, b) the NEXT number character that we
        // are looking for in our number string, we can loop through the number String
        // beginning at the current index.
        while (numsIdx < num.length()) {
          if (num.charAt(numsIdx) == currNum) {
            // We found a valid number and we can continue the search.  We must also
            // increment our lastFoundIdx to indicate the index in which we last found
            // a number.
            lastFoundIdx = numsIdx;
            numsIdx++;
            continue chars;
          }
          numsIdx++; 

          // After we bump the numsIdx we must check to see if the the lastFoundIdx
          // is 0 or greater.  If it is, then the difference beween the lastFoundIdx
          // and the numsIdx cannot be greater than 1, or we do not have a contiguous
          // number.  We need to reset the lastFoundIdx and start searching through
          // the number string again
          if (lastFoundIdx >= 0) {
            if ((numsIdx - lastFoundIdx) > 1) {
              lastFoundIdx = Integer.MIN_VALUE;
            }
          }
        }
      }
      retVal.add(word);
    }

    return retVal;
  }

  public static void main(String[] args) {
    List<String> words = new ArrayList<>(Arrays.asList("foo", "bar", "baz", "foobar", "emo", "cap", "car", "cat", "as"));
    System.out.println(telephoneWords("3662277", words));
  }
}
