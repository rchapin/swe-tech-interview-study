import java.util.Set;
import java.util.HashSet;

public class Decompress {

    public static final Set<Character> NUM_CHARS = getNumChars();
    public static Set<Character> getNumChars() {
        Set<Character> retVal = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            System.out.println("i=" + i);
            // Convert the integer i to a char primitive
            retVal.add((char)(i + '0'));
        }
        return retVal;
    }

    public static void decompress(String s) {
        int i = 0;
        while (i < s.length()) {
            // System.out.println(s.charAt(i));
            int k = 0;

            // Determine how many consecutive chars are a number
            while (true) {
                if (NUM_CHARS.contains(s.charAt(i))) {
                    k *= 10;
                    k += Character.getNumericValue(s.charAt(i));
                    // System.out.println("k=" + k);
                    i++;
                } else{
                    break;
                }
            }

            // Now I have a number that represents the number of times
            // that I need to print the character.
            for (int j = 0; j < k; j++) {
                System.out.printf("%c", s.charAt(i));
            }
            i++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        decompress("2c3a1t");
        decompress("4s2b");
        decompress("2p1o5p");
        decompress("3n12e2z");
        decompress("127y");
    }
}
