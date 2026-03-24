import java.util.List;
import java.util.ArrayList;

public class Toeplitz {

  static boolean check(int[][] arr, int val, int row, int col) {
    // Do bounds checking to ensure that we do not step off either axis of our matrix.
    if (row > arr.length - 1) {
      return true;
    }
    if (col > arr[row].length - 1) {
      return true;
    }
    // Check that the values match.  If so, continue a recursive search until we either
    // run out of rows and columns or find a value that does not match.
    if (arr[row][col] != val) {
      return false;
    }
    return check(arr, val, row + 1, col + 1);
  }

  static boolean isToeplitz(int[][] arr) {
    if (arr.length == 1) {
      return true;
    }


    for (int row = 0; row < arr.length; row++) {
      // Figure out at which column we need to stop processing to avoid reprocessing
      // values that we have already validated.
      int endIdx = row == 0 ? arr[0].length - 1 : (arr[row].length == 1 ? 0 : row);
      for (int col = 0; col < endIdx; col++) {
        int val = arr[row][col];
        if (!check(arr, val, row + 1, col + 1)) {
          return false;
        }
      }
    }
    return true;
  }

  static void run(int[][] arr) {
    long start = System.nanoTime();

    long duration = System.nanoTime() - start;
    System.out.printf("%d, %b%n", duration, isToeplitz(arr));
  }

  public static void main(String[] args) {
    List<int[][]> tests = new ArrayList<>();
    tests.add(new int[][] {{4,0}, {9, 4}});
    tests.add(new int[][] {{6, 4, 4}});
    tests.add(new int[][] {{3}, {5}, {6}});
    tests.add(new int[][] {
      {3,9},{5,3},{6,5}
    });
    tests.add(new int[][] {{3,1,7},{4,1,1},{2,4,3}});
    tests.add(new int[][] {
      {8,8,8,8,8},{8,8,8,8,9},{8,8,8,8,8},{8,8,8,8,8},{8,8,8,8,8}
    });
    for (int[][] test : tests) {
      run(test);
    }
  }
}

