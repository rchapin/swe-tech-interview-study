import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class GridTraveler {

    public static final String FORMAT = "%d,%d";

    public static long gridTraveler(long x, long y, Map<String, Long> memo) {
        // The base case is either when we have a grid with zero dimension in
        // either axis.  In which case there are no ways to travel in this
        // grid.
        if (x < 1 || y < 1) {
            return 0L;
        }
        // If we are in a 1x1 grid, there is only 1 way to travel, we are
        // already there and this is our base case.
        if (x == 1 && y == 1) {
            return 1L;
        }
        // Since we know that the number of ways to travel in a 4,3 grid is the
        // same as a 3,4 grid
        String key = x < y ? String.format(FORMAT, y, x) : String.format(FORMAT, x, y); 
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        long retVal = gridTraveler(x-1, y, memo) + gridTraveler(x, y-1, memo);
        memo.put(key, retVal);
        return retVal;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press CTRL+C to exit");
        while (true) {
            System.out.printf("Enter x y size for a grid to be traveled, two ints separated by a space: ");
            String in = scanner.nextLine();
            String[] tokens = in.split("\\s+");
            long x = Long.parseLong(tokens[0]);
            long y = Long.parseLong(tokens[1]);
            System.out.printf("Result=%d%n", gridTraveler(x, y, new HashMap<String, Long>())); 
        } 
    }
}


