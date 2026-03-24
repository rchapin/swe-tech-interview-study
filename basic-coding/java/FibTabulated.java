import java.util.Scanner;

public class FibTabulated {

    public static void initTable(int[] t) {
        for (int i = 0; i < t.length; i++) {
            t[i] = 0;
        }
        t[1] = 1;
    }

    public static int fib(int n) {
        // Initialize a table that is the size of n + 1
        int[] t = new int[n+1];
        initTable(t);

        int idx = 2;
        while (idx <= n) {
            t[idx] = t[idx-2] + t[idx-1];
            idx++;
        }
        return t[n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press CTRL+C to exit");
        while (true) {
            System.out.printf("Enter a fibonacci number to calculate: ");
            String in = scanner.nextLine();
            int n = Integer.parseInt(in);
            System.out.printf("Result=%d%n", fib(n)); 
        } 
    }
}


