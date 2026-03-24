import java.util.Scanner;

public class StringReverser {

    public static String reverse(String in) {
        int lastIdx = in.length() - 1;
        char temp;
        StringBuffer buf = new StringBuffer(in);
        for (int i = 0, j = lastIdx; i < j; i++, j--) {
            temp = buf.charAt(i);
            buf.setCharAt(i, buf.charAt(j));
            buf.setCharAt(j, temp);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press CTRL+C to exit");
        while (true) {
            System.out.printf("Enter a String to be reversed: ");
            String input = scanner.nextLine();
            System.out.printf("Reversed String:               %s%n", reverse(input));
        }
    }
}
