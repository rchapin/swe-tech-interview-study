import java.util.Scanner;

public class GridTravelerTabulated {

    public static long[][] initTable(int x, int y) {
        long[][] retVal = new long[x+1][];
        for (int i = 0; i <= x; i++) {
            retVal[i] = new long[y+1];
        }

        // Set the 1,1, position to 1 to seed the table with what we know
        // is the value for the base case for this cell in the table.
        retVal[1][1] = 1L;
        return retVal;
    }

    public static long gridTraveler(int x, int y) {
        // Initialize a table that is the size of n + 1
        long[][] t = initTable(x, y);

        // Walk through the table, row by row, column, by column and add the
        // current cell to the one to the right of us, and the one below us
        // since we know that we can only travel down and to the right.
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                long current = t[i][j];
                // Add our value to the cell below us if there is a "below"
                if ((i+1) < t.length) {
                    t[i+1][j] += current;
                }
                // Add our value to the the cell to our right if there is a "right"
                if ((j+1) < t[i].length) {
                    t[i][j+1] += current;
                }
            }
        }

        return t[x][y];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press CTRL+C to exit");
        while (true) {
            System.out.printf("Enter x y size for a grid to be traveled, two ints separated by a space: ");
            String in = scanner.nextLine();
            String[] tokens = in.split("\\s+");
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);
            System.out.printf("Result=%d%n", gridTraveler(x, y)); 
        } 
    }
}


