import java.util.Set;
import java.util.HashSet;
import java.util.Deque;
import java.util.ArrayDeque;

public class ClosestTarget {

    public static void addNeighbors(char[][] grid, Cell current) {

    }

    public static void addToQueue(char[][] grid, Cell cell, Set<Cell> visited, Deque<Cell> queue) {
        if (grid[cell.x][cell.y] == 'X') {
            visited.add(cell);
            return;
        }
        if (!visited.contains(cell)) {
            queue.add(cell);
        }
    }

    public static int closestTarget(char[][] grid, int x, int y) {
        // Setup our visited set and the queue that we will use to keep track
        // of all of the visited cells
        Set<Cell> visited = new HashSet<>();
        Deque<Cell> queue = new ArrayDeque<>();
        // Add the first cell onto the queue
        int distance = 0;
        queue.add(new Cell(x, y, 0));

        while (queue.size() > 0) {
            Cell c = queue.remove();
            visited.add(c);
            if (grid[c.x][c.y] == 'T') {
                return c.distance;
            }
            distance = c.distance + 1;


            // If we did not find a T cell in the grid, add any of the
            // neighbors/child nodes if they have not yet been visited.
            // N
            if (c.x > 0) {
                Cell n = new Cell(c.x - 1, c.y, distance);
                addToQueue(grid, n, visited, queue);
            }

            // S
            if (c.x < grid.length - 1) {
                Cell s = new Cell(c.x + 1, c.y, distance);
                addToQueue(grid, s, visited, queue);
            }

            // E
            if (c.y > 0) {
                Cell e = new Cell(c.x, c.y - 1, distance);
                addToQueue(grid, e, visited, queue);
            }

            // W
            if (c.y < grid[c.x].length - 1) {
                Cell n = new Cell(c.x, c.y + 1, distance);
                addToQueue(grid, n, visited, queue);
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{
            {'O', 'O', 'O', 'O', 'O'},
            {'O', 'X', 'O', 'O', 'O'},
            {'O', 'X', 'X', 'O', 'O'},
            {'O', 'X', 'T', 'O', 'O'},
            {'O', 'X', 'X', 'O', 'O'},
            {'T', 'O', 'O', 'O', 'O'}
        };
        System.out.println(closestTarget(grid, 1, 2));


        grid = new char[][]{
            {'O', 'O', 'X', 'X', 'X'},
            {'O', 'X', 'X', 'X', 'T'},
            {'O', 'X', 'O', 'X', 'X'},
            {'O', 'O', 'O', 'O', 'O'},
            {'O', 'X', 'X', 'X', 'X'},
            {'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'T', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O'}
        };
        System.out.println(closestTarget(grid, 3, 4)); // -> 9
    }

    public static class Cell {
        int x;
        int y;
        int distance;
        public Cell(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return String.format("Cell[x=%d, y=%d, d=%d]", x, y, distance);
        }

        @Override
        public int hashCode() {
            return String.format("%d,%d", x, y).hashCode();
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof Cell) {
                Cell otherCell = (Cell) other;
                if (this.x != otherCell.x) {
                    return false;
                }
                if (this.y != otherCell.y) {
                    return false;
                }
                return true;
            }
            return false;
        }
    }
}
