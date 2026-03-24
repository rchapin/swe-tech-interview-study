import java.util.Set;
import java.util.HashSet;

public class Compress {

    public static String compress(String input) {
        StringBuffer buf = new StringBuffer();
        int i = 0;
        int k = 0;
        char c;

        while (i < input.length()) {
            k = 1;
            c = input.charAt(i);

            // While we keep seeing the same char, bump k
            while (true) {
                if ((i+1) < input.length() && c == input.charAt(i+1)) {
                    i++;
                    k++;
                } else {
                    break;
                }
            }
            // Now output 
            for (int l = 0; l < k; l++) {
                buf.append(c);
            }
            i++;
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        System.out.println(compress("ccaaatsss"));
        System.out.println(compress("ssssbbz"));
        System.out.println(compress("ppoppppp"));
        System.out.println(compress("nnneeeeeeeeeeeezz"));
        System.out.println(compress("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy"));
    }
}
