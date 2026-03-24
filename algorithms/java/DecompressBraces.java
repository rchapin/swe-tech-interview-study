import java.util.Set;
import java.util.HashSet;
import java.lang.StringBuffer;
import java.lang.StringBuilder;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;

public class DecompressBraces {

    public static final Set<Character> NUMS = getNums();
    public static Set<Character> getNums() {
        Set<Character> retVal = new HashSet<>();
        for (char c = '0'; c <= '9'; c++) {
            retVal.add(c);
        }
        return retVal;
    }

    public static void decompress(Deque<String> stack) {
        // Pop Strings off of the stack until we find one that contains as it's
        // first character, some numeric character.
        List<String> buf = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String cur = null;

        while (true) {
            cur = stack.pop();
            // Determine if this is a number
            if (NUMS.contains(cur.charAt(0))) {
                int numItr = Integer.parseInt(cur);
                // Generate our decompressed string based on the contents of buf.
                // The current buf is the set of strings that we need to print out
                // but it is currently in reverse order.
                StringBuffer output = new StringBuffer();
                for (int i = buf.size() - 1; i >= 0; i--) {
                    output.append(buf.get(i));
                }
                for (int i = 0; i < numItr; i++) {
                    sb.append(output.toString());    
                }
                stack.push(sb.toString());
                return;
            }
            buf.add(cur);
        }
    }

    public static String renderStack(Deque<String> stack) {
        String[] arr = new String[stack.size()];
        int idx = stack.size() - 1;

        while (stack.size() > 0) {
            arr[idx] = stack.pop();
            idx--;
        }
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static String decompressBraces(String input) {
        Deque<String> stack = new ArrayDeque<>();

        // The current number that we might parse from a numerical character
        // that we find while iterating through our input String.
        int curNum = 0;
        // The number of times that we will print out a character sequence
        // that we parse out between two sets of braces.
        int numItr = 0;
        char curChar;

        for (int i = 0; i < input.length(); i++) {
            curChar = input.charAt(i);
            if (NUMS.contains(curChar)) {
                curNum = Character.getNumericValue(curChar);
                numItr = (numItr * 10) + curNum;
                continue;
            }
            // If the current char is NOT a number we need to determine if we
            // just finished iterating through a series of number characters to
            // generate the actual number of times that we should concatenate
            // the character sequence that follows.
            if (numItr > 0) {
                stack.push(Integer.toString(numItr));
                numItr = 0;
            }

            if (curChar == '{') {
                continue;
            }
            if (curChar == '}') {
                decompress(stack);
                continue;
            }
            stack.push(Character.toString(curChar));
        }
        return renderStack(stack);
    }

    public static void main(String[] args) {
        String[] input = {
            "11{q}3{tu}v",
            "ch3{ao}",
            "2{y3{o}}s",
            "z3{a2{xy}b}",
            "2{3{r4{e}r}io}",
            "go3{spinn2{ing}s}",
            "2{l2{if}azu}l",
            "3{al4{ec}2{icia}}"
        };
        for (String s : input) {
            System.out.println(decompressBraces(s));
        }
    }
}
