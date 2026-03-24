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
        retVal.add("");
        char curChar;
        for (int i = 0; i < input.length(); i++) {
            // System.out.println("retVal=" + retVal);
            curChar = input.charAt(i);
            // System.out.printf("curChar=%c%n", curChar); 

            if (curChar == '(') {
                List<String> expList = new ArrayList<>();
                while(true) {
                    i++;
                    curChar = input.charAt(i);
                    // System.out.printf("curChar=%c%n", curChar); 
                    if (curChar == ')') {
                        break;
                    }
                    expList.add(Character.toString(curChar));
                }
                retVal = expand(retVal, expList);
            } else {
                // Add the current character to each of the existing elements
                // in the retVal list.
                for (int j = 0; j < retVal.size(); j++) {
                    retVal.set(j, retVal.get(j) + Character.toString(curChar));
                }
            }
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
