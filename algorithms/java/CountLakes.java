import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class CountLakes {

  static String createNode(int x, int y) {
    return String.format("%d,%d", x, y);
  }

  static void renderGrid(boolean[][] grid) {
    System.out.printf("   ");
    for (int i = 0; i < grid[0].length; i++) {
      System.out.printf(" %d ", i);
    }
    System.out.printf("%n");

    for (int i = 0; i < grid.length; i++) {
      System.out.printf("%d  ", i);
      for (int j = 0; j < grid[i].length; j++) {
        System.out.printf(" %s ", (grid[i][j] ? "x" : "."));
      }
      System.out.println();
    }
  }

  static int countLakes(boolean[][] grid, int x, int y) {
    renderGrid(grid);

    // First we need to find the boundaries of the island and all of the Nodes
    // that make it up.
    Set<String> island = new HashSet<>();
    int n = Integer.MAX_VALUE;
    int s = Integer.MIN_VALUE;
    int e = Integer.MIN_VALUE;
    int w = Integer.MAX_VALUE;

    Deque<String> queue = new ArrayDeque<>();
    queue.add(createNode(x, y));
    int curX = 0;
    int curY = 0;

    while (queue.size() > 0) {
      String cur = queue.remove();
      island.add(cur);
      String[] tokens = cur.split(",");
      curX = Integer.valueOf(tokens[0]);
      curY = Integer.valueOf(tokens[1]);
      // Determine the northern most, southern most, eastern most, and western most nodes
      // so that we have a boundary for the island.
      n = Math.min(n, curX);
      s = Math.max(s, curX);
      e = Math.max(e, curY);
      w = Math.min(w, curY);

      // Check to see which of the adjacent Nodes are also islands and then add them
      // to the queue.
      if (curX-1 >= 0 && grid[curX-1][curY]) {
        String node = createNode(curX-1, curY);
        if (!island.contains(node)) {
          queue.add(node);
        }
      }
      if (curX+1 < grid.length && grid[curX+1][curY]) {
        String node = createNode(curX+1, curY);
        if (!island.contains(node)) {
          queue.add(node);
        }
      }
      if (curY-1 >= 0 && grid[curX][curY-1]) {
        String node = createNode(curX, curY-1);
        if (!island.contains(node)) {
          queue.add(node);
        }
      }
      if (curY+1 < grid[curX].length && grid[curX][curY+1]) {
        String node = createNode(curX, curY+1);
        if (!island.contains(node)) {
          queue.add(node);
        }
      }
    }

    // Now that we know all of the islands and the boundary of the island we can
    // iterate over all of the nodes in the boundary looking for water.  When we find
    // a water node, we do a BFS for other water nodes until we find one that has
    // one of its edges touching the boundary in which case it is a lagoon and
    // not an interior island.  Or, until we no longer find visited water nodes
    // and we have found an island.  Then we continue traversing the unvisited
    // island nodes until all are visited and/or we encounter another water node
    // within the boundary.
    Set<String> visited = new HashSet<>();
    int retVal = 0;
    for (int i = n; i <= s; i++) {
      for (int j = w; j <= e; j++) {

        // Is this node water
        if (!grid[i][j]) {
          // And have we not yet visited it?
          String node = createNode(i, j);
          if (visited.contains(node)) {
            continue;
          }

          // Execute a BFS visiting all of the adjacent water nodes.
          queue = new ArrayDeque<>();
          queue.add(createNode(i, j));
          boolean isLake = true;

          while (queue.size() > 0) {
            String cur = queue.remove();
            String[] tokens = cur.split(",");
            curX = Integer.valueOf(tokens[0]);
            curY = Integer.valueOf(tokens[1]);
            // Check to see if any of the adjacent nodes go beyond the boundary
            // if so this cannot be a lake.
            visited.add(cur);

            // North node
            if (curX-1 >= 0 && !grid[curX-1][curY]) {
              if (curX-1 < n) {
                // This node is outside our boundary and this water and any
                // connected to it cannot be a lake.
                isLake = false;
              } else {
                node = createNode(curX-1, curY);
                if (!visited.contains(node)) {
                  queue.add(node);  
                }
              }
            }

            // South node
            if (curX+1 < grid.length && !grid[curX+1][curY]) {
              if (curX+1 > s) {
                isLake = false;
              } else {
                node = createNode(curX+1, curY);
                if (!visited.contains(node)) {
                  queue.add(node);  
                }
              }
            }

            // East node
            if (curY+1 < grid[curY].length && !grid[curX][curY+1]) {
              if (curY+1 > e) {
                isLake = false;
              } else {
                node = createNode(curX, curY+1);
                if (!visited.contains(node)) {
                  queue.add(node);  
                }
              }
            }

            // West node
            if (curY-1 >= 0 && !grid[curX][curY-1]) {
              if (curY-1 < w) {
                isLake = false;
              } else {
                node = createNode(curX, curY-1);
                if (!visited.contains(node)) {
                  queue.add(node);  
                }
              }
            }
          }

          if (isLake) {
            retVal++;
          }
        }
      }
    }
    
    return retVal;
  }


  public static void main(String[] args) {
    List<TestData> testData = new ArrayList<>();

    boolean[][] grid = new boolean[8][];
    grid[0] = new boolean[] {false, false, false, false, false, false, false, false, false, false};
    grid[1] = new boolean[] {false, false, false, true,  true,  true,  true,  true,  false, false};
    grid[2] = new boolean[] {false, false, true,  true,  false, true,  false, true,  false, false};
    grid[3] = new boolean[] {false, false, false, false, false, true,  true,  true,  false, false};
    grid[4] = new boolean[] {false, false, false, true,  true,  false, false, true,  false, false};
    grid[5] = new boolean[] {false, false, false, true,  true,  true,  true,  true,  false, false};
    grid[6] = new boolean[] {false, false, false, false, false, false, false, false, false, false};
    grid[7] = new boolean[] {false, false, false, false, false, false, false, false, false, false};
    testData.add(new TestData(grid, 1, 3));

    grid = new boolean[8][];
    grid[0] = new boolean[] {false, false, false, false, false, false, false, false, false, false};
    grid[1] = new boolean[] {false, false, true,  true,  true,  true,  true,  true,  false, false};
    grid[2] = new boolean[] {false, false, true,  true,  false, true,  false, true,  false, false};
    grid[3] = new boolean[] {false, false, true,  false, false, true,  true,  true,  false, false};
    grid[4] = new boolean[] {false, false, true,  true,  true,  false, false, true,  false, false};
    grid[5] = new boolean[] {false, false, true,  true,  true,  true,  true,  true,  false, false};
    grid[6] = new boolean[] {false, false, false, false, false, false, false, false, false, false};
    grid[7] = new boolean[] {false, false, false, false, false, false, false, false, false, false};
    testData.add(new TestData(grid, 5, 7));

    grid = new boolean[8][];
    grid[0] = new boolean[] {false, false, false, false, false, false, false, false, false, false};
    grid[1] = new boolean[] {false, false, true,  true,  true,  true,  true,  true,  false, false};
    grid[2] = new boolean[] {false, false, true,  true,  false, true,  false, true,  false, false};
    grid[3] = new boolean[] {false, false, true,  false, false, true,  false, true,  false, false};
    grid[4] = new boolean[] {false, false, false, false, false, false, false, true,  false, false};
    grid[5] = new boolean[] {false, false, true,  true,  true,  true,  true,  true,  false, false};
    grid[6] = new boolean[] {false, false, false, false, false, false, false, false, false, false};
    grid[7] = new boolean[] {false, false, false, false, false, false, false, false, false, false};
    testData.add(new TestData(grid, 5, 4));

    grid = new boolean[8][];
    grid[0] = new boolean[] {true,  true,  true,  false, false, false, false, false, false, false};
    grid[1] = new boolean[] {true,  false, true,  false, true,  true,  true,  true,  false, false};
    grid[2] = new boolean[] {true,  true,  true,  false, false, true,  false, true,  false, false};
    grid[3] = new boolean[] {false, false, false, false, false, true,  false, true,  false, false};
    grid[4] = new boolean[] {false, false, false, false, false, false, false, true,  false, false};
    grid[5] = new boolean[] {false, false, true,  true,  true,  true,  true,  true,  false, false};
    grid[6] = new boolean[] {false, false, false, false, false, false, false, false, false, false};
    grid[7] = new boolean[] {false, false, false, false, false, false, false, false, false, false};
    testData.add(new TestData(grid, 0, 0));

    grid = new boolean[8][];
    grid[0] = new boolean[] {true,  true,  true,  false, false, false, false, false, false, false};
    grid[1] = new boolean[] {true,  false, true,  false, true,  true,  true,  true,  false, false};
    grid[2] = new boolean[] {true,  true,  true,  false, false, true,  false, true,  false, false};
    grid[3] = new boolean[] {false, false, false, false, false, true,  false, true,  false, false};
    grid[4] = new boolean[] {false, false, false, false, false, false, false, true,  false, false};
    grid[5] = new boolean[] {false, false, true,  true,  true,  true,  true,  true,  false, false};
    grid[6] = new boolean[] {false, false, false, false, false, false, false, false, false, false};
    grid[7] = new boolean[] {false, false, false, false, false, false, false, false, false, false};
    testData.add(new TestData(grid, 5, 2));

    grid = new boolean[8][];
    grid[0] = new boolean[] {true,  true,  true,  false, false, false, false, false, false, false};
    grid[1] = new boolean[] {true,  false, true,  false, true,  true,  true,  true,  false, false};
    grid[2] = new boolean[] {true,  true,  true,  false, false, true,  false, true,  false, false};
    grid[3] = new boolean[] {false, false, false, false, false, true,  false, true,  false, false};
    grid[4] = new boolean[] {false, false, false, false, false, true,  false, true,  false, false};
    grid[5] = new boolean[] {false, false, true,  true,  true,  true,  true,  true,  false, false};
    grid[6] = new boolean[] {false, false, false, false, false, false, false, false, false, false};
    grid[7] = new boolean[] {false, false, false, false, false, false, false, false, false, false};
    testData.add(new TestData(grid, 5, 4));

    for (TestData t : testData) {
      System.out.printf("startX=%d, startY=%d, num lakes=%d%n%n", t.startX, t.startY, countLakes(t.grid, t.startX, t.startY));
    }
  }

  public static class TestData {
    boolean[][] grid;
    int startX;
    int startY;

    public TestData(boolean[][] grid, int startX, int startY) {
      this.grid = grid;
      this.startX = startX;
      this.startY = startY;
    }
  }
}
