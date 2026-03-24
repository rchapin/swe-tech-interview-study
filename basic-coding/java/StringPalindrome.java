import java.util.Scanner;

public class StringPalindrome {

    public static boolean isPalindrome(String input) {
        char a, b;
        for (int i = 0, j = input.length() - 1; i < j; i++, j--) {
            a = input.charAt(i);
            b = input.charAt(j);
            if (a != b) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press CTRL+C to exit");
        while (true) {
            System.out.printf("Enter a String to check if it is a palindrome: ");
            String input = scanner.nextLine();
            System.out.printf("%s isPalindrome=%b%n", input, isPalindrome(input));
        }
    }
}
