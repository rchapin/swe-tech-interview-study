import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Deque;
import java.util.ArrayDeque;

public class BefittingBrackets2 { 

    public static boolean befittingBrackets(String in) {
        Set<Character> openChars = new HashSet<>();
        openChars.add('(');
        openChars.add('{');
        openChars.add('[');

        Set<Character> closedChars = new HashSet<>();
        closedChars.add(')');
        closedChars.add('}');
        closedChars.add(']');

        Map<Character, Character> closedToOpen = new HashMap<>();
        closedToOpen.put(']', '[');
        closedToOpen.put('}', '{');
        closedToOpen.put(')', '(');

        Deque<Character> stack = new ArrayDeque<>();
        
        char cur;
        char popped;
        for (int i = 0; i < in.length(); i++) {
            cur = in.charAt(i);
            if (openChars.contains(cur)) {
                stack.push(cur);
                continue;
            }
            popped = stack.pop();
            if (closedToOpen.get(cur) != popped) {
                return false;
            }
        }
        if (stack.size() == 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String[] in = {
            "(){}[](())",   // T
            "({[]})",       // T
            "[]{}(}[]",     // F
            "{[(}])",       // F
            "",             // T
            "{[(}])"        // F
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
