import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class StringAnagram {

    public static Map<Character, Integer> hashString(String input) {
        Map<Character, Integer> retVal = new HashMap<>();
        char c;
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            Integer val = retVal.get(c);
            if (val == null) {
                val = 0;
            }
            retVal.put(c, val + 1);
        }
        return retVal;
    }

    public static boolean isAnagram(String a, String b) {
        // If both strings do not contain the same number of characters
        // there is no reason to continue
        if (a.length() != b.length()) {
            return false;
        }
        Map<Character, Integer> aMap = hashString(a);
        Map<Character, Integer> bMap = hashString(b);
        if (aMap.equals(bMap)) {
            return true;
        }
        
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Press CTRL+C to exit");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("Enter two strings to check whether they are anagrams of each other separated by spaces: ");
            String input = scanner.nextLine();
            String[] tokens = input.split(" ");
            System.out.printf("Result=%b%n", isAnagram(tokens[0], tokens[1]));
        }
    }
}
