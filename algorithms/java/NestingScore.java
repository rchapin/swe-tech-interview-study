import java.util.Set;
import java.util.HashSet;
import java.util.Deque;
import java.util.ArrayDeque;

public class NestingScore {

    public static final Set<Character> NUMS = getNums();
    public static Set<Character> getNums() {
        Set<Character> retVal = new HashSet<>();
        for (char c = '0'; c <= '9'; c++) {
            retVal.add(c);
        }
        return retVal;
    }


    public static void score(Deque<Object> stack) {
        int curVal;
        Object curElement = stack.pop();
        if (curElement instanceof Integer) {
            // If what we just popped off of the stack is an Integer, we need
            // to keep popping items off the stack, adding them to the current
            // value until we find an open bracket.  At that point, we push
            // onto the stack the current value * 2.
            curVal = ((Integer)curElement).intValue();
            while (true) {
                curElement = stack.pop();
                if (curElement instanceof Integer) {
                    curVal += ((Integer)curElement).intValue();
                }
                else {
                    stack.push(Integer.valueOf(curVal * 2));
                    return;
                }
            }
        } else {
            stack.push(Integer.valueOf(1));
        }
    }

    public static int nestingScore(String input) {
        Deque<Object> stack = new ArrayDeque<>();
        char curChar;

        for (int idx = 0; idx < input.length(); idx++) {
            curChar = input.charAt(idx);
            if (curChar == '[') {
                stack.push(Character.valueOf(curChar));
                continue;
            }
            if (curChar == ']') {
                score(stack);
            }
        }

        int retVal = 0;
        while (stack.size() > 0) {
            retVal += (Integer)stack.pop();
        }
        return retVal;
    }

    public static void main(String[] args) {
        String[] input = {
            "[]",
            "[][][]",
            "[[]]",
            "[[][]]",
            "[[][][]]",
            "[[][]][]",
            "[][[][]][[]]",
            "[[[[[[[][]]]]]]][]"
        };
        for (String s : input) {
            System.out.println(nestingScore(s));
        }
    }
}
