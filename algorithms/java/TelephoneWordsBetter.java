import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class TelephoneWordsBetter {

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

  public static Map<String, String> buildWordsAsNums(List<String> words, Map<Character, Character> revIdx) {
    Map<String, String> retVal = new HashMap<>(); 
    words:
    for (String word : words) {
      StringBuilder buf = new StringBuilder();
      for (int i = 0; i < word.length(); i++) {
        // if the current character is not in our reverse index, dump this word altogether.
        char c = word.charAt(i);
        if (revIdx.containsKey(c)) {
          buf.append(revIdx.get(c));
        } else {
          continue words;
        }
      }
      retVal.put(word, buf.toString());
    }
    return retVal;
  }

  public static List<String> telephoneWords(String num, List<String> words) {
    List<String> retVal = new ArrayList<>();

    // Build my reverse index;
    Map<Character, Character> revIdx = getRevIdx(num);
    Map<String, String> wordsAsNums = buildWordsAsNums(words, revIdx);

    // Now that we have a version of each of our input words as a "number" String
    // we can simply see if the string exists as a substring of our input number
    for (Map.Entry<String, String> entry : wordsAsNums.entrySet()) {
      if ((num.indexOf(entry.getValue())) != -1) {
        retVal.add(entry.getKey());
      }
    }
    return retVal;
  }

  public static void main(String[] args) {
    List<String> words = new ArrayList<>(Arrays.asList("foo", "bar", "baz", "foobar", "emo", "cap", "car", "cat", "as", "sa"));
    System.out.println(telephoneWords("3662277", words));
  }
}
