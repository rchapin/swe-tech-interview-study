import java.util.Map;
import java.util.HashMap;

public class BefittingBrackets { 

    public static final char PAREN_OPEN = '(';
    public static final char PAREN_CLOSED = ')';
    public static final char BRACKET_OPEN = '[';
    public static final char BRACKET_CLOSED = ']';
    public static final char BRACE_OPEN = '{';
    public static final char BRACE_CLOSED = '}';


    public static boolean befittingBrackets(String in) {
        Count parenCount = new Count();
        Count bracketCount = new Count();
        Count bracesCount = new Count();

        Map<Character, Count> counts = new HashMap<>();
        counts.put(PAREN_OPEN, parenCount);
        counts.put(PAREN_CLOSED, parenCount);
        counts.put(BRACKET_OPEN, bracketCount);
        counts.put(BRACKET_CLOSED, bracketCount);
        counts.put(BRACE_OPEN, bracesCount);
        counts.put(BRACE_CLOSED, bracesCount);

        Map<Character, Integer> braces = new HashMap<>();
        braces.put(PAREN_OPEN, 1);
        braces.put(PAREN_CLOSED, -1);
        braces.put(BRACKET_OPEN, 1);
        braces.put(BRACKET_CLOSED, -1);
        braces.put(BRACE_OPEN, 1);
        braces.put(BRACE_CLOSED, -1);

        for (int i = 0; i < in.length(); i++) {
            // System.out.println(in.charAt(i));
            char c = in.charAt(i);
            Count count = counts.get(c);
            count.count += braces.get(c);
            if (count.count < 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[] in = {
            "(){}[](())",   // T
            "({[]})",       // T
            "[]{}(}[]",     // F
            "{[(}])",       // F
            ""             // T
        };
        for (String s : in) {
            System.out.println(befittingBrackets(s));
        }
    }

    public static class Count {
        int count;
        public Count(){}
        public String toString() {
            return String.format("count=%d", count);
        }
    }
}
