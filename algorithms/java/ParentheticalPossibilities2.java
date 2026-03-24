import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ParentheticalPossibilities {

    public static List<String> expand(List<String> prefixes, List<String> expList) {
        // System.out.printf("prefixes=%s, expList=%s%n", prefixes.toString(), expList.toString());
        List<String> retVal = new ArrayList<>();
        for (int i = 0; i < prefixes.size(); i++) {
            for (int j = 0; j < expList.size(); j++) {
                retVal.add(prefixes.get(i) + expList.get(j));
            }
        }
        // System.out.println("expand returning " + retVal);
        return retVal;
    }

    public static List<String> parentheticalPossibilities(String input) {
        List<String> retVal = new ArrayList<>();
        if (input.isEmpty) {
            return retVal;
        }
        
        char firstElem = input.charAt(0);
        if (firstElem == "(") {
            // Get all of the chars that are inside the parens.
            int idx = 1;
            StringBuilder buf = new StringBuilder();
            char c;
            while (true) {
                c = intput.charAt(idx);
                if (c == ')') {
                    break;
                }
                buf.append(c);
                idx++;
            }
            // Recursively call this method for each of the chars in the
            // current buffer.
            for (int i = 0; i < buf.length(); i++) {
                List<String> expResult = parenthethicalPossibilities(input.subString(idx, input.length());
                // Now, append the expansion list char from the buffer to each
                // of the Strings returned.
                for (String s : expResult) {
                    retVal.add(Character.toString(buf.get(i)) + s;
                }
            }
        } else {

        }

        return retVal;
    }

    public static void printResult(String input, List<String> list) {
        System.out.printf("-- %s%n", input);
        Collections.sort(list);
        for (String s : list) {
            System.out.println(s);
        }
    }


    public static void main(String[] args) {
        String[] input = {
            "x(mn)yz",
            "(qr)ab(stu)c",
            "(etc)(blvd)(cat)",
            "",
            "taco",
        };
        for (String i : input) {
            printResult(i, parentheticalPossibilities(i));
        }
    }
}
